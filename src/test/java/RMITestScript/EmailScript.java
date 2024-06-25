package RMITestScript;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Generic.Auto_Constant;
import Generic.Email_BaseTest;
import Generic.Excel;
import POM_Classes.EmailPage;

public class EmailScript extends Email_BaseTest{

    WebDriver driver;
    public Properties prop;
    public static Logger log;
    
    
    @Test(priority=4)
//    @BeforeTest
    public void setup() throws IOException {
    		
    		String path = System.getProperty("user.dir") + "//src//test//resources//ConfigFile//Config.properties";

    		FileInputStream fin = new FileInputStream(path);
    		prop = new Properties();
    		prop.load(fin);

    		String strBrowser = prop.getProperty("browser");

    		if (strBrowser.equalsIgnoreCase("chrome")) {
    			driver = new ChromeDriver();
    		} else if (strBrowser.equalsIgnoreCase("edge")) {
    			driver = new EdgeDriver();
    		}

    		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    		driver.manage().deleteAllCookies();
    		driver.manage().window().maximize();

    		driver.get(prop.getProperty("googleUrl"));
    	}

    
//       @Test(priority=4)
       public void EmailVer() throws IOException, InterruptedException {
    	Thread.sleep(3000);
     	String email = Excel.Testdata(Path, "Sheet2", 5, 1);
		String pass = Excel.Testdata(Path, "Sheet2", 6, 1);
		String emailSub1 = Excel.Testdata(Path, "Sheet2", 8, 1);
		String emailSub2 = Excel.Testdata(Path, "Sheet2", 9, 1);
     	 
        EmailPage EP = new EmailPage(driver);
        EP.login(email, pass);
        EP.readEmail(emailSub1);
        EP.readEmailandgetbody(emailSub2);
        
        String emailBody = "Username: exampleUser\nPassword: secretPassword";

        // Extract username and password from the email body
        String username = extractUsername(emailBody);
        String password = extractPassword(emailBody);

        // Print extracted username and password
        System.out.println("Extracted Username: " + username);
        System.out.println("Extracted Password: " + password);

        // Perform login using extracted username and password
        EP.login(username, password);
    }

    // Method to extract username from email body
    private static String extractUsername(String emailBody) {
        int usernameStartIndex = emailBody.indexOf("Username:");
        if (usernameStartIndex == -1) {
            // Username not found in email body
            return "";
        }
        usernameStartIndex += "Username:".length();

        int usernameEndIndex = emailBody.indexOf("\n", usernameStartIndex);
        if (usernameEndIndex == -1) {
            // End of username not found in email body
            return "";
        }
        return emailBody.substring(usernameStartIndex, usernameEndIndex).trim();
    }

    // Method to extract password from email body
    private static String extractPassword(String emailBody) {
        int passwordStartIndex = emailBody.indexOf("Password:");
        if (passwordStartIndex == -1) {
            // Password not found in email body
            return "";
            
        }
        passwordStartIndex += "Password:".length();

        int passwordEndIndex = emailBody.indexOf("\n", passwordStartIndex);
        if (passwordEndIndex == -1) {
            // End of password not found in email body
            return "";
        }
        return emailBody.substring(passwordStartIndex, passwordEndIndex).trim();
    }
}

