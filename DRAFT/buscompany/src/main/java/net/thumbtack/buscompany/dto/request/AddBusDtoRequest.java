package net.thumbtack.buscompany.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBusDtoRequest {
    @NotEmpty
    private String busName;
    private int placeCount;
}
