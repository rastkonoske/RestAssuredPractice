package practiceEight;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DeleteUser {

    @Test
    void test_deleteUser(ITestContext context)
    {

        String bearerToken="1a2b5c16b0eca03b4c026c2e818a67d7eab3df43c00a963b2b4062c07ca0ff1c";

        //int id =(Integer) context.getAttribute("user_id");   // this should come from createUser request

        int id =(Integer) context.getSuite().getAttribute("user_id");

        given()
                .headers("Authorization","Bearer "+bearerToken)
                .pathParam("id",id)
                .when()
                .delete("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(204)
                .log().all();

    }
}
