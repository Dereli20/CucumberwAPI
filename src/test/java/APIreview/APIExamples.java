package APIreview;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import groovyjarjarantlr.Token;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class APIExamples {

    //set the base uri
    String basaeURI = RestAssured.baseURI ="http://hrm.syntaxtechs.net/syntaxapi/api";
    String token= "Bearer  eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTY0NzIyMzUsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1NjUxNTQzNSwidXNlcklkIjoiNDAwNiJ9.HWeWZ9b2DvR0CLJmaDyWiJaHFzZsUnX2PhODo4aI0kI";

    //create an employee in SyntaxHRMS
    @Test
    public void createAnEmployee(){

       RequestSpecification request = given().header("Content-type","application/json").header("Autorization", token)
               .body("{\n" +
                       "        \"employee_id\": \"39117A\",\n" +
                       "        \"emp_firstname\": \"gizo\",\n" +
                       "        \"emp_gender\": \"F\",\n" +
                       "        \"emp_middle_name\": \"joy\",\n" +
                       "        \"emp_lastname\": \"dere\",\n" +
                       "        \"emp_birthday\": \"2000-01-01\",\n" +
                       "        \"emp_job_title\": \"Banking\",\n" +
                       "        \"emp_status\": \"student\"\n" +
                       "    }");

           Response response = request.when().post("/createEmployee.php");
           response.prettyPrint();

    //understanding GSON to decode json object
        JsonElement json_element = new JsonParser().parse(response.asString());

        System.out.println(json_element);

        JsonObject json_data = json_element.getAsJsonObject();

        System.out.println(json_data);

    }

}
