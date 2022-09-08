package leasecloseouts;

import org.openqa.selenium.By;

public class Locators 
{
	public static By userName = By.id("loginEmail");
	public static By password = By.name("password");
	public static By signMeIn = By.xpath("//*[@value='Sign Me In']");
	
	public static By searchbox = By.name("eqsSearchText");
	public static By dashboardsTab = By.linkText("Dashboards");
	public static By searchingLoader = By.xpath("//*[@id='eqsResult']/h1");
    public static By leasesTab = By.xpath("//*[@class='tabbedSection']/a[4]");	
    public static By leaseStartDate_PW = By.xpath("//*[@id='infoTable']/tbody/tr[3]/td[1]");
    public static By leaseEndDate_PW = By.xpath("//*[@id='infoTable']/tbody/tr[3]/td[2]");
    public static By notesAndDocs = By.id("notesAndDocuments");
    public static By documentsList = By.xpath("//*[@id='documentHolderBody']/tr/td[1]/a"); 
    
    public static By ledgerTab = By.id("tab2");
    public static By newCharge = By.xpath("//*[@value='New Charge']");
    public static By accountDropdown = By.name("charge.GLAccountID");
    public static By accountList = By.xpath("//*[@id='oneTimeGLAccountID']/optgroup/option");
    public static By referenceName = By.name("charge.refNo");
    public static By moveInChargeAmount = By.name("charge.editAmountAsString");
    public static By moveInChargeDate = By.name("charge.dateAsString");
    public static By moveInChargeSave = By.xpath("//*[@value='Save']");
    public static By moveInChargeCancel = By.xpath("//*[@value='Cancel']");
    
    
    

}
