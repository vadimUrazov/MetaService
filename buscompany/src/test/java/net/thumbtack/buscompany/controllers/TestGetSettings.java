package net.thumbtack.buscompany.controllers;


import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.jayway.restassured.http.ContentType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


@Disabled
public class TestGetSettings extends AbstractControllerTest {


  @Test
  public void testGetSettings() throws Exception {

    var r = given()
        .contentType(ContentType.JSON)
        .body(
            "{\"query\":\"query{\\n  getSettings{\\n    port\\n  }\\n}\\n\\n\",\"variables\":null}")
        .when()
        .post("http://localhost:9090/graphql/")
        .andReturn().then().assertThat()
        .body("data.getSettings.port", equalTo(9090));


  }
}

