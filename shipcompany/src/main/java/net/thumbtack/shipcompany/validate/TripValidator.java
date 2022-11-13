package net.thumbtack.shipcompany.validate;

import net.thumbtack.shipcompany.dto.request.AddTripRequest;
import net.thumbtack.shipcompany.dto.request.UpdateTripDtoRequest;
import net.thumbtack.shipcompany.exception.ErrorCode;
import net.thumbtack.shipcompany.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Optional;

@Component
public class TripValidator {
    private final ErrorCode err = ErrorCode.ERROR_FIELD;

    private Optional<Collection<String>> validateDates(Collection<String> dates) throws ServiceException {

        if (dates.isEmpty()) {
            throw new ServiceException(ErrorCode.DATE_NOT_FOUND);
        }
        try {
            for (String date : dates) {
                LocalDate.parse(date);
            }
        } catch (DateTimeParseException e) {
            throw new ServiceException(ErrorCode.DATE_NOT_FOUND);
        }
        for (String date : dates) {
            var dat = LocalDate.parse(date);
            if (dat.getMonthValue() == 1 || dat.getMonthValue() == 2 || dat.getMonthValue() == 3) {
                throw new ServiceException(ErrorCode.ERROR_DATE);
            }
        }

        return Optional.of(dates);
    }


    public Optional<AddTripRequest> validate(final AddTripRequest request) throws ServiceException {
        if (StringUtils.isBlank(request.getFromStation())) {
            err.setField("fromStation");
            throw new ServiceException(err);
        }
        if (StringUtils.isBlank(request.getToStation())) {
            err.setField("toStation");
            throw new ServiceException(err);
        }
        if (StringUtils.isBlank(request.getShipName())) {
            err.setField("busName");
            throw new ServiceException(err);
        }


        try {
            LocalTime.parse(request.getStart());
        } catch (DateTimeParseException e) {
            throw new ServiceException(ErrorCode.INCORRECT_START_TIME);
        }

        try {
            LocalTime.parse(request.getDuration());
        } catch (DateTimeParseException e) {
            throw new ServiceException(ErrorCode.INCORRECT_DURATION);
        }


        if (request.getPrice().intValue() <= 0) {
            throw new ServiceException(ErrorCode.INCORRECT_PRICE);
        }

        if (request.getDates() == null) {
            throw new ServiceException(ErrorCode.INCORRECT_TRIP);
        }

        if (request.getDates() != null) {
            validateDates(request.getDates());
        }


        return Optional.of(request);
    }

    public Optional<UpdateTripDtoRequest> validate(final UpdateTripDtoRequest request) throws ServiceException {
        if (StringUtils.isBlank(request.getFromStation())) {
            err.setField("fromStation");
            throw new ServiceException(err);
        }
        if (StringUtils.isBlank(request.getToStation())) {
            err.setField("toStation");
            throw new ServiceException(err);
        }
        if (StringUtils.isBlank(request.getShipName())) {
            err.setField("shipName");
            throw new ServiceException(err);
        }

        if (request.getDates() == null) {
            throw new ServiceException(ErrorCode.INCORRECT_TRIP);
        }
        try {
            LocalTime.parse(request.getStart());
        } catch (DateTimeParseException e) {
            throw new ServiceException(ErrorCode.INCORRECT_START_TIME);
        }

        try {
            LocalTime.parse(request.getDuration());
        } catch (DateTimeParseException e) {
            throw new ServiceException(ErrorCode.INCORRECT_DURATION);
        }


        if (request.getPrice().intValue() <= 0) {
            throw new ServiceException(ErrorCode.INCORRECT_PRICE);
        }


        return Optional.of(request);
    }


}
