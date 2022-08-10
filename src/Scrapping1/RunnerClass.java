package Scrapping1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.CharMatcher;

public class RunnerClass 
{
	static	ChromeDriver driver;
	static Actions actions;
	static JavascriptExecutor js;
	static File file;
	static FileInputStream fis;
	static StringBuilder stringBuilder = new StringBuilder() ;
	static WebDriverWait wait;
	static int flag =0;
	static File userCredentialsFile;
	static FileInputStream userFileInputStream;
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
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		
		
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
	    FileInputStream outputFIS = new FileInputStream(outputFile);
	    XSSFWorkbook outputWorkbook = new XSSFWorkbook(outputFIS);
	    XSSFSheet outputSheet = outputWorkbook.getSheetAt(0);
	    try {
	    for(int i =0;i<InputSheet.getLastRowNum();i++)
	    {
	    Row InputRow= InputSheet.getRow(i+1);
	    Row outputRow = outputSheet.createRow(outputSheet.getLastRowNum()+1);
	    Cell Cell_pageID = outputRow.createCell(0);
	    Cell Cell_followersCount = outputRow.createCell(1);
	    Cell Cell_followingCount = outputRow.createCell(2);
	    Cell Cell_URL = outputRow.createCell(3);
	    String pageName = InputRow.getCell(0).toString();
	    actions.click(driver.findElement(Locators.searchBox)).sendKeys((pageName)).build().perform();
	    try
	    {
	    driver.findElement(Locators.selectingPageFromList).click();
	    }
	    catch(Exception e)
	    {
	    	continue;
	    }
	   
        //driver.findElement(Locators.followersCount).click();
        String followersCountRaw = driver.findElement(Locators.followersCount).getText();
        String theDigits =null;
      
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
	    Cell_pageID.setCellValue(pageID);
	    Cell_followersCount.setCellValue(followersCount);
	    Cell_followingCount.setCellValue(followingCount);
	    Cell_URL.setCellValue(URLInBio);
	    Cell statusCell = InputRow.getCell(1);
	    statusCell.setCellValue("Completed");
	    driver.navigate().to(AppConfig.instagramURL);
	    System.out.println("------------------");
	    Thread.sleep(2000);
	    }
	    fis.close();
	    FileOutputStream fos = new FileOutputStream(outputFile);
	    FileOutputStream fos2 = new FileOutputStream(file);
	    outputWorkbook.write(fos);
	    InputWorkbook.write(fos2);
	    fis.close();
	    outputFIS.close();
	    fos.close();
	    fos2.close();
	    }
	    catch(Exception e)
	    {
	    	fis.close();
		    FileOutputStream fos = new FileOutputStream(outputFile);
		    FileOutputStream fos2 = new FileOutputStream(file);
		    outputWorkbook.write(fos);
		    InputWorkbook.write(fos2);
		    fis.close();
		    outputFIS.close();
		    fos.close();
		    fos2.close();
	    }
	    }
	}


