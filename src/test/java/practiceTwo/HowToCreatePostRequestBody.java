package practiceTwo;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;


/*
Different ways to create POST request body
1) Post request body using Hashmap
2) Post request body creation using Org.JSON
3) Post request body creation using POJO class
4) Post request using external json file data
*/

public class HowToCreatePostRequestBody {
    //1) Post request body using Hashmap

    //@Test(priority=1)
    void testPostUsingHashMap()
    {

        HashMap data=new HashMap();

        data.put("name","Scott");
        data.put("location","France");
        data.put("phone","123456");

        String courseArr[]= {"C","C++"};
        data.put("courses", courseArr);


        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name",equalTo("Scott"))
                .body("location", equalTo("France"))
                .body("phone", equalTo("123456"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("Content-Type","application/json; charset=utf-8") //glavno pitanje: kako sva ova polja iz tela dinamicki uglaviti?
                .log().all();

    }


    //2) Post request body using org.json library

    //@Test(priority=1)
    void testPostUsingJsonLibrary()
    {
        JSONObject data=new JSONObject(); //ovo je glupo, isti kurac ko i HashMap, a vise jebendisanja

        data.put("name","Scott");
        data.put("location","France");
        data.put("phone","123456");

        String coursesArr[]= {"C","C++"};
        data.put("courses", coursesArr);

        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name",equalTo("Scott"))
                .body("location", equalTo("France"))
                .body("phone", equalTo("123456"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();

    }



    //3) Post request body using POJO Class

    //@Test(priority=1)
    void testPostUsingPOJO()
    {
        Pojo_PostRequest data=new Pojo_PostRequest(); //ovo dobro za dinamiku ako ima vise ocina podataka

        data.setName("Scott");
        data.setLocation("France");
        data.setPhone("123456");
        String coursesArr[]= {"C","C++"};
        data.setCourses(coursesArr);


        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name",equalTo("Scott"))
                .body("location", equalTo("France"))
                .body("phone", equalTo("123456"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();

    }


    //4) Post request body using External JSON File

    @Test(priority=1)
    void testPostUsingExternalJsonFile() throws FileNotFoundException
    {

        File f=new File("D:\\Kursevi\\REST Assured\\Vezbe\\src\\test\\resources\\body.json");

        FileReader fr=new FileReader(f);

        JSONTokener jt=new JSONTokener(fr);

        JSONObject data=new JSONObject(jt);


        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name",equalTo("Scott"))
                .body("location", equalTo("France"))
                .body("phone", equalTo("123456"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();

    }



    //deleting student record
    @Test(priority=2)
    void testDelete()
    {
        given()

                .when()
                .delete("http://localhost:3000/students/4")

                .then()
                .statusCode(200);

    }
}
