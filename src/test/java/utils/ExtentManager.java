package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

  //  private static ExtentReports extent;

    public static ExtentReports getExtent() {

    	ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/ExtentReport.html");
    	reporter.config().setDocumentTitle("Mini Automation Project");
    	reporter.config().setReportName("Login Logout Test Report");
    	reporter.config().setTheme(Theme.STANDARD);

            ExtentReports extent = new ExtentReports();
            extent.attachReporter(reporter);

            extent.setSystemInfo("Project", "Practice Test Automation");
            extent.setSystemInfo("Module", "Login Logout");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        
        return extent;
    }
}
