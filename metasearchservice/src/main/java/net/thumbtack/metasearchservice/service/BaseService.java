package net.thumbtack.metasearchservice.service;

import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;
import io.aexp.nodes.graphql.Variable;
import net.thumbtack.metasearchservice.dto.response.ClientDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

@Component
public class BaseService {
    @Autowired
    protected CacheManager cacheManager;
    private static String requestGetIdUserQuery(GraphQLTemplate.GraphQLMethod method) {
        return method.getValue() +
                "($login: String){ " +
                "getUserByLogin(login: $login)" +
                "{ " +
                "id " +
                "surname " +
                "name " +
                "middlename " +
                "email " +
                "phone " +
                "userType" +
                "}" +
                "}"
                ;

    }
    public ClientDtoResponse getIdClientByLogin(String login){
        ClientDtoResponse user = null;
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/graphql+json");
        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
        GraphQLRequestEntity requestEntity = null;
        try {
            requestEntity = GraphQLRequestEntity.Builder()
                    .request(requestGetIdUserQuery(GraphQLTemplate.GraphQLMethod.QUERY))
                    .url("http://localhost:8070/graphql")
                    .variables(new Variable<String>("login", login))
                    .headers(headers)
                    .build();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        user = graphQLTemplate.query(requestEntity, ClientDtoResponse.class).getResponse();
         return user;
    }
}
