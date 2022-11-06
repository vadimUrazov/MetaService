package net.thumbtack.shipcompany.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class GetClientsResponse {
    private List<ClientDtoResponse> clients;

}
