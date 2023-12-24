package qtriptest.tests;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebElement;
import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.util.List;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class testCase_03 {
    static RemoteWebDriver driver;
    ExtentTest test;
    ExtentReports report;
     List<WebElement> reservationId;

    @BeforeSuite(alwaysRun= true)
    public void setup() {
        DriverSingleton driverSingleton =  DriverSingleton.getInstanceOfSingletonBrowserClass();
        driver = driverSingleton.getDriver();
        report = ReportSingleton.getReport();
        test = report.startTest("Qtrip Booking and Cancellation");
     }

     @Test(description ="verify booking and cancellation flow",priority = 3,groups="Booking and Cancellation Flow",dataProvider="qtripDataProvider",dataProviderClass = DP.class)
     public void TestCase03(String registerUser, String password, 
     String searchCity ,String adventure, String name, String date, 
     String noOfPersons)throws InterruptedException, MalformedURLException{
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();

        RegisterPage register = new RegisterPage(driver);
        register.navigateToRegisterPage();
        register.registerUser(registerUser, password, password, true);

        LoginPage login = new LoginPage(driver);
        login.navigateToLoginPage();
        login.performLogin(register.lastGeneratedUserName, password);

        homePage.searchCity(searchCity);
        homePage.isNoCityFound();
        Thread.sleep(2000);

        homePage.searchCity(searchCity);
        homePage.assertAutoCompleteText(searchCity);
        Thread.sleep(2000);
        homePage.selectCity(searchCity);
        Thread.sleep(2000);

        AdventurePage adventurePage = new AdventurePage(driver);
        adventurePage.selectAdventure(adventure);

        AdventureDetailsPage adventureDetailsPage = new AdventureDetailsPage(driver);
        adventureDetailsPage.BookAdventure(name, date, noOfPersons);
        adventureDetailsPage.isBookingSuccessful();

        HistoryPage historyPage = new HistoryPage(driver);
        reservationId = historyPage.noOfReservation();
        String transId = reservationId.get(reservationId.size() - 1).getText();
        historyPage.cancelReservation(transId);
        Thread.sleep(1000);
        homePage.logOutUser();
       
     }
     @AfterTest()
     public void tearDown() {
        driver.quit();
        //  - End the test
        report.endTest(test);
        //  - Write the test to filesystem
        report.flush();
    }
}
