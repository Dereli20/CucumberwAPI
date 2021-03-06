package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.List;

public class DashboardSteps extends CommonMethods {
    @Then("user verifies all the dashboard tabs")
    public void user_verifies_all_the_dashboard_tabs(DataTable dataTable) {
      //this data is coming from feature file
        List<String> expectedTabs = dataTable.asList();

        List<String> actualTabs = new ArrayList<>();

        for (WebElement ele:dash.dashboardTabs) {
            actualTabs.add(ele.getText());
        }
        System.out.println(expectedTabs);
        System.out.println(actualTabs);

        //Assert.assertEquals();
      //if assertions is passed it will not give you any information and will execute our code
      //if assertions is
        Assert.assertTrue(expectedTabs.equals(actualTabs));

    }
}
