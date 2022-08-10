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
    static String leaseName = "TAYL2790 - (2790 TAYLOR HILL DR)_5895";
    static String leaseOwner = "Bass - Bass";
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
			if(documents.get(i).getText().contains(leaseFirstName))
			{
				documents.get(i).click();
				break;
			}
		}
		

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
	
	
	
	
}
