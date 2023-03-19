package net.thumbtack.metasearchservice.dto.request;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
