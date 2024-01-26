package net.thumbtack.buscompany.service.mappers;

import net.thumbtack.buscompany.dto.request.RegisterAdminDtoRequest;
import net.thumbtack.buscompany.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminRegisterMapper {

  AdminRegisterMapper INSTANCE = Mappers.getMapper(AdminRegisterMapper.class);

  @Mappings({
      @Mapping(target = "surname", source = "surname"),
      @Mapping(target = "name", source = "name"),
      @Mapping(target = "middlename", source = "middlename"),
      @Mapping(target = "login", source = "login"),
      @Mapping(target = "password", source = "password"),
      @Mapping(target = "position", source = "position")
  })
  Admin fromDto(RegisterAdminDtoRequest request);
}
