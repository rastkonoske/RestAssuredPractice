package practiceSix;


import org.testng.annotations.Test;
import io.restassured.matcher.RestAssuredMatchers;
import static io.restassured.RestAssured.*;

public class XMLSchemaValidator {

    @Test
    void xmlSchemavalidation()
    {
        given()

                .when()
                .get("http://restapi.adequateshop.com/api/Traveler")
                .then()
                .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("traveler.xsd"));

    }
}
