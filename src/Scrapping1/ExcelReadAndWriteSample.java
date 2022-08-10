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

public class ExcelReadAndWriteSample 
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
	ExcelReadAndWriteSample()
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
	    FileInputStream outputFIS = new FileInputStream(outputFile);
	    XSSFWorkbook outputWorkbook = new XSSFWorkbook(outputFIS);
	    XSSFSheet outputSheet = outputWorkbook.getSheetAt(0);
	    for(int i =0;i<InputSheet.getLastRowNum();i++)
	    {
	    Row InputRow= InputSheet.getRow(i+1);
	    Row outputRow = outputSheet.createRow(outputSheet.getLastRowNum()+1);
	    Cell Cell_pageID = outputRow.createCell(0);
	    Cell Cell_followersCount = outputRow.createCell(1);
	    Cell Cell_followingCount = outputRow.createCell(2);
	    Cell Cell_URL = outputRow.createCell(3);
	    String pageName = InputRow.getCell(0).toString();
	    actions.click(driver.findElement(Locators.searchBox)).sendKeys(("jtbarnett")).build().perform();
	    driver.findElement(Locators.selectingPageFromList).click();
        driver.findElement(Locators.followersCount).click();
        String followersCountRaw = driver.findElement(Locators.followersCount).getText();
        String theDigits =null;
        int followersCount =0;
        if(followersCountRaw.contains("k"))
        {
        	theDigits = CharMatcher.inRange('0', '9').retainFrom(followersCountRaw); // 123
        	followersCount = Integer.parseInt(theDigits);
        	 followersCount = followersCount*100;
        }
        ArrayList<String> list = new ArrayList<String>();
        List<WebElement> followers = driver.findElements(Locators.followersList);
        int size = followers.size();
       // int followersCount = Integer.parseInt(theDigits);
        for(int j=0;j<followers.size();j++)
        {
           String text = followers.get(j).getText();
           System.out.println(text);
           list.add(text);
           if(followers.size()==(j+1))
           {
        	   //driver.findElement(By.xpath("//*[@class='isgrP']")).click();
        	   //js.executeScript("arguments[0].scrollTop = arguments[1];",driver.findElement(By.xpath("//*[@class='isgrP']")), 100);
        	   //js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        	   driver.findElement(By.xpath("//*[@class='isgrP']")).click();
        	   actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).build().perform();
        	   js.executeScript("arguments[0].scrollIntoView();", followers.get(j));
        	   //actions.moveToElement(followers.get(j)).build().perform();
        	   actions.moveToElement( driver.findElement(By.xpath("//*[@class='isgrP']"))).build().perform();
        	   Thread.sleep(2000);
        	   j=followers.size()-1;
        	   followers = driver.findElements(Locators.followersList);
        	   size = followers.size();
           }
        }
        System.out.println(list.size());
        XSSFSheet outputSheet2 = outputWorkbook.getSheetAt(1);
        for(int k=0;k<list.size();k++)
        {
        	Row row = outputSheet2.createRow(outputSheet2.getLastRowNum()+1);
        	Cell accountName = row.createCell(0);
        	accountName.setCellValue(pageName);
        	Cell followerPageName = row.createCell(1);
        	followerPageName.setCellValue(list.get(k));
        }
        FileOutputStream fos = new FileOutputStream(outputFile);
	    FileOutputStream fos2 = new FileOutputStream(file);
	    outputWorkbook.write(fos);
	    InputWorkbook.write(fos2);
	    fis.close();
	    outputFIS.close();
	    fos.close();
	    fos2.close();
        break;
	    /*String pageID;
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
	    
	    }*/}
	    //fis.close();
	  
	    }
	}


