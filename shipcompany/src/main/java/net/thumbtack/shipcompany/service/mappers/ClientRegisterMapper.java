package net.thumbtack.shipcompany.service.mappers;

import net.thumbtack.shipcompany.dto.request.RegisterClientDtoRequest;
import net.thumbtack.shipcompany.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientRegisterMapper {
    ClientRegisterMapper INSTANCE = Mappers.getMapper(ClientRegisterMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "surname", source = "surname"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "middlename", source = "middlename"),
            @Mapping(target = "login", source = "login"),
            @Mapping(target = "password", source = "password"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "phone", source = "phone")
    })
    Client fromDto(RegisterClientDtoRequest request);

}
