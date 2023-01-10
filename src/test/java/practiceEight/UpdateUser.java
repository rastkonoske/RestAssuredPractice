package practiceEight;

import static io.restassured.RestAssured.given;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

public class UpdateUser {

    @Test
    void test_updateUser(ITestContext context)
    {
        Faker faker=new Faker();

        JSONObject data=new JSONObject();

        data.put("name",faker.name().fullName());
        data.put("gender", "Male");
        data.put("email",faker.internet().emailAddress());
        data.put("status", "active");


        String bearerToken="1a2b5c16b0eca03b4c026c2e818a67d7eab3df43c00a963b2b4062c07ca0ff1c";

        //int id =(Integer) context.getAttribute("user_id");   // this should come from createUser request
        int id =(Integer) context.getSuite().getAttribute("user_id");


        given()
                .headers("Authorization","Bearer "+bearerToken)
                .contentType("application/json")
                .body(data.toString())
                .pathParam("id",id)

                .when()
                .put("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(200)
                .log().all();

    }
}
