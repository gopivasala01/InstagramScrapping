package leasecloseouts;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class PropertyWare_InsertLeaseInfo 
{

	public void insertData() throws Exception
	{
		RunnerClass.driver.navigate().refresh();
		RunnerClass.driver.findElement(Locators.ledgerTab).click();
		Thread.sleep(2000);
		
		
		String[][] array = new String[4][4]; 
		System.out.println(array.length);
		array[0][0]=AppConfig.proratedRent_AccountCode;
		array[0][1]=AppConfig.proratedRent_AccountRef;
		array[0][2]=PropertyWare.proratedRent;
		array[0][3]=PropertyWare.convertDate(PropertyWare.commensementDate);
		
		array[1][0]=AppConfig.fullMonthRent_AccountCode;
		array[1][1]=AppConfig.fullMonthRent_AccountRef;
		array[1][2]=PropertyWare.monthlyRent;
		array[1][3]=PropertyWare.convertDate(PropertyWare.monthlyRentDate);
		
		array[2][0]=AppConfig.adminFee_AccountCode;
		array[2][1]=AppConfig.adminFee_AccountRef;
		array[2][2]=PropertyWare.adminFee;
		array[2][3]=PropertyWare.convertDate(PropertyWare.commensementDate);;
		
		array[3][0]=AppConfig.proratedPetRent_AccountCode;
		array[3][1]=AppConfig.proratedPetRent_AccountRef;
		array[3][2]=PropertyWare;
		array[3][3]=PropertyWare.convertDate(PropertyWare.commensementDate);
		
		
		for(int i=0;i<array.length;i++)
		{
			for(int j=0;j<=i;j++)
			{
			System.out.println(array[i][j]+" "+array[i][j+1]+" "+array[i][j+2]+" "+array[i][j+3]);
			RunnerClass.driver.findElement(Locators.newCharge).click();
			Thread.sleep(2000);
			RunnerClass.driver.findElement(Locators.accountDropdown).click();
			Thread.sleep(2000);
			
			List<WebElement> accountList = RunnerClass.driver.findElements(Locators.accountList);
			for(int c=0;c<accountList.size();c++)
			{
				if(accountList.get(c).getText().equalsIgnoreCase(AppConfig.proratedPetRent_AccountCode))
				{
					accountList.get(c).click();
					break;
				}
			}
			RunnerClass.driver.findElement(Locators.referenceName).sendKeys(AppConfig.proratedPetRent_AccountRef);
			Thread.sleep(2000);
			RunnerClass.driver.findElement(Locators.moveInChargeAmount).click();
			RunnerClass.actions.sendKeys(Keys.SHIFT).sendKeys(Keys.HOME).sendKeys(Keys.BACK_SPACE).build().perform();
			Thread.sleep(2000);
			RunnerClass.driver.findElement(Locators.moveInChargeAmount).sendKeys(PropertyWare.proratedRent);
			Thread.sleep(2000);
			RunnerClass.driver.findElement(Locators.moveInChargeDate).clear();
			Thread.sleep(2000);
			RunnerClass.driver.findElement(Locators.moveInChargeDate).sendKeys(PropertyWare.convertDate(PropertyWare.proratedRentDate));
			Thread.sleep(2000);
			RunnerClass.driver.findElement(Locators.moveInChargeCancel).click();
			
			RunnerClass.driver.navigate().refresh();
			
			break;
			}
		}
		
		
		
	}
	
	
}
