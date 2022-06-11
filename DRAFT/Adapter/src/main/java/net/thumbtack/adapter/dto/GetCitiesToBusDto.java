package net.thumbtack.adapter.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@GraphQLProperty(name = "getCitiesByToStation")
public class GetCitiesToBusDto {
    private List<TripBus> cities = new ArrayList<>();

    @JsonCreator
    public GetCitiesToBusDto() {
    }
}
