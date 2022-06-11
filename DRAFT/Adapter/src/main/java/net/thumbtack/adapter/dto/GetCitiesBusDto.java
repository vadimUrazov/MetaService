package net.thumbtack.adapter.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@GraphQLProperty(name = "getCitiesByFromStation")
public class GetCitiesBusDto {
    private List<TripBus> cities = new ArrayList<>();

    @JsonCreator
    public GetCitiesBusDto() {
    }
}
