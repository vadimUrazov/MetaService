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
@GraphQLProperty(name = "getCitiesByToStation")
public class GetCitiesToTrainDto {
    private List<TripTrain> cities = new ArrayList<>();
}
