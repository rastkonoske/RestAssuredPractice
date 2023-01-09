package practiceFive;

import static io.restassured.RestAssured.*;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse {

    @Test(priority = 1)
    void testXMLResponse()
    {
        //Approach1

		/* given()

		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
			.statusCode(200)
			.header("Content-Type","application/xml; charset=utf-8")
			.body("TravelerinformationResponse.page", equalTo("1"))
			.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));
		*/

        //Approach2

        Response res=
                given()

                        .when()
                        .get("http://restapi.adequateshop.com/api/Traveler?page=1");

        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.header("Content-Type"),"application/xml; charset=utf-8");

        String pageNo=res.xmlPath().get("TravelerinformationResponse.page").toString();
        Assert.assertEquals(pageNo, "1");

        String travelName=res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
        Assert.assertEquals(travelName, "Developer");

    }

    @Test (priority = 2)
    void testXMLResponseBody()
    {
        Response res=
                given()

                        .when()
                        .get("http://restapi.adequateshop.com/api/Traveler?page=1");

        XmlPath xmlobj=new XmlPath(res.asString()); //asString je metod koji se koristi kad ceo objekat treba prebaciti u string, dok se toString koristi za poseban podatak

        //Verify total number of travellers
        List<String> travellers=xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");
        Assert.assertEquals(travellers.size(),10);

        //verify traveller name is present in response
        List<String> traveller_names=xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");

        boolean status=false;
        for(String travellerName:traveller_names)
        {
            if(travellerName.equals("Developer"))
            {
                status=true;
                break;
            }
        }

        Assert.assertEquals(status,true);

    }
}
