package net.thumbtack.metasearchservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.thumbtack.metasearchservice.dto.request.PassengerDtoRequest;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDto {
   private long idClient;
   private PassengerDtoRequest passenger;
}
