package leasecloseouts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;


public class PropertyWare 
{
    static String leaseName = "31ST7116 - (7116 N 31ST DR)_7406";
    static String leaseOwner = "Nelson - Jones";
    public static String commensementDate;
    public static String expirationDate;
    public static String proratedRent;
    public static String proratedRentDate;
    public static String monthlyRent;
    public static String monthlyRentDate;
    public static String adminFee;
    public static String airFilterFee;
    public static String earlyTermiantion;
    public static String occupants;
    public static String lateChargeDay;
    public static String lateChargeFee;
    public static String proratedPetRent;
    public static String proratedPetRentDate;
    public static String petSecurityDeposit;
    
	public void login()
	{
		RunnerClass.driver.get(AppConfig.propertyWareURL);
		RunnerClass.driver.findElement(Locators.userName).sendKeys(AppConfig.userName);
		RunnerClass.driver.findElement(Locators.password).sendKeys(AppConfig.password);
		RunnerClass.driver.findElement(Locators.signMeIn).click();
		RunnerClass.driver.manage().window().maximize();
	}
	
	public void selectLease()
	{
		RunnerClass.driver.findElement(Locators.dashboardsTab).click();
		RunnerClass.driver.findElement(Locators.searchbox).sendKeys(leaseName);
		RunnerClass.wait.until(ExpectedConditions.invisibilityOf(RunnerClass.driver.findElement(Locators.searchingLoader)));
		RunnerClass.driver.findElement(By.partialLinkText(leaseName)).click();
	}
	public boolean validateSelectedLease() throws Exception
	{
		RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		Thread.sleep(2000);
		RunnerClass.driver.findElement(Locators.leasesTab).click();
		RunnerClass.driver.findElement(By.linkText(leaseOwner)).click();
		String leaseStartDate_PW =RunnerClass.driver.findElement(Locators.leaseStartDate_PW).getText();
		String leaseEndDate_PW =RunnerClass.driver.findElement(Locators.leaseEndDate_PW).getText();
		RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		Thread.sleep(2000);
		RunnerClass.driver.findElement(Locators.notesAndDocs).click();
		String leaseFirstName = leaseOwner.split(" ")[0];
		List<WebElement> documents = RunnerClass.driver.findElements(Locators.documentsList);
		for(int i =0;i<documents.size();i++)
		{
			if(documents.get(i).getText().contains("Lease"))
			{
				documents.get(i).click();
				break;
			}
		}
		
		Thread.sleep(15000);
		File file = PropertyWare.getLastModified();
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(RunnerClass.driver).withTimeout(Duration.ofSeconds(25)).pollingEvery(Duration.ofMillis(100));
		wait.until( x -> file.exists());
		Thread.sleep(10000);
		return true;
	}
	public static void renameDownloadedFile() throws Exception
	{
		/*
		Path dir = Paths.get(AppConfig.PDFFilePath);  // specify your directory

		Optional<Path> lastFilePath = Files.list(dir)    // here we get the stream with full directory listing
		    .filter(f -> !Files.isDirectory(f))  // exclude subdirectories from listing
		    .max(Comparator.comparingLong(f -> f.toFile().lastModified()));  // finally get the last file using simple comparator by lastModified field

		if ( lastFilePath.isPresent() ) // your folder may be empty
		{
		    
			
		}    
		*/			
		File file = PropertyWare.getLastModified();
		File newFile = new File(AppConfig.PDFFilePath+"\\"+leaseName+".pdf");
		file.renameTo(newFile);
		//file.renameTo(new File(AppConfig.PDFFilePath+"\\TAYL2790 - (2790 TAYLOR HILL DR)_5895.pdf"));
        //Path source = Paths.get(AppConfig.PDFFilePath);
		//Files.move(dir, dir.resolveSibling("TAYL2790 - (2790 TAYLOR HILL DR)_5895"));
		//if(file.exists())
		//{
		//	file.delete();
		//}
		
	}
	
	public static File getLastModified()
	{
	    File directory = new File(AppConfig.PDFFilePath);
	    File[] files = directory.listFiles(File::isFile);
	    long lastModifiedTime = Long.MIN_VALUE;
	    File chosenFile = null;

	    if (files != null)
	    {
	        for (File file : files)
	        {
	            if (file.lastModified() > lastModifiedTime)
	            {
	                chosenFile = file;
	                lastModifiedTime = file.lastModified();
	            }
	        }
	    }

	    return chosenFile;
	}
	public static String convertDate(String date)
	{
		String[] d = date.trim().split(" ");
		String month = PropertyWare.convertMonth(d[0].trim());
		String[] yearAndDate = date.trim().split(",");
		String dateIn = month +"/"+ yearAndDate[0].split(" ")[1].trim()+"/"+yearAndDate[1].trim();
		System.out.println(dateIn);
		return dateIn;
				
	}
	public static String convertMonth(String month)
	{
		switch (month) {
		  case "January":
		    month =  "01";
		    break;
		  case "February":
			  month = "02";
			  break;
		  case "March":
			  month = "03";
			  break;
		  case "April":
			  month =  "04"; 
			  break;
		  case "May":
			  month =  "05";
			  break;
		  case "June":
			  month =  "06";
			  break;
		  case "July":
			  month =  "07";
			  break;
		  case "August":
			  month =  "08";
			  break;
		  case "September":
			  month =  "09";
			  break;
		  case "October":
			  month =  "10";
			  break;
		  case "November":
			  month =  "11";
			  break;
		  case "December":
			  month =  "12";
			  break;
		               }
          return month;		
	}

		   public static boolean onlyDigits(String str)
		    {
		        for (int i = 0; i < str.length(); i++) 
		        {
		            if (Character.isDigit(str.charAt(i))) 
		            {
		                return true;
		            }
		        }
		        return false;
		    }
		 
	
	
	
}
