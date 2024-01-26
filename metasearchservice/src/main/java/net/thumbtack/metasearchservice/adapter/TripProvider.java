package net.thumbtack.metasearchservice.adapter;

import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;
import io.aexp.nodes.graphql.Variable;
import net.thumbtack.metasearchservice.dto.GetTripsDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TripProvider {

    private static String requestQuery(GraphQLTemplate.GraphQLMethod method) {
        return method.getValue() +
                "($fromStation: String,$toStation: String){ " +
                "getTrips(fromStation: $fromStation,toStation: $toStation){ " +
                "fromBuses{" +
                "id " +
                "fromStation " +
                "toStation " +
                "transport " +
                "price " +
                "start " +
                "duration " +
                "dayTrips " +
                "}" +
                "toBuses{" +
                "id " +
                "fromStation " +
                "toStation " +
                "transport " +
                "price " +
                "start " +
                "duration " +
                "dayTrips " +
                "}" +
                "fromTrains{" +
                "id " +
                "fromStation " +
                "toStation " +
                "transport " +
                "price " +
                "start " +
                "duration " +
                "dayTrips " +
                "}" +
                "toTrains{" +
                "id " +
                "fromStation " +
                "toStation " +
                "transport " +
                "price " +
                "start " +
                "duration " +
                "dayTrips " +
                "}" +
                "fromShips{" +
                "id " +
                "fromStation " +
                "toStation " +
                "transport " +
                "price " +
                "start " +
                "duration " +
                "dayTrips " +
                "}" +
                "toShips{" +
                "id " +
                "fromStation " +
                "toStation " +
                "transport " +
                "price " +
                "start " +
                "duration " +
                "dayTrips " +
                "}" +
                "}" +
                "}";
    }

    public GetTripsDto getTripsAdapter(String from, String to) throws Exception {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/graphql+json");
        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
        GraphQLRequestEntity requestEntity = GraphQLRequestEntity.Builder()
                .request(requestQuery(GraphQLTemplate.GraphQLMethod.QUERY))
                .url("http://localhost:8070/graphql")
                .variables(new Variable<String>("fromStation", from), new Variable<String>("toStation", to))
                .headers(headers)
                .build();

        var res = graphQLTemplate.query(requestEntity, GetTripsDto.class);
        return res.getResponse();
    }
}
