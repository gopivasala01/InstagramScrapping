package leasecloseouts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import CompanyWisePDFDataScrap.Arizona;

public class RunnerClass 
{
	static	ChromeDriver driver;
	static Actions actions;
	static JavascriptExecutor js;
	static File file;
	static FileInputStream fis;
	static StringBuilder stringBuilder = new StringBuilder() ;
	static WebDriverWait wait;
	static FileOutputStream fos;
	static RunnerClass runnerClassObject;
	static PropertyWare propertyWareObject;
    static PDFDataScrapping pdfDataScrapperObject;
	RunnerClass()
	{
		System.setProperty(AppConfig.browserType,AppConfig.browserPath);
		Map<String, Object> prefs = new HashMap<String, Object>();
        
        // Use File.separator as it will work on any OS
        prefs.put("download.default_directory",
                "C:\\Gopi\\Projects\\Property ware\\Lease Close Outs\\PDFS");
         
        // Adding cpabilities to ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
         
        // Printing set download directory
         
        // Launching browser with desired capabilities
         driver= new ChromeDriver(options);
		actions = new Actions(driver);
		js = (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	}
	
	public static void main(String[] args) throws Exception
	{
		runnerClassObject = new RunnerClass();
		propertyWareObject = new PropertyWare();
		
		// Login to Propertyware
		propertyWareObject.login();
		propertyWareObject.selectLease();
		propertyWareObject.validateSelectedLease();
		Thread.sleep(10000);
		// Get data from PDF
		//pdfDataScrapperObject = new PDFDataScrapping();
		//pdfDataScrapperObject.getDataFromPDF();
		Arizona arizona = new Arizona();
		arizona.arizona();
		PropertyWare_InsertLeaseInfo insertLeaseInfo = new PropertyWare_InsertLeaseInfo();
		insertLeaseInfo.insertData();
		

	}
	
	

}
