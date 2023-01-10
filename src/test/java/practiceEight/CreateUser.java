package practiceEight;

import static io.restassured.RestAssured.given;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

public class CreateUser {

    @Test
    void tes_createUser(ITestContext context)
    {

        Faker faker=new Faker();

        JSONObject data=new JSONObject();

        data.put("name",faker.name().fullName());
        data.put("gender", "Male");
        data.put("email",faker.internet().emailAddress());
        data.put("status", "inactive");


        String bearerToken="1a2b5c16b0eca03b4c026c2e818a67d7eab3df43c00a963b2b4062c07ca0ff1c";

        int id= given()
                .headers("Authorization","Bearer "+bearerToken)
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("https://gorest.co.in/public/v2/users")
                .jsonPath().getInt("id");

        System.out.println("Generated id is:"+id);

        //context.setAttribute("user_id", id);
        context.getSuite().setAttribute("user_id", id); // sa .getSuite() moze da se testira i kao odvojeni testovi, a ne samo klase unutar jednog testa
    }

}
