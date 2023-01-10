package practiceSix;

import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import practiceTwo.Pojo_PostRequest;

//Pojo  ---- Serilize-->  JSON Object -- de-serilize----->POjo

public class SerializationDeserialization {

    // Pojo -----> JSON    ( Serilization)
    @Test
    void convertPojo2Json() throws JsonProcessingException
    {
        //created java object using pojo class
        Student stuPojo=new Student();    //pojo

        stuPojo.setName("Scott");
        stuPojo.setLocation("France");
        stuPojo.setPhone("123456");
        String coursesArr[]= {"C","C++"};
        stuPojo.setCourses(coursesArr);

        //convert java object ---> json object (serilization)
        ObjectMapper objMapper=new ObjectMapper();

        String jsonData=objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stuPojo);

        System.out.println(jsonData);

    }

    // Json -----> Pojo    ( De-Serilization)
    @Test
    void convertJson2Pojo() throws JsonProcessingException
    {

        String jsonData="{\r\n"
                + "  \"name\" : \"Scott\",\r\n"
                + "  \"location\" : \"France\",\r\n"
                + "  \"phone\" : \"123456\",\r\n"
                + "  \"courses\" : [ \"C\", \"C++\" ]\r\n"
                + "}";

        //convert json data---> Pojo object

        ObjectMapper objMapper=new ObjectMapper();

        Student stuPojo=objMapper.readValue(jsonData,Student.class); //convert json to pojo

        System.out.println("Name: "+stuPojo.getName());
        System.out.println("Location: "+stuPojo.getLocation());
        System.out.println("Phone: "+stuPojo.getPhone());
        System.out.println("Course 1: "+stuPojo.getCourses()[0]);
        System.out.println("Course 2: "+stuPojo.getCourses()[1]);

    }
}
