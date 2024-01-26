package net.thumbtack.metasearchservice.service.mappers;

import net.thumbtack.metasearchservice.dto.TripDto;
import net.thumbtack.metasearchservice.entity.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface TripMapper {
    TripMapper INSTANCE = Mappers.getMapper(TripMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "fromStation", source = "fromStation"),
            @Mapping(target = "transport", source = "transport"),
            @Mapping(target = "price", source = "price"),
            @Mapping(target = "start", source = "start"),
            @Mapping(target = "duration", source = "duration"),
            @Mapping(target = "dayTrips", source = "dayTrips")
    })
    Trip fromDto(TripDto request);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "fromStation", source = "fromStation"),
            @Mapping(target = "transport", source = "transport"),
            @Mapping(target = "price", source = "price"),
            @Mapping(target = "start", source = "start"),
            @Mapping(target = "duration", source = "duration"),
            @Mapping(target = "dayTrips", source = "dayTrips")
    })
    TripDto toDto(Trip request);

    List<Trip> fromDtoAll(List<TripDto> request);

    List<TripDto> toDtoAll(List<Trip> request);
}
