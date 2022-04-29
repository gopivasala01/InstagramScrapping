package Scrapping1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RunnerClass 
{
	static	ChromeDriver driver;
	static Actions actions;
	static JavascriptExecutor js;
	static File file;
	static FileInputStream fis;
	//static XSSFWorkbook workbook;
	//static XSSFSheet sheet;
	//static Row row;
	static StringBuilder stringBuilder = new StringBuilder() ;
	static WebDriverWait wait;
	static int flag =0;
	static File userCredentialsFile;
	static FileInputStream userFileInputStream;
	//static Workbook userWorkbook;
	//static Sheet userSheet;
	static Calendar userLoggedInTime;
	static FileOutputStream fos;
	static int lastRowNumber;
	static boolean flagToCheckUserLoginTime = false;
	static String loggedInTimeInSheet;
	static boolean flagToCheckLoginForFirstTimeOrRepeatedUser;
	RunnerClass()
	{
		System.setProperty(AppConfig.browserType,AppConfig.browserPath);
		driver = new ChromeDriver();
		js = (JavascriptExecutor)driver;
		actions = new Actions(driver);
		//wait = new WebDriverWait(driver,100);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		
	}
	public static void main(String[] args) throws Exception
	{
		RunnerClass runnerClassObject = new RunnerClass();
	    driver.get(AppConfig.instagramURL);
	    driver.manage().window().maximize();
	    driver.findElement(Locators.userName).sendKeys(AppConfig.userName);
	    driver.findElement(Locators.password).sendKeys(AppConfig.password);
	    Thread.sleep(2000);
	    actions.sendKeys(Keys.ENTER).build().perform();
	    Thread.sleep(2000);
	    try
	    {
	    driver.findElement(Locators.saveLoginInfo).click();
	    }
	    catch(Exception e)
	    {
	    	driver.navigate().refresh();
	    	driver.findElement(Locators.userName).sendKeys(AppConfig.userName);
		    driver.findElement(Locators.password).sendKeys(AppConfig.password);
		    Thread.sleep(2000);
		    actions.sendKeys(Keys.ENTER).build().perform();
		    Thread.sleep(2000);
		    driver.findElement(Locators.saveLoginInfo).click();
	    }
	    Thread.sleep(2000);
	    driver.findElement(Locators.turnOnNotifications).click();
	    Thread.sleep(2000);
	    //Read Page names from client sheet
	    file = new File(AppConfig.pagesList);
	    fis = new FileInputStream(file);
	    XSSFWorkbook InputWorkbook = new XSSFWorkbook(fis);
	    XSSFSheet InputSheet = InputWorkbook.getSheetAt(0);
	    File outputFile = new File(AppConfig.scrappedFile);
	    file = new File(AppConfig.scrappedFile);
	    fos = new FileOutputStream(outputFile);
	    XSSFWorkbook OutputWorkbook=null;
	    for(int i =0;i<InputSheet.getLastRowNum();i++)
	    {
	    Row InputRow= InputSheet.getRow(i+1);
	    String pageName = InputRow.getCell(0).toString();
	    actions.click(driver.findElement(Locators.searchBox)).sendKeys((pageName)).build().perform();
	    driver.findElement(Locators.selectingPageFromList).click();
	    String pageID;
	    try
	    {
	    pageID  = driver.findElement(Locators.pageID).getText();
	    }
	    catch(Exception e)
	    {
	    	pageID = pageName;
	    	
	    }
	    System.out.println(pageID);
	    String followersCount;
	    try
	    {
	    followersCount = driver.findElement(Locators.followersCount).getText();
	    }
	    catch(Exception e)
	    {
	    followersCount ="N/A";
	    }
	    System.out.println(followersCount);
	    String followingCount;
	    try
	    {
	    followingCount = driver.findElement(Locators.followingCount).getText();
	    }
	    catch(Exception e)
	    {
	    	followingCount = "N/A";
	    }
	    System.out.println(followingCount);
	    String URLInBio;
	    try
	    {
	    URLInBio = driver.findElement(Locators.URL).getText(); //the_culture_club_makassar
	    }
	    catch(Exception e)
	    {
	    	URLInBio = "N/A";
	    }
	    System.out.println(URLInBio);
	    /*InputStream outputFis = new FileInputStream(outputFile);
		   OutputWorkbook = new XSSFWorkbook(outputFis);
		    XSSFSheet OutputSheet = OutputWorkbook.getSheetAt(0);
		    int lastOutputSheetRowNumber = OutputSheet.getLastRowNum();
	    Row outputRow = OutputSheet.createRow(lastOutputSheetRowNumber+i+1);
	    Cell PageIDValue = outputRow.createCell(0);
	    PageIDValue.setCellValue(pageID);
	    Cell followersCountvalue = outputRow.createCell(1);
	    followersCountvalue.setCellValue(followersCount);
	    Cell followingCountvalue = outputRow.createCell(2);
	    followingCountvalue.setCellValue(followingCount);
	    Cell URLValue = outputRow.createCell(3);
	    URLValue.setCellValue(URLInBio);*/
	    driver.navigate().to(AppConfig.instagramURL);
	    System.out.println("------------------");
	    Thread.sleep(2000);
	    
	    }
	    fis.close();
	    OutputWorkbook.write(fos);
	    

	}

}
