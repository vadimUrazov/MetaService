package net.thumbtack.shipcompany.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChoosePlaceResponse {
    private long orderId;
    private String ticket;
    private String lastName;
    private String firstName;
    private int place;
}
