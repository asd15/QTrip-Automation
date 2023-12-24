package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
    
    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login/";

    @FindBy(id = "floatingInput")
    WebElement userName_Text_Box;

    @FindBy(id = "floatingPassword")
    WebElement password_Text_Box;
    
    @FindBy(xpath = "//h1")
    WebElement loginPage_Text;

    @FindBy(className = "btn-login")
    WebElement login_Button;


    public LoginPage(RemoteWebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    public void navigateToLoginPage(){
        if(!driver.getCurrentUrl().equals(this.url)){
            driver.get(this.url); 
        }
    }

    public Boolean performLogin(String username, String password) throws InterruptedException{

        // userName_Text_Box.sendKeys(username);
        // password_Text_Box.sendKeys(password);
        SeleniumWrapper.sendKeys(userName_Text_Box, username);
        SeleniumWrapper.sendKeys(password_Text_Box, password);
        // login_Button.click();
        SeleniumWrapper.click(login_Button, driver);

        Thread.sleep(6000);
        return this.VerifyUserLoggedIn();
    }


    public Boolean VerifyUserLoggedIn(){
        
        try{
         return loginPage_Text.isDisplayed();
        } catch(Exception e){
            return false;
        }
    }
}
