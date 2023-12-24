package qtriptest;

import java.io.File;
import com.relevantcodes.extentreports.ExtentReports;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ReportSingleton {
    static String lastGeneratedUsername_;
    RemoteWebDriver driver; 
    private static ExtentReports report = null;
    private ReportSingleton(){}

        public static ExtentReports  getReport(){
        if(report == null){
        String path = System.getProperty("user.dir")
                    + "/src/test/java/qtriptest/ExtentReportResults.html";
        report = new ExtentReports(path, true);

        report.loadConfig(new File(System.getProperty("user.dir") + "/extent_customization_configs.xml"));
        }
        return report;
    }
}