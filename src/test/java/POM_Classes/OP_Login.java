package POM_Classes;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OP_Login {

    WebDriver driver;

    @FindBy(xpath = "//a[normalize-space()='Activities']")
    private WebElement activity;

    @FindBy(xpath = "//a[normalize-space()='Franchisee Verification/Inspection']")
    private WebElement FranVeriInfec;

    @FindBy(id = "FromDate")
    private WebElement fromDateInput;

    @FindBy(id = "ToDate")
    private WebElement toDateInput;

    @FindBy(xpath = "//select[@id='Status']")
    private WebElement selectStatus;

    @FindBy(id = "btnShow")
    private WebElement Showbtn;
    
    @FindBy(xpath="//input[@type='search']")
    private WebElement srchBtn;

    @FindBy(xpath = "//a[@id='viewAppl_1']")
    private WebElement select;

    @FindBy(xpath = "//textarea[@id='comment']")
    private WebElement commentBox;

    @FindBy(xpath = "//input[@id='docUpload']")
    private WebElement fileInput;

    @FindBy(id ="Action")
    private WebElement actionDropdown;

    @FindBy(id = "btnSubmit")
    private WebElement btnSubmit;

    @FindBy(id = "Back")
    private WebElement Backbtn;

    @FindBy(xpath = "//td[normalize-space()='Pending For Security Deposit Payment']")
    private static WebElement pendingForScrutiny;

    public OP_Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void OPlogin(String filePath, String fromDate, String toDate,String regEmailId) throws InterruptedException {
        Thread.sleep(2000);
        activity.click();
        FranVeriInfec.click();
        Thread.sleep(3000);
        selectFromDate(fromDate);
        selectToDate(toDate);

        Select drop = new Select(selectStatus);
        drop.selectByVisibleText("Under Scrutiny");

        Showbtn.click();
        Thread.sleep(5000);
        srchBtn.sendKeys(regEmailId);
        Thread.sleep(3000);
        select.click();
        commentBox.sendKeys("Approved");
        uploadFile(filePath);

        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(5000);
        
        Select drop1 = new Select(actionDropdown);
        drop1.selectByVisibleText("Approved");

        btnSubmit.click();
        Thread.sleep(3000);
        Backbtn.click();
    }

    private void selectFromDate(String fromDate) {
        // Clear the existing dropdown value
        fromDateInput.clear();
        // Enter the new date using sendKeys()
        fromDateInput.sendKeys(fromDate);
    }

    private void selectToDate(String toDate) {
        // Clear the existing dropdown value
        toDateInput.clear();
        // Enter the new date using sendKeys()
        toDateInput.sendKeys(toDate);
    }

    private void uploadFile(String filePath) {
        // Ensure the file input field is visible and enabled before uploading
        if (fileInput.isDisplayed() && fileInput.isEnabled()) {
            fileInput.sendKeys(filePath);
        } else {
            System.out.println("File input element is not visible or enabled.");
        }
    }

    public String pendingForScrutinyText() {
        return pendingForScrutiny.getText();
    }
}
