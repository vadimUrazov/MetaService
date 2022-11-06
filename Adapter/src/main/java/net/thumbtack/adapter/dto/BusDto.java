package net.thumbtack.adapter.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusDto {
    String busName;

    @JsonCreator
    public BusDto() {
    }
}
