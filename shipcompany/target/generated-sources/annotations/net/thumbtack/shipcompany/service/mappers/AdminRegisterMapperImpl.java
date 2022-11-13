package net.thumbtack.shipcompany.service.mappers;

import javax.annotation.processing.Generated;
import net.thumbtack.shipcompany.dto.request.RegisterAdminDtoRequest;
import net.thumbtack.shipcompany.entity.Admin;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-13T15:30:56+0600",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
public class AdminRegisterMapperImpl implements AdminRegisterMapper {

    @Override
    public Admin fromDto(RegisterAdminDtoRequest request) {
        if ( request == null ) {
            return null;
        }

        Admin admin = new Admin();

        admin.setSurname( request.getSurname() );
        admin.setName( request.getName() );
        admin.setMiddlename( request.getMiddlename() );
        admin.setLogin( request.getLogin() );
        admin.setPassword( request.getPassword() );
        admin.setPosition( request.getPosition() );

        return admin;
    }
}
