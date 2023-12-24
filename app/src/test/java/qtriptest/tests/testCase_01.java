package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class testCase_01 {  

    static RemoteWebDriver driver;
    ExtentTest test;
    ExtentReports report;
    
    @BeforeTest(alwaysRun = true)
    public void setup() {
       DriverSingleton driverSingleton =  DriverSingleton.getInstanceOfSingletonBrowserClass();
       driver = driverSingleton.getDriver();
       report = ReportSingleton.getReport();
       test = report.startTest("Qtrip Login-Logout");
     
    }
    @Test(description ="Verify user registration - login - logout to QTrip",priority = 1,groups="Login Flow",dataProvider="qtripDataProvider",dataProviderClass = DP.class)
    public void TestCase01(String username, String password) throws InterruptedException, MalformedURLException{
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.navigateToRegisterPage();
        Thread.sleep(2000);
        registerPage.registerUser(username, password, password, true);
       
        LoginPage login = new LoginPage(driver);
        login.navigateToLoginPage();
        login.performLogin(registerPage.lastGeneratedUserName, password);  
        
        homePage.logOutUser();
    }

    @AfterTest()
    public void tearDown() {
        System.out.println("quit()");
        driver.quit();
        //  - End the test
        report.endTest(test);
        //  - Write the test to filesystem
        report.flush();
    }
    
}
