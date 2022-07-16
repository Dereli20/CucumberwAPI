package utils;

public class APIPayloadConstans {

    public static String createEmployeePayload(){
        String createEmployee = "{\n" +
                "  \"emp_firstname\": \"bay\",\n" +
                "  \"emp_lastname\": \"smokin\",\n" +
                "  \"emp_middle_name\": \"joy\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"1994-05-17\",\n" +
                "  \"emp_status\": \"student\",\n" +
                "  \"emp_job_title\": \"QA Eng\"\n" +
                "} ";
        return createEmployee;
    }


}
