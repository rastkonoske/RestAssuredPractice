package practiceThree;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class PathAndQueryParameters {

    //https://reqres.in/api/users?page=2&id=5 ovo je api koji treba testirati i cije elemente uneti u test

    @Test
    void testQueryAndPathParameters()
    {
        given()
                .pathParam("mypath","users")    // path parameters - key sam biram ime, jer je on varijabla
                .queryParam("page",2)  // query parameter
                .queryParam("id",5)  // query parameters

                .when()
                .get("https://reqres.in/api/{mypath}")      //unutar {} isto kao u postmanu ubacujemo key od varijable

                .then()
                .statusCode(200)
                .log().all();
    }
}
