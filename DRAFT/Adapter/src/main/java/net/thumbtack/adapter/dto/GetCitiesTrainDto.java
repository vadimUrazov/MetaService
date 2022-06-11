package net.thumbtack.adapter.dto;

import io.aexp.nodes.graphql.annotations.GraphQLProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@GraphQLProperty(name = "getCitiesByFromStation")
public class GetCitiesTrainDto {
    private List<TripTrain> cities = new ArrayList<>();
}
