package practiceSix;

import org.testng.annotations.Test;
import io.restassured.module.jsv.JsonSchemaValidator;
import static io.restassured.RestAssured.*;

//json ---> jsonschema converter
//  https://jsonformatter.org/json-to-jsonschema

public class JSONSchemaValidation {

    @Test
    void jsonSchemavalidation()
    {
        given()

                .when()
                .get("http://localhost:3000/store")
                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJsonSchema.json"));

    }
}
