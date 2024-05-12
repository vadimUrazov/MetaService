package net.thumbtack.traincompany.service;

import net.thumbtack.traincompany.config.KafkaConsumerPlace;
import net.thumbtack.traincompany.dto.PlaceDto;
import net.thumbtack.traincompany.dto.request.ChoosePlaceRequest;
import net.thumbtack.traincompany.dto.request.ChoosePlacesRequest;
import net.thumbtack.traincompany.dto.request.RegisterClientDtoRequest;
import net.thumbtack.traincompany.dto.request.UpdateClientDtoRequest;
import net.thumbtack.traincompany.dto.response.ChoosePlaceResponse;
import net.thumbtack.traincompany.dto.response.ChoosePlacesResponse;
import net.thumbtack.traincompany.dto.response.GetFreePlaceResponse;
import net.thumbtack.traincompany.dto.response.RegisterClientDtoResponse;
import net.thumbtack.traincompany.dto.response.UpdateClientDtoResponse;
import net.thumbtack.traincompany.entity.Client;
import net.thumbtack.traincompany.entity.Passenger;
import net.thumbtack.traincompany.entity.Place;
import net.thumbtack.traincompany.entity.UserType;
import net.thumbtack.traincompany.exception.ErrorCode;
import net.thumbtack.traincompany.exception.ServiceException;
import net.thumbtack.traincompany.service.mappers.ClientRegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ClientService extends ServiceBase {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    KafkaConsumerPlace consumer;

    @Autowired
    public ClientService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    private String parsePhone(String phone) {
        return phone.replace("-", "");
    }

    public RegisterClientDtoResponse registerClient(RegisterClientDtoRequest request) throws ServiceException {
        Client client = ClientRegisterMapper.INSTANCE.fromDto(request);
        var password = passwordEncoder.encode(request.getPassword());
        client.setUserType(UserType.CLIENT);
        client.setPassword(password);
        client.setPhone(parsePhone(request.getPhone()));
        Client res = (Client) userDao.registerUser(client);
        RegisterClientDtoResponse registerClientDtoResponse = new RegisterClientDtoResponse(res.getId(), res.getSurname(),
                res.getName(), res.getMiddlename(), res.getEmail(), res.getPhone(), "CLIENT");

        return registerClientDtoResponse;
    }

    public UpdateClientDtoResponse updateClient(long id, UpdateClientDtoRequest request) throws ServiceException {
        Client client = (Client) userDao.getUserById(id);

        client.setSurname(request.getSurname());
        client.setName(request.getName());
        client.setMiddlename(request.getMiddlename());
        client.setPassword(request.getNewPassword());
        client.setEmail(request.getEmail());
        client.setPhone(request.getPhone());

        userDao.updateUser(client);
        return new UpdateClientDtoResponse(client.getSurname(), client.getName(), client.getMiddlename(), client.getEmail(), client.getPhone(), "CLIENT");
    }

    public ChoosePlaceResponse choosePlace(ChoosePlaceRequest request) throws ServiceException {
        checkPassenger(request.getFirstName(), request.getLastName(), request.getPassport());

        Client client = (Client) userDao.getUserById(request.getClientId());
        Passenger passenger = new Passenger(request.getFirstName(), request.getLastName(), request.getPassport());

        var order = clientDao.getOrderById(request.getOrderId());

        if (order == null) {
            throw new ServiceException(ErrorCode.ORDER_NOT_FOUND);
        }

        if (order.getClient().getId() != client.getId()) {
            throw new ServiceException(ErrorCode.ORDER_IS_STRANGER);
        }

        var passengers = order.getPassengers();

        var flag = false;

        for (Passenger pas : passengers) {
            if (pas.getFirstName().equals(request.getFirstName()) && pas.getLastName().equals(request.getLastName()) && pas.getPassport() == request.getPassport()) {
                flag = true;
                passenger.setId(pas.getId());
                passenger.setPlace(pas.getPlace());
                break;
            }
        }
        if (!flag) {
            throw new ServiceException(ErrorCode.PASSENGER_NOT_FOUND);
        }
        var places = order.getDayTrip().getPlaces();

        Collections.sort(places, Comparator.comparingInt(Place::getNumber));
        Place place = null;
        int index = Collections.binarySearch(places, new Place(request.getPlace(), request.getCar()), Comparator.comparingInt(Place::getNumber));
        if (index >= 0 && places.get(index).getPassenger() == null) {
            place = clientDao.choosePlace(passenger, request.getPlace(), request.getCar(), order);

        }

        if (place == null) {
            throw new ServiceException(ErrorCode.INCORRECT_PLACE);
        }

        var ticket = "Билет " + order.getId() + "_" + place.getNumber() + "_" + place.getCar();
        return new ChoosePlaceResponse(request.getOrderId(), ticket, request.getLastName(), request.getFirstName(), request.getPlace(), request.getCar());
    }

    public ChoosePlacesResponse choosePlaces(ChoosePlacesRequest request) throws ServiceException {
        request=consumer.getDtoRequest();
        List<ChoosePlaceResponse> resp = new ArrayList<>();
        for (ChoosePlaceRequest req : request.getRequest()) {
            var buf = choosePlace(req);
            resp.add(buf);
        }
        return new ChoosePlacesResponse(resp);
    }
    public GetFreePlaceResponse getFreePlaces(long id) throws ServiceException {


        if (clientDao.getOrderById(id) == null) {
            throw new ServiceException(ErrorCode.ORDER_NOT_FOUND);
        }

        var places = clientDao.getFreePlaces(id);

        List<PlaceDto> placeDtos = new ArrayList<>();
        for (Place place : places) {
            placeDtos.add(new PlaceDto(place.getNumber(), place.getCar()));
        }

        return new GetFreePlaceResponse(placeDtos);
    }


}
