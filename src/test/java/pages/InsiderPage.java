package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class InsiderPage {
    public InsiderPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(id="wt-cli-accept-all-btn")
    public WebElement cookieButton;

    @FindBy(xpath = "//span[normalize-space()='More']")
    public WebElement moreButton;

    @FindBy (xpath = "//h5[text()='Careers']")
    public WebElement careersButton;

    @FindBy (xpath = "//h3[normalize-space()='Find your calling']")
    public WebElement teamsWebElement;

    @FindBy(xpath = "//h3[@class='category-title-media ml-0']")
    public WebElement locationsWebelement;

    @FindBy (xpath = "//a[text()='See all teams']")
    public WebElement seeAllTeamsButton;

    @FindBy (xpath = "//h3[text()='Quality Assurance']")
    public WebElement quantityAssuranceButton;

    @FindBy (xpath = "//a[text()='See all QA jobs']")
    public WebElement seeAllQaJobsButton;

    @FindBy(id = "filter-by-location")
    public WebElement locationSelect;


    @FindBy(xpath = "(//a[@class='btn btn-navy rounded pt-2 pr-5 pb-2 pl-5'])[4]")
    public WebElement applyButton;


    @FindBy(id = "filter-by-department")
    public WebElement departmentSelect;


    @FindBy(xpath = "//selection[@id=career-our-location]")
    public WebElement locationSlade;


}
