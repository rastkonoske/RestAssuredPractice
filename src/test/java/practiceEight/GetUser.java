package practiceEight;

import static io.restassured.RestAssured.given;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetUser {

    @Test
    void test_getUser(ITestContext context)
    {
        //int id =(Integer) context.getAttribute("user_id");   // this should come from createUser request
        int id =(Integer) context.getSuite().getAttribute("user_id");

        String bearerToken="1a2b5c16b0eca03b4c026c2e818a67d7eab3df43c00a963b2b4062c07ca0ff1c";

        given()
                .headers("Authorization","Bearer "+bearerToken)
                .pathParam("id",id)
                .when()
                .get("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(200)
                .log().all();


    }
}
