package practiceSeven;

import static io.restassured.RestAssured.given;
import org.json.JSONArray;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJSONArray_EmployeesAPI {

    @Test
    void testJsonResponseBodyData()
    {
        Response res=
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get("http://localhost:3000/employees");


        //using JSONArray class
        JSONArray jarr=new JSONArray(res.asString());


        //print all emails of employees
        for(int i=0; i<jarr.length();i++)
        {
            String empemails=jarr.getJSONObject(i).get("email").toString();
            System.out.println(empemails);
        }

    }
}
