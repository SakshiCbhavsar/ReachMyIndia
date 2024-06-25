package POM_Classes;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic.Email_BaseTest;


public class EmailPage extends Email_BaseTest{

	private WebDriver driver;

	@FindBy(id = "identifierId")
	private WebElement emailInput;

	@FindBy(xpath = "//span[normalize-space()='Next']")
	private WebElement nextButton;

	@FindBy(xpath = "//input[@name='Passwd']")
	private WebElement passwordInput;

	@FindBy(id = "passwordNext")
	private WebElement passwordNextButton;
	
	@FindBy(xpath = "//a[normalize-space()='click here']")
	private WebElement chickfordeposit;

	public EmailPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void login(String email, String password) throws InterruptedException {
		emailInput.sendKeys(email);
		nextButton.click();
		Thread.sleep(6000);
		passwordInput.sendKeys(password);
		passwordNextButton.click();
	}

	 public void readEmail(String subject) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(
	                By.xpath("//span[contains(text(), '" + subject + "')]/ancestor::tr[contains(@class, 'zA')]")));

	        // Click on the email to open it
	        String currentWindowHandle = driver.getWindowHandle();

	        // Click on the email to open it
	        email.click();

	        // Get all window handles
	        Set<String> windowHandles = driver.getWindowHandles();

	        // Iterate over all window handles
	        for (String windowHandle : windowHandles) {
	            // Switch to the new window
	            if (!windowHandle.equals(currentWindowHandle)) {
	                driver.switchTo().window(windowHandle);
	                break;
	            }
	        }

	        // Click on the "click here" link
	        chickfordeposit.click();

	        // Switch back to the original window
	        driver.switchTo().window(currentWindowHandle);

	        // Navigate back to the previous page
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.history.go(-2)");
	  
	    }
	 public void readEmailandgetbody(String subject) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(
	                By.xpath("//span[contains(text(), '" + subject + "')]/ancestor::tr[contains(@class, 'zA')]")));

	        // Click on the email to open it
	        email.click();

	        // Wait for the email body to be visible
	        WebElement emailBody = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='adn ads']/div[2]")));

	        // Extract and print email body
	        System.out.println("Email Body:");
	        System.out.println(emailBody.getText());
	    }
	


}
