package net.thumbtack.shipcompany.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoDtoResponse {
    @NotEmpty
    private String cargoType;
    private long idClient;
}
