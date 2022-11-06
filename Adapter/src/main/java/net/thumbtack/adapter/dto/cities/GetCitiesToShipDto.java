package net.thumbtack.adapter.dto.cities;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.thumbtack.adapter.dto.trips.TripShip;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@GraphQLProperty(name = "getCitiesByToStation")
public class GetCitiesToShipDto {
    private List<TripShip> cities = new ArrayList<>();

    @JsonCreator
    public GetCitiesToShipDto() {
    }
}
