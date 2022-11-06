package net.thumbtack.traincompany.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class ChoosePlaceRequest {

    private long clientId;

    private long orderId;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String firstName;

    private long passport;

    private int place;

    private int car;

    @JsonCreator
    public ChoosePlaceRequest() {
    }

}
