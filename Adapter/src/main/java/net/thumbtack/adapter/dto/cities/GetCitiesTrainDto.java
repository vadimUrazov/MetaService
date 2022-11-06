package net.thumbtack.adapter.dto.cities;

import io.aexp.nodes.graphql.annotations.GraphQLProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.thumbtack.adapter.dto.trips.TripTrain;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@GraphQLProperty(name = "getCitiesByFromStation")
public class GetCitiesTrainDto {
    private List<TripTrain> cities = new ArrayList<>();
    private List<TripTrain> toCities = new ArrayList<>();

    public GetCitiesTrainDto(List<TripTrain> cities) {
        this.cities = cities;
    }
}
