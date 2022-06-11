package net.thumbtack.traincompany.dto.response;


import lombok.*;
import net.thumbtack.traincompany.dto.TrainDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GetTrainResponse {
    private List<TrainDto> trainList;
}
