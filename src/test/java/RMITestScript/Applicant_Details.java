package RMITestScript;
import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.Reg_Form;
//@Listeners(ScreenShot.TestNGListeners.class)
public class Applicant_Details extends BaseTest 
{
	private static Reg_Form appdtls;
	public static Logger log;
	
	@Test(priority=2)
	public static void  form()throws InterruptedException, IOException
	{
		
		String name = Excel.Testdata(Path, "Sheet1", 8, 0);
		String pan =  Excel.Testdata(Path, "Sheet1", 8, 1);
		String dob = Excel.Testdata(Path, "Sheet1", 8, 2);
		String paddr = Excel.Testdata(Path, "Sheet1", 8, 3);
		String pcode = Excel.Testdata(Path, "Sheet1", 8, 4);
		Thread.sleep(1000);
		appdtls=new Reg_Form(driver);
		Thread.sleep(1000);
		appdtls.details(name, pan,dob, paddr, pcode);
		Reg_Form.Screenshot();
		
		
		log.debug("Regisration done Successfully!");
		
		

	 
		
		
		
	}
}
