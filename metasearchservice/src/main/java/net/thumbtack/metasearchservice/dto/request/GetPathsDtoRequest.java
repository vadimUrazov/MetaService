package net.thumbtack.metasearchservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPathsDtoRequest {
    @NotEmpty
    private String fromStation;
    @NotEmpty
    private String toStation;

    @NotEmpty
    private String criteria;

    @NotEmpty
    private String transport;

    @NotEmpty
    private String dateFrom;
}
