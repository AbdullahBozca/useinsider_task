package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InsiderPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InsiderTest extends TestBaseRapor {
    /*
    ● Test Automation
    1. Visit https://useinsider.com/ and check Insider home page is opened or not
    2. Select “More” menu in navigation bar, select “Careers” and check Career page, its
    Locations, Teams and Life at Insider blocks are opened or not
    3. Click “See All Teams”, select Quality Assurance, click “See all QA jobs”, filter jobs by
    Location - Istanbul, Turkey and department - Quality Assurance, check presence of
    jobs list
    4. Check that all jobs’ Position contains “Quality Assurance”, Department contains
    “Quality Assurance”, Location contains “Istanbul, Turkey” and “Apply Now” button
    5. Click “Apply Now” button and check that this action redirects us to Lever Application
    form page
     */

    @Test
    public void test() throws InterruptedException {
        extentTest=extentReports.createTest("Insider UI Test","Task01");
        Driver.getDriver().get(ConfigReader.getProperty("insiderURL"));
        extentTest.info("https://useinsider.com/ sayfasina gidildi");
        InsiderPage insiderPage = new InsiderPage();
        insiderPage.cookieButton.click();
        insiderPage.moreButton.click();
        insiderPage.careersButton.click();
        assertTrue(insiderPage.teamsWebElement.isDisplayed());
        extentTest.pass("Teams Webelementi goruldu");
        assertTrue(insiderPage.locationsWebelement.isDisplayed());
        extentTest.pass("Locations Webelementi goruldu");


        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript("arguments[0].click()", insiderPage.seeAllTeamsButton);
        jse.executeScript("arguments[0].click()", insiderPage.quantityAssuranceButton);
        jse.executeScript("arguments[0].click()", insiderPage.seeAllQaJobsButton);



        Select locationsSelect = new Select(insiderPage.locationSelect);
        locationsSelect.selectByVisibleText("Istanbul, Turkey");
        extentTest.info("Istanbul, Turkey secildi");
        List<WebElement> locationsSelectElement=locationsSelect.getOptions();
        int locationControl=0;
        for (WebElement each:locationsSelectElement) {
            if (each.getText().equals("Istanbul, Turkey"))
                locationControl=1;
        }
        assertEquals(locationControl,1,"Istanbul, Turkey  degeri yok");
        extentTest.pass("Location dorp down da Istanbul, Turkey var");



        Select departmentSelect=new Select(insiderPage.departmentSelect);
        List<WebElement> departmentSelectElement=departmentSelect.getOptions();
        int departmentControl=0;
        for (WebElement each:departmentSelectElement) {
            if (each.getText().equals("Quality Assurance"))
                departmentControl=1;
        }
        assertEquals(departmentControl,1,"Quality Assurance degeri yok");
        extentTest.pass("Department dorp down da Quality Assurance degeri yok var");
        Actions actions=new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_DOWN).sendKeys(Keys.PAGE_DOWN).perform();
        ReusableMethods.waitFor(1);
        actions.moveToElement(insiderPage.applyButton).perform();
        assertTrue(insiderPage.applyButton.isDisplayed());
        extentTest.pass("Apply butonu goruldu");
        jse.executeScript("arguments[0].click()", insiderPage.applyButton);

        Set<String> windowHandles = Driver.getDriver().getWindowHandles();
        String newWindowHandles = "";
        for (String each : windowHandles) {
            if (each != Driver.getDriver().getWindowHandle())
                newWindowHandles = each;
        }
        Driver.getDriver().switchTo().window(newWindowHandles);
        assertTrue(Driver.getDriver().getCurrentUrl().contains("https://jobs.lever.co/useinsider"));
        extentTest.pass("Lever Application sayfasina gecildi");

    }


}
