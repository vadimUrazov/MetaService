package net.thumbtack.shipcompany.dao.impl;

import lombok.NoArgsConstructor;
import net.thumbtack.shipcompany.dao.ClientDao;
import net.thumbtack.shipcompany.entity.*;
import net.thumbtack.shipcompany.exception.ErrorCode;
import net.thumbtack.shipcompany.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository("SQLClientDao")
@NoArgsConstructor
public class ClientDaoImpl extends BaseDaoImpl implements ClientDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void deleteOrder(long id) throws ServiceException {
        LOGGER.debug("DAO delete order{ }");

        var order = orderRepository.getOrderById(id);
        if (order == null) {
            throw new ServiceException(ErrorCode.ORDER_NOT_FOUND);
        }
        order.setClient(null);
        orderRepository.deleteById(id);


    }

    @Override
    @Transactional
    public Order createOrder(Order order, OrderType orderType) throws ServiceException {
        LOGGER.debug("DAO create order{ }");

        List<Passenger> pass = new ArrayList<>();
        List<Cargo> carg = new ArrayList<>();
        pass.addAll(order.getPassengers());
        carg.addAll(order.getCargos());
        order.getPassengers().clear();
        order.getCargos().clear();

        orderRepository.save(order);

        var id = order.getId();
        if (orderType.equals(OrderType.CARGO)) {
            for (Cargo cargo : carg) {
                cargo.setIdOrder(id);
                cargoRepository.save(cargo);
                order.getCargos().add(cargo);
            }
        } else {
            if (orderRepository.updateDayTrip(order.getPassengers().size()) == 0) {
                throw new ServiceException(ErrorCode.INCORRECT_ORDER);
            }
            for (Passenger passenger : pass) {
                passenger.setIdOrder(id);
                passengerRepository.save(passenger);
                order.getPassengers().add(passenger);
            }
        }

        return order;
    }

    @Override
    @Transactional
    public Order getOrderById(long id) throws ServiceException {
        LOGGER.debug("DAO get Order By Id { }");
        return orderRepository.getOrderById(id);


    }

    @Override
    @Transactional
    public Place choosePlace(Passenger passenger, int place, Order order) throws ServiceException {
        LOGGER.debug("DAO create order{ }");
        var res = new Place(place);
        var idDay = order.getDayTrip().getId();

        String sql = "UPDATE place SET id_passenger=:passenger WHERE  number=:place AND id_day_trip=:day AND place.id_passenger IS NULL";
        String free = "UPDATE place SET id_passenger=null WHERE id_day_trip=:day AND number=:place";

        var placeCount = entityManager.createNativeQuery(sql, Place.class).setParameter("passenger", passenger.getId()).setParameter("place", place).setParameter("day", idDay).executeUpdate();

        var oldPlace = passenger.getPlace();
        if (placeCount == 1 && oldPlace != null) {
            entityManager.createNativeQuery(free).setParameter("day", idDay).setParameter("place", oldPlace.getNumber());
        }

        if (placeCount == 0) {
            return null;
        }


        return res;
    }

    @Override
    @Transactional
    public List<Place> getFreePlaces(long orderId) throws ServiceException {
        LOGGER.debug("DAO create order{ }");
        List<Place> places = new ArrayList<>();
        var order = orderRepository.getOrderById(orderId);
        var list = placeRepository.getPlacesByPassengerNull();
        for (Place place : list) {
            var id = place.getIdDayTrip();
            var day = dayTripRepository.getDayTripById(id);
            if (order.getDayTrip().equals(day)) {
                places.add(place);
            }
        }

        return places;
    }

}
