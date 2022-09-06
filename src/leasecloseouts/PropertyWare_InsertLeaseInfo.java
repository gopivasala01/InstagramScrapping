package leasecloseouts;

public class PropertyWare_InsertLeaseInfo 
{

	public void insertData()
	{
		RunnerClass.driver.navigate().refresh();
		RunnerClass.driver.findElement(Locators.ledgerTab).click();
		RunnerClass.driver.findElement(Locators.newCharge).click();
		RunnerClass.driver.findElement(Locators.accountDropdown).click();
	}
	
	
}
