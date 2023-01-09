package practiceThree;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class Cookies {

    @Test(priority=1)
    void testCookies()
    {

        given()

                .when()
                .get("https://www.google.com/")

                .then()
                .cookie("AEC","ARSKqsLY1Z1-XI5slO-uwvF6cJJPxw4oDGb1GFxRs5S4AdJtIkIr3WhKlw") //ovo uvek pada jer se vrednost menja sa svakim zahtevom
                .log().all();
    }


    @Test(priority=2)
    void getCookiesInfo()
    {

        Response res=given() // ceo odgovor staviti u varijablu res; posle dodatnim naredbama proveriti

                .when()
                .get("https://www.google.com/");


        //get single cookie info
        //String cookie_value=res.getCookie("AEC");
        //System.out.println("Value of cookie is====>"+cookie_value);


        // get all cookies info
        Map<String,String> cookies_values=res.getCookies();

        //System.out.println(cookies_values.keySet());

        for(String k:cookies_values.keySet())
        {
            String cookie_value=res.getCookie(k);
            System.out.println(k+"        "+cookie_value);
        }


    }
}
