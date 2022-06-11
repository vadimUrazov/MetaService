package net.thumbtack.traincompany.service.mappers;

import javax.annotation.processing.Generated;
import net.thumbtack.traincompany.dto.request.RegisterClientDtoRequest;
import net.thumbtack.traincompany.entity.Client;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-11T13:32:03+0600",
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
