
package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdventureDetailsPage {

    RemoteWebDriver driver;

    @FindBy (xpath = "(//input[@class='form-control'])[1]")
    WebElement name_Text_Box;

    @FindBy (xpath = "(//input[@class='form-control'])[2]")
    WebElement date_box;
    
    @FindBy (xpath = "(//input[@class='form-control'])[3]")
    WebElement noOfPersons_Text_Box;

    @FindBy (xpath = "//button[@class='reserve-button']")
    WebElement reserve_Button;

    @FindBy (id = "reserved-banner")
    WebElement successful_message;


    public AdventureDetailsPage(RemoteWebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    public void BookAdventure(String name, String date, String noOfPersons){
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElements(name_Text_Box));

        name_Text_Box.clear();
        // name_Text_Box.sendKeys(name);
        SeleniumWrapper.sendKeys(name_Text_Box, name);
        selectDate(date);
        noOfPersons_Text_Box.clear();
        // noOfPersons_Text_Box.sendKeys(noOfPersons);
        SeleniumWrapper.sendKeys(noOfPersons_Text_Box, noOfPersons);
        // reserve_Button.click();
        SeleniumWrapper.click(reserve_Button, driver);
        //WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(successful_message));
        

    }

    public void selectDate(String date){
        // date_box.click();
        // date_box.sendKeys(date);
        SeleniumWrapper.click(date_box, driver);
        SeleniumWrapper.sendKeys(date_box, date);
    }

    public Boolean isBookingSuccessful(){
        Boolean status= false;
        try{
            status = successful_message.isDisplayed();
            System.out.println(status);
            return status;
        } catch (Exception e) {
            return status;
        }
    }

}
