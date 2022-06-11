package net.thumbtack.traincompany.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTrainDtoRequest {
    @NotEmpty
    private String trainName;
    private int placeCount;
    private int cars;
}
