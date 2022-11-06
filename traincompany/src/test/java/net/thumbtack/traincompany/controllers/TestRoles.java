package net.thumbtack.traincompany.controllers;


import com.jayway.restassured.http.ContentType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
public class TestRoles extends AbstractControllerTest {

    @Test
    public void testNoAdmin() {
        var result = given()
                .contentType(ContentType.JSON)
                .body("{\"query\":\"mutation {\\n  registerClient(\\n    request: {surname: \\\"Иванов\\\", name: \\\"Иван\\\", middlename: \\\"Иванович\\\", login: \\\"ivanpetrgrhuhu\\\", password: \\\"wknc&ekv12\\\", email: \\\"ivanov@mail.ru\\\", phone: \\\"8-916-621-32-94\\\"}\\n  ) {\\n    id\\n    surname\\n    name\\n    type\\n  }\\n}\",\"variables\":null}")
                .when()
                .post("http://localhost:9090/graphql/");

        var res = given()
                .contentType(ContentType.JSON)
                .body("{\\\"query\\\":\\\"query{\\\\n  getBuses{\\\\n    busList{" +
                        "}\\\\n  }\\\\n}\\\\n\\\\n\\\",\\\"variables\\\":null}\"")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpdmFucGV0cmdyaHVodSIsImlhdCI6MTY1MDYyODI5NSwiZXhwIjoxNjUwNzE0Njk1fQ.oAsLcz3UOYSAB7m7M9amjU09dwzvQJYtjsz5l-RePFfqbK_4aG1bd3Hdniq6XHVFrjsgKBwuAMo_3MoarPlKYg")
                .when()
                .post("http://localhost:9090/graphql/");

        assertEquals(400, res.statusCode());
        assertTrue(res.asString().contains("error"));

    }

    @Test
    public void testAdminClient() {
        var result = given()
                .contentType(ContentType.JSON)
                .body("{\"query\":\"mutation {\\n  registerClient(\\n    request: {surname: \\\"Иванов\\\", name: \\\"Иван\\\", middlename: \\\"Иванович\\\", login: \\\"ivanpetrgrhuhu\\\", password: \\\"wknc&ekv12\\\", email: \\\"ivanov@mail.ru\\\", phone: \\\"8-916-621-32-94\\\"}\\n  ) {\\n    id\\n    surname\\n    name\\n    type\\n  }\\n}\",\"variables\":null}")
                .when()
                .post("http://localhost:9090/graphql/");

        var res = given()
                .contentType(ContentType.JSON)
                .body("{\"query\":\"mutation {\\n  login(\\n    loginDto: { login: \\\"ivanpetrgrhuhu\\\", password: \\\"wknc&ekv12\\\"}\\n  ) {\\n      surname\\n    name\\n     }\\n}\",\"variables\":null}")
                .when()
                .post("http://localhost:9090/graphql/");

        var resul = given()
                .contentType(ContentType.JSON)
                .body("{\"query\":\"query {\\n  healthCheck\\\"}\\n  ) {\\n }\\n}\",\"variables\":null}")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpdmFucGV0cmdyaHVodSIsImlhdCI6MTY1MDYyODI5NSwiZXhwIjoxNjUwNzE0Njk1fQ.oAsLcz3UOYSAB7m7M9amjU09dwzvQJYtjsz5l-RePFfqbK_4aG1bd3Hdniq6XHVFrjsgKBwuAMo_3MoarPlKYg")
                .when()
                .post("http://localhost:9090/graphql/");

        assertEquals(200, resul.statusCode());

    }

    @Test
    public void testNoAuthorized() {

        var res = given()
                .contentType(ContentType.JSON)
                .body("{\"query\":\"query {\\n  greeting\\n  \\\"}\\n  ) {\\n   }\\n}\",\"variables\":null}")
                .when()
                .header("Authorization", "Bearer eyJh")
                .post("http://localhost:9090/graphql/");

        assertTrue(res.asString().contains("error"));
    }


}
