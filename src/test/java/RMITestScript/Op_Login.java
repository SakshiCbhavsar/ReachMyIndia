package RMITestScript;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.Login;
import POM_Classes.OP_Login;



public class Op_Login extends BaseTest  {
	
	public static Logger log;
	
	@BeforeTest
	public void setup() throws IOException
	{	
//		initialize();
		log=LogManager.getLogger(Login.class.getName());
		
	}
	
	
	@Test(priority=3,dataProvider="getLoginData")
//	@Test(priority=1,dataProvider="getLoginData")
	public void loginPage(String expectedResult) throws IOException, InterruptedException{
		
		Thread.sleep(2000);
		String opuserrname = Excel.Testdata(Path, "Sheet2", 1, 0);
		String opPassword = Excel.Testdata(Path, "Sheet2", 1, 1);

		Login l = new Login(driver);
		Thread.sleep(2000);
		log.debug("Navigated to application URL");
		l.login(opuserrname, opPassword);
		log.debug("Username And Password got entered");
		log.debug("Login Successfully!");
	
	

//	@Test(priority=2)
////	@Test(priority=4)
//	public void OPloginPage() throws InterruptedException{
		
	    String UploadFilePath = Excel.Testdata(Path, "Sheet2", 2, 1);
	    String Fromdate = Excel.Testdata(Path, "Sheet2", 3, 1);
	    String Todate= Excel.Testdata(Path, "Sheet2", 4, 1);
	    String regEmailId= Excel.Testdata(Path, "Sheet1", 4, 1);
		
		Thread.sleep(4000);
		OP_Login OP=new OP_Login(driver);
		OP.OPlogin(UploadFilePath,Fromdate, Todate,regEmailId);
		
		
		 Assert.assertEquals(OP.pendingForScrutinyText(), "Pending For Security Deposit Paymentt");
	}
	
	@DataProvider
	public Object[][] getLoginData() {
		Object[][] data = {{"Success"},{"Failure"}};
		return data;
	}
}
   




