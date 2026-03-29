package utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getExtent() {

        if (extent == null) {

            // Create reports folder (IMPORTANT for Jenkins)
            String reportPath = System.getProperty("user.dir") + "/reports";
            File reportDir = new File(reportPath);

            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }

            ExtentSparkReporter reporter =
                    new ExtentSparkReporter(reportPath + "/ExtentReport.html");

            reporter.config().setDocumentTitle("Mini Automation Project");
            reporter.config().setReportName("Login Logout Test Report");
            reporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            extent.setSystemInfo("Project", "Practice Test Automation");
            extent.setSystemInfo("Module", "Login Logout");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }

        return extent;
    }
}