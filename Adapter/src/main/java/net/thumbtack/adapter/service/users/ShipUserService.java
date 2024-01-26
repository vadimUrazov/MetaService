package net.thumbtack.adapter.service.users;

import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;
import io.aexp.nodes.graphql.Variable;
import net.thumbtack.adapter.dto.users.ClientDtoResponse;
import net.thumbtack.adapter.dto.users.RegisterClientDtoRequest;
import net.thumbtack.adapter.dto.users.RegisterClientDtoResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

@Service
@ConditionalOnProperty(
        value = "delivery.type",
        havingValue = "getShip",
        matchIfMissing = true)
public class ShipUserService implements UserService<RegisterClientDtoResponse> {
    @Override
    public String getTypeCompany() {
        return "Ship";
    }

    private static String requestRegisterQuery(GraphQLTemplate.GraphQLMethod method) {
        return method.getValue() +
                "($request: RegisterClientDtoRequest!){ " +
                "registerClient(request: $request)" +
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

    private static String requestGetUserQuery(GraphQLTemplate.GraphQLMethod method) {
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

    public ClientDtoResponse getUserByLogin(String login) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/graphql+json");
        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
        GraphQLRequestEntity requestEntity = null;
        try {
            requestEntity = GraphQLRequestEntity.Builder()
                    .request(requestGetUserQuery(GraphQLTemplate.GraphQLMethod.QUERY))
                    .url("http://localhost:9050/graphql")
                    .variables(new Variable<String>("login", login))
                    .headers(headers)
                    .build();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        var res = graphQLTemplate.query(requestEntity, ClientDtoResponse.class);
        return res.getResponse();
    }

    @Override
    @Retryable(retryFor = ConnectException.class, maxAttemptsExpression = "${retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
    public Future<RegisterClientDtoResponse> registerUser(RegisterClientDtoRequest request) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/graphql+json");
        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
        GraphQLRequestEntity requestEntity = null;
        try {
            if (getUserByLogin(request.getLogin()) != null) {
                throw new RuntimeException("User already exists");
            }
            requestEntity = GraphQLRequestEntity.Builder()
                    .request(requestRegisterQuery(GraphQLTemplate.GraphQLMethod.MUTATE))
                    .url("http://localhost:9050/graphql")
                    .variables(new Variable<RegisterClientDtoRequest>("request", request))
                    .headers(headers)
                    .build();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return  new AsyncResult<RegisterClientDtoResponse>(graphQLTemplate.query(requestEntity, RegisterClientDtoResponse.class).getResponse());

    }
}
