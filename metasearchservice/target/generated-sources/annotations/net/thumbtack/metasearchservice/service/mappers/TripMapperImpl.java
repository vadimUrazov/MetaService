package net.thumbtack.metasearchservice.service.mappers;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import net.thumbtack.metasearchservice.dto.TripDto;
import net.thumbtack.metasearchservice.entity.Transport;
import net.thumbtack.metasearchservice.entity.Trip;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-19T16:34:17+0600",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
public class TripMapperImpl implements TripMapper {

    @Override
    public Trip fromDto(TripDto request) {
        if ( request == null ) {
            return null;
        }

        Trip trip = new Trip();

        trip.setId( request.getId() );
        trip.setFromStation( request.getFromStation() );
        if ( request.getTransport() != null ) {
            trip.setTransport( Enum.valueOf( Transport.class, request.getTransport() ) );
        }
        trip.setPrice( BigDecimal.valueOf( request.getPrice() ) );
        if ( request.getStart() != null ) {
            trip.setStart( LocalTime.parse( request.getStart() ) );
        }
        if ( request.getDuration() != null ) {
            trip.setDuration( LocalTime.parse( request.getDuration() ) );
        }
        List<String> list = request.getDayTrips();
        if ( list != null ) {
            trip.setDayTrips( new ArrayList<String>( list ) );
        }
        trip.setToStation( request.getToStation() );

        return trip;
    }

    @Override
    public TripDto toDto(Trip request) {
        if ( request == null ) {
            return null;
        }

        TripDto tripDto = new TripDto();

        tripDto.setId( request.getId() );
        tripDto.setFromStation( request.getFromStation() );
        if ( request.getTransport() != null ) {
            tripDto.setTransport( request.getTransport().name() );
        }
        if ( request.getPrice() != null ) {
            tripDto.setPrice( request.getPrice().intValue() );
        }
        if ( request.getStart() != null ) {
            tripDto.setStart( DateTimeFormatter.ISO_LOCAL_TIME.format( request.getStart() ) );
        }
        if ( request.getDuration() != null ) {
            tripDto.setDuration( DateTimeFormatter.ISO_LOCAL_TIME.format( request.getDuration() ) );
        }
        List<String> list = request.getDayTrips();
        if ( list != null ) {
            tripDto.setDayTrips( new ArrayList<String>( list ) );
        }
        tripDto.setToStation( request.getToStation() );

        return tripDto;
    }

    @Override
    public List<Trip> fromDtoAll(List<TripDto> request) {
        if ( request == null ) {
            return null;
        }

        List<Trip> list = new ArrayList<Trip>( request.size() );
        for ( TripDto tripDto : request ) {
            list.add( fromDto( tripDto ) );
        }

        return list;
    }

    @Override
    public List<TripDto> toDtoAll(List<Trip> request) {
        if ( request == null ) {
            return null;
        }

        List<TripDto> list = new ArrayList<TripDto>( request.size() );
        for ( Trip trip : request ) {
            list.add( toDto( trip ) );
        }

        return list;
    }
}
