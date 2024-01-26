package net.thumbtack.buscompany.dto.response;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.thumbtack.buscompany.dto.BusDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GetBusesResponse {

  private List<BusDto> busList;
}
