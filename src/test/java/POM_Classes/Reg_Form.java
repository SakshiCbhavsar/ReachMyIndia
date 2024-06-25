package POM_Classes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hpsf.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;

import Generic.Auto_Constant;
import Generic.Base_Page;

public class Reg_Form extends Base_Page implements Auto_Constant {
	static WebDriver driver;
	// WebDriverWait wait;

	@FindBy(id = "ApplicantName")
	private WebElement appName;

	@FindBy(id = "PanCardNumber")
	private WebElement Pan;
	
	@FindBy(id = "DateOfBirth")
	private WebElement Dob;

	@FindBy(xpath = "//select[@id='Gender']")
	private WebElement gen;

	@FindBy(xpath = "//select[@id='State']")
	private WebElement state;

	@FindBy(how = How.XPATH, using = "//select[@id='District']")
	private WebElement dist;

	@FindBy(xpath = "//input[@id='CommunicationAddress1']")
	private WebElement pradr;

	@FindBy(xpath = "//input[@id='CommunicationAddressPinCode']")
	private WebElement pincode;

	@FindBy(xpath = "//*[@id='IsAddressSame']")
	private WebElement spaddr;

	@FindBy(xpath = "//input[@id='IsAccept']")
	private WebElement cond;

	@FindBy(xpath = "//input[@id='btnRegistration']")
	private WebElement cont;
	
	@FindBy(xpath = "//a[normalize-space()='//a[normalize-space()='Download Application Form'] Application Form']")
	private WebElement DowAppForm;
	

	public Reg_Form(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void details(String aname, String pan, String dob, String paddr, String pincde)

			throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(appName))
				.sendKeys(aname);

		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(Pan)).sendKeys(pan);
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(Dob)).sendKeys(dob);	 
	  
			 
		Select sel = new Select(gen);
		sel.selectByIndex(1);

		Thread.sleep(2000);

		Select sel1 = new Select(state);
		sel1.selectByVisibleText("Karnataka");

		Thread.sleep(2000);

		Select drop = new Select(dist);
		drop.selectByVisibleText("Ballary");

		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("window.scrollBy(0,350)", "");

		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(pradr)).sendKeys(paddr);

		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(pincode))
				.sendKeys(pincde);

		js2.executeScript("window.scrollBy(0,350)", "");
		Thread.sleep(2000);

		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(spaddr))
				.click();
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(cond)).click();
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(cont)).click();

		// Navigate back two time
		Thread.sleep(10000);
	}
//	 public void details1(String aname) throws InterruptedException, IOException {
//		    
//		        DowAppForm.click();
//		        // Extract text from the dynamically named PDF file
//		        extractTextFromPDF(aname);
		        
//	 }
//	 private void extractTextFromPDF(String aname) throws IOException {
//		    String dynamicFilePath = "C:\\Users\\SAKSHI_BHAVASAR\\Downloads" + aname + ".pdf";
//		    try {
//		        FileInputStream fileInputStream = new FileInputStream(dynamicFilePath);
//		        PDDocument document = PDDocument.load(fileInputStream);
//		        PDFTextStripper stripper = new PDFTextStripper();
//		        String text = stripper.getText(document);
//		        String applicationId = extractApplicationId(text);
//
//		        System.out.println("Application ID: " + applicationId);
//		        document.close();
//		        
//		        document.close();
//		    } catch (FileNotFoundException e) {
//		        System.err.println("File not found: " + dynamicFilePath);
//		        e.printStackTrace();
//		    } catch (IOException e) {
//		        System.err.println("Error loading PDF: " + e.getMessage());
//		        e.printStackTrace();
//		    }
//		}
//	 
//	 private String extractApplicationId(String text) {
//		    String regex = "\\b[A-Za-z0-9]{20}\\b";
//		
//		    Pattern pattern = Pattern.compile(regex);
//		    Matcher matcher = pattern.matcher(text);
//	
//		    if (matcher.find()) {
//		        return matcher.group(1);
//		    } else {
//		        return null;
//		    }
//		}

	// Scroll to Top
	public static void Screenshot() throws IOException, InterruptedException {
		
		Thread.sleep(3000);
		JavascriptExecutor js4 = (JavascriptExecutor) driver;
		js4.executeScript("window.scrollTo(0, 0);");
		Long innerHeight = (Long) js4.executeScript("return window.innerHeight;");
		Long scroll = innerHeight;

		Long scrollHeight = (Long) js4.executeScript("return document.body.scrollHeight;");

		scrollHeight = scrollHeight + scroll;
		try {
			do {
				File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				File destination = new File(SFolderpath + String.join("_", LocalDateTime.now().toString().split("[^A-Za-z0-9]")) + ".png");
				FileUtils.copyFile(screenshot, destination);
				js4.executeScript("window.scrollTo(0, " + innerHeight + ");");
				innerHeight = innerHeight + scroll;
			} while (scrollHeight >= innerHeight);
			System.out.println("ScreenShot Captured");
		} catch (IOException e) {
			System.out.println("Unble to capture ScreenShot" + e.getMessage());
		}
	}

}
