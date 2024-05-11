package net.thumbtack.metasearchservice.adapter;

import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;
import io.aexp.nodes.graphql.Variable;
import net.thumbtack.metasearchservice.dto.request.*;
import net.thumbtack.metasearchservice.dto.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.*;
import java.util.concurrent.CountDownLatch;

@Service
public class UserProvider {
    @Autowired
    private KafkaProducer producer;

    private static String requestRegisterQuery(GraphQLTemplate.GraphQLMethod method) {
        return method.getValue() +
                "($request: RegisterClientDtoRequest!){ " +
                "registerUser(request: $request)" +
                "{ " +
                "id " +
                "surname " +
                "name " +
                "middlename " +
                "email " +
                "phone " +
                "}" +
                "}"
                ;

    }

    private static String requestLoginQuery(GraphQLTemplate.GraphQLMethod method) {
        return method.getValue() +
                "($request: LoginDtoRequest!){ " +
                "loginUser(request: $request)" +
                "{ " +
                "token" +
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
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        ClientDtoResponse user = null;
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/graphql+json");
        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
        GraphQLRequestEntity requestEntity = null;
        try {
            requestEntity = GraphQLRequestEntity.Builder()
                    .request(requestGetUserQuery(GraphQLTemplate.GraphQLMethod.QUERY))
                    .url("http://localhost:8070/graphql")
                    .variables(new Variable<String>("login", username))
                    .headers(headers)
                    .build();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        user = graphQLTemplate.query(requestEntity, ClientDtoResponse.class).getResponse();

        return User.withUsername(username)
                .password(user.getPassword())
                .roles(user.getType())
                .build();
    }

    public RegisterUserDtoResponse registerUser(RegisterClientDtoRequest request) throws Exception {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/graphql+json");
        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
        GraphQLRequestEntity requestEntity = GraphQLRequestEntity.Builder()
                .request(requestRegisterQuery(GraphQLTemplate.GraphQLMethod.MUTATE))
                .url("http://localhost:8070/graphql")
                .variables(new Variable<RegisterClientDtoRequest>("request", request))
                .headers(headers)
                .build();

        var res = graphQLTemplate.query(requestEntity, RegisterUserDtoResponse.class);
        return res.getResponse();

    }

    public LoginDtoResponse loginUser(LoginDtoRequest request) throws Exception {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/graphql+json");
        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
        GraphQLRequestEntity requestEntity = GraphQLRequestEntity.Builder()
                .request(requestLoginQuery(GraphQLTemplate.GraphQLMethod.MUTATE))
                .url("http://localhost:8070/graphql")
                .variables(new Variable<LoginDtoRequest>("request", request))
                .headers(headers)
                .build();

        var res = graphQLTemplate.query(requestEntity, LoginDtoResponse.class);
        return res.getResponse();

    }



    public void createOrder(CreateOrderRequest request,String topic) {
        producer.send(request,topic);

    }

    public void choosePlace(ChoosePlaceRequest request,String topic){
        producer.send(request,topic);
    }

}