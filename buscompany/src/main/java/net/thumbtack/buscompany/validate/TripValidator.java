package net.thumbtack.buscompany.validate;

import net.thumbtack.buscompany.dto.request.AddTripRequest;
import net.thumbtack.buscompany.dto.request.UpdateTripDtoRequest;
import net.thumbtack.buscompany.exception.ErrorCode;
import net.thumbtack.buscompany.exception.ServiceException;
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

    private Optional<Collection<String>> validateDates(Collection<String> dates)
            throws ServiceException {

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
        if (StringUtils.isBlank(request.getBusName())) {
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

    public Optional<UpdateTripDtoRequest> validate(final UpdateTripDtoRequest request)
            throws ServiceException {
        if (StringUtils.isBlank(request.getFromStation())) {
            err.setField("fromStation");
            throw new ServiceException(err);
        }
        if (StringUtils.isBlank(request.getToStation())) {
            err.setField("toStation");
            throw new ServiceException(err);
        }
        if (StringUtils.isBlank(request.getBusName())) {
            err.setField("busName");
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
