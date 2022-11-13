package net.thumbtack.shipcompany.service.mappers;

import javax.annotation.processing.Generated;
import net.thumbtack.shipcompany.dto.request.RegisterClientDtoRequest;
import net.thumbtack.shipcompany.entity.Client;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-13T15:30:56+0600",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
public class ClientRegisterMapperImpl implements ClientRegisterMapper {

    @Override
    public Client fromDto(RegisterClientDtoRequest request) {
        if ( request == null ) {
            return null;
        }

        Client client = new Client();

        client.setSurname( request.getSurname() );
        client.setName( request.getName() );
        client.setMiddlename( request.getMiddlename() );
        client.setLogin( request.getLogin() );
        client.setPassword( request.getPassword() );
        client.setEmail( request.getEmail() );
        client.setPhone( request.getPhone() );

        return client;
    }
}
