package net.thumbtack.traincompany.controllers;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import net.thumbtack.traincompany.dao.UserDao;
import net.thumbtack.traincompany.dto.request.RegisterClientDtoRequest;
import net.thumbtack.traincompany.service.AdminService;
import net.thumbtack.traincompany.service.ClientService;
import net.thumbtack.traincompany.service.SessionService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
public class TestUserValidator extends AbstractControllerTest {


    @SpyBean
    SessionService sessionService;

    @SpyBean
    ClientService service;

    @SpyBean
    AdminService adminService;

    @Autowired
    @Qualifier("DaoUser")
    UserDao userDao;

    @Test
    public void testRegisterClient() throws Exception {
        RegisterClientDtoRequest request = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv", "12s223dfghj");
        var result = given()
                .contentType(ContentType.JSON)
                .body("{\"query\":\"mutation {\\n  registerClient(\\n    request: {surname: \\\"Иванов\\\", name: \\\"Иван\\\", middlename: \\\"Иванович\\\", login: \\\"iivanovIvanv\\\", password: \\\"12s223dfghj\\\", email: \\\"ivanov@mail.ru\\\", phone: \\\"8-916-621-32-94\\\"}\\n  ) {\\n    id\\n    surname\\n    name\\n    userType\\n  }\\n}\",\"variables\":null}")
                .when()
                .post("http://localhost:9090/graphql/");
        assertEquals(result.andReturn().getStatusCode(), 200);

    }

    @Test
    public void testRegisterClientFailEmail() throws Exception {
        RegisterClientDtoRequest request = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanomail.ru", "8-916-621-32-64", "ivanovIvanv", "12s223dfghj");
        var result = given()
                .contentType(ContentType.JSON)
                .body("{\"query\":\"mutation {\\n  registerClient(\\n    request: {surname: \\\"Иванов\\\", name: \\\"Иван\\\", middlename: \\\"Иванович\\\", login: \\\"iivanovIvanv\\\", password: \\\"12s223dfghj\\\", email: \\\"ivanmail.ru\\\", phone: \\\"8-916-621-32-94\\\"}\\n  ) {\\n    id\\n    surname\\n    name\\n    userType\\n  }\\n}\",\"variables\":null}")
                .when()
                .post("http://localhost:9090/graphql/");
        JsonPath jp = new JsonPath(result.asString());
        String value = jp.get("errors.message").toString();

        assertTrue(value.contains("INTERNAL_ERROR"));

    }

    @Test
    public void testRegisterClientFailPhone() throws Exception {
        RegisterClientDtoRequest request = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanov@mail.ru", "-6cdr6789", "ivanovIvanv", "12s223dfghj");
        var result = given()
                .contentType(ContentType.JSON)
                .body("{\"query\":\"mutation {\\n  registerClient(\\n    request: {surname: \\\"Иванов\\\", name: \\\"Иван\\\", middlename: \\\"Иванович\\\", login: \\\"iivanovIvanv\\\", password: \\\"12s223dfghj\\\", email: \\\"ivanov@mail.ru\\\", phone: \\\"-6cdr6789\\\"}\\n  ) {\\n    id\\n    surname\\n    name\\n    userType\\n  }\\n}\",\"variables\":null}")
                .when()
                .post("http://localhost:9090/graphql/");
        JsonPath jp = new JsonPath(result.asString());
        String value = jp.get("errors.message").toString();

        assertTrue(value.contains("INTERNAL_ERROR"));
    }

    @Test
    public void testRegisterClientFailSurname() throws Exception {
        RegisterClientDtoRequest request = new RegisterClientDtoRequest(0,"", "Иван", "Иванович", "ivanoм@mail.ru", "8-916-621-32-64", "ivanovIvanv", "12s223dfghj");
        var result = given()
                .contentType(ContentType.JSON)
                .body("{\"query\":\"mutation {\\n  registerClient(\\n    request: {surname: \\\"\\\", name: \\\"Иван\\\", middlename: \\\"Иванович\\\", login: \\\"iivanovIvanv\\\", password: \\\"12s223dfghj\\\", email: \\\"ivanov@mail.ru\\\", phone: \\\"8-916-621-32-94\\\"}\\n  ) {\\n    id\\n    surname\\n    name\\n    userType\\n  }\\n}\",\"variables\":null}")
                .when()
                .post("http://localhost:9090/graphql/");

        JsonPath jp = new JsonPath(result.asString());
        String value = jp.get("errors.message").toString();

        assertTrue(value.contains("INTERNAL_ERROR"));
    }

    @Test
    public void testRegisterClientFailName() throws Exception {
        RegisterClientDtoRequest request = new RegisterClientDtoRequest(0,"Иванов", "", "Иванович", "ivano@mail.ru", "8-916-621-32-64", "ivanovIvanv", "12s223dfghj");
        var result = given()
                .contentType(ContentType.JSON)
                .body("{\"query\":\"mutation {\\n  registerClient(\\n    request: {surname: \\\"Иванов\\\", name: \\\"\\\", middlename: \\\"Иванович\\\", login: \\\"iivanovIvanv\\\", password: \\\"12s223dfghj\\\", email: \\\"ivanov@mail.ru\\\", phone: \\\"8-916-621-32-94\\\"}\\n  ) {\\n    id\\n    surname\\n    name\\n    userType\\n  }\\n}\",\"variables\":null}")
                .when()
                .post("http://localhost:9090/graphql/");
        JsonPath jp = new JsonPath(result.asString());
        String value = jp.get("errors.message").toString();

        assertTrue(value.contains("INTERNAL_ERROR"));

    }

    @Test
    public void testRegisterClientFailLoginSize() throws Exception {
        RegisterClientDtoRequest request = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanov@mail.ru", "8-916-621-32-64", "ivan", "12s223dfghj");
        var result = given()
                .contentType(ContentType.JSON)
                .body("{\"query\":\"mutation {\\n  registerClient(\\n    request: {surname: \\\"Иванов\\\", name: \\\"Иван\\\", middlename: \\\"Иванович\\\", login: \\\"ivan\\\", password: \\\"12s223dfghj\\\", email: \\\"ivanov@mail.ru\\\", phone: \\\"8-916-621-32-94\\\"}\\n  ) {\\n    id\\n    surname\\n    name\\n    userType\\n  }\\n}\",\"variables\":null}")
                .when()
                .post("http://localhost:9090/graphql/");

        JsonPath jp = new JsonPath(result.asString());
        String value = jp.get("errors.message").toString();

        assertTrue(value.contains("INTERNAL_ERROR"));
    }

    @Test
    public void testRegisterClientFailLogin() throws Exception {
        RegisterClientDtoRequest request = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanov@mail.ru", "8-916-621-32-64", "", "12s223dfghj");
        var result = given()
                .contentType(ContentType.JSON)
                .body("{\"query\":\"mutation {\\n  registerClient(\\n    request: {surname: \\\"Иванов\\\", name: \\\"Иван\\\", middlename: \\\"Иванович\\\", login: \\\"\\\", password: \\\"12s223dfghj\\\", email: \\\"ivanov@mail.ru\\\", phone: \\\"8-916-621-32-94\\\"}\\n  ) {\\n    id\\n    surname\\n    name\\n    userType\\n  }\\n}\",\"variables\":null}")
                .when()
                .post("http://localhost:9090/graphql/");


        JsonPath jp = new JsonPath(result.asString());
        String value = jp.get("errors.message").toString();

        assertTrue(value.contains("INTERNAL_ERROR"));
    }

    @Test
    public void testRegisterClientFailPassword() throws Exception {
        RegisterClientDtoRequest request = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv", null);
        var result = given()
                .contentType(ContentType.JSON)
                .body("{\"query\":\"mutation {\\n  registerClient(\\n    request: {surname: \\\"Иванов\\\", name: \\\"Иван\\\", middlename: \\\"Иванович\\\", login: \\\"iivanovIvanv\\\", password: \\\"null\\\", email: \\\"ivanov@mail.ru\\\", phone: \\\"8-916-621-32-94\\\"}\\n  ) {\\n    id\\n    surname\\n    name\\n    userType\\n  }\\n}\",\"variables\":null}")
                .when()
                .post("http://localhost:9090/graphql/");

        JsonPath jp = new JsonPath(result.asString());
        String value = jp.get("errors.message").toString();

        assertTrue(value.contains("INTERNAL_ERROR"));
    }

    @Test
    public void testRegisterClientFailPasswordSize() throws Exception {
        RegisterClientDtoRequest request = new RegisterClientDtoRequest(0,"Иванов", "Иван", "Иванович", "ivanov@mail.ru", "8-916-621-32-64", "ivanovIvanv", "1234");
        var result = given()
                .contentType(ContentType.JSON)
                .body("{\"query\":\"mutation {\\n  registerClient(\\n    request: {surname: \\\"Иванов\\\", name: \\\"Иван\\\", middlename: \\\"Иванович\\\", login: \\\"iivanovIvanv\\\", password: \\\"1234\\\", email: \\\"ivanov@mail.ru\\\", phone: \\\"8-916-621-32-94\\\"}\\n  ) {\\n    id\\n    surname\\n    name\\n    userType\\n  }\\n}\",\"variables\":null}")
                .when()
                .post("http://localhost:9090/graphql/");


        JsonPath jp = new JsonPath(result.asString());
        String value = jp.get("errors.message").toString();

        assertTrue(value.contains("INTERNAL_ERROR"));
    }
}
