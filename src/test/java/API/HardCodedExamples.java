package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.bouncycastle.cert.ocsp.Req;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.json.Json;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    String basaeURI = RestAssured.baseURI ="http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
            " eyJpYXQiOjE2NTU1NjE3NTMsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1NTYwNDk1MywidXNlcklkIjoiMzkyMCJ9." +
            "BgHHiDFn_WbArH1Dl4Pa3No9HHGu6cPaxNAqyI67en8";
    static String employee_id;

    @Test
    public void acreateEmployee(){
       RequestSpecification request = given().header("Content-Type", "application/json").
        header("Authorization",token).body("{\n" +
                        "  \"emp_firstname\": \"bay\",\n" +
                        "  \"emp_lastname\": \"smokin\",\n" +
                        "  \"emp_middle_name\": \"joy\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1994-05-17\",\n" +
                        "  \"emp_status\": \"student\",\n" +
                        "  \"emp_job_title\": \"QA Eng\"\n" +
                        "}");

       Response response= request.when().post("/createEmployee.php");
        //response.prettyPeek();
        response.then().assertThat().statusCode(201);

        //Hamcrest matchers
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        response.then().assertThat().body("Employee.emp_firstname",equalTo("bay"));

        //using jsonPath(), to specify the key in the body so that it returns the value against it
         employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
    }
    @Test
    public void bgetCreatedEmployee(){
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json").
                header("Authorization", token).queryParam("employee_id", employee_id);

        Response response = preparedRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        String tempId = response.jsonPath().getString("employee.employee_id");
        System.out.println(tempId);
        Assert.assertEquals(tempId,employee_id);
    }

    @Test
    public void cupdateEmployee(){
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json").
                header("Authorization", token).
                body("{\n" +
                        "        \"employee_id\": \""+ employee_id + "\",\n" +
                        "        \"emp_firstname\": \"edward\",\n" +
                        "        \"emp_lastname\": \"sisi\",\n" +
                        "        \"emp_middle_name\": \"MS1\", \n" +
                        "        \"emp_gender\": \"M\",       \n" +
                        "        \"emp_birthday\": \"1995-06-12\", \n" +
                        "        \"emp_status\": \"confirmed\",       \n" +
                        "        \"emp_job_title\": \"Manager\"        \n" +
                        "    }");

        Response response =preparedRequest.when().put("/updateEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void dGetUpdatedEmployee(){
        RequestSpecification request = given().header("Content-Type", "application/json").
                header("Authorization", token).queryParam("employee_id", employee_id);

        Response response = request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void eGetAllEmployees(){
        RequestSpecification request = given().header("Authorization", token).
        header("Content-Type", "application/json");
        Response response = request.when().get("/getAllEmployees.php");

        //it returns String of response
        String allEmployees = response.prettyPrint();

        //jsonPath() vs jsonPath
        //jsonPath is a class that contain method for converting the value into json object
        //jsonPath() is a merhod belongs to jsonPath class

        //creating obfect of jsonPath class
        JsonPath js = new JsonPath(allEmployees);

        //retrieving the tital number of employees
        int count = js.getInt("Employees.size()");
        System.out.println(count);

        //to print only employee id of all the employees
        for (int i = 0; i < count; i++) {
            String empID = js.getString("Employees[" + i + "].employee_id");
            System.out.println(empID);
        }
    }



}
