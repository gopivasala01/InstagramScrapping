package leasecloseouts;

public class AppConfig 
{
	public static String PDFFilePath = "C:\\Gopi\\Projects\\Property ware\\Lease Close Outs\\PDFS";
	public static String propertyWareURL = "https://app.propertyware.com/pw/login.jsp";
	public static String userName = "mds0418@gmail.com";
	public static String password = "HomeRiver1#";
	public static String leaseInfo = "C:\\Gopi\\Projects\\Property ware\\Lease Close Outs\\LeaseInfo.xlsx";
	public static String browserType = "webdriver.chrome.driver";
	public static String browserPath = "C:\\Gopi\\Automation\\Chrome Drivers\\chromedriver_103.exe"; 
	
	// **************************** Account Codes - Move In Charges *******************************
	public static String proratedRent_AccountCode = "4000 - Rent";
	public static String proratedRent_AccountRef = "Pro Rate Rent";
	
	public static String fullMonthRent_AccountCode = "2017 - Prepayments";
	public static String fullMonthRent_AccountRef = "Full Month's Rent";
	
	public static String adminFee_AccountCode = "4303 - Tenant Admin Revenue";
	public static String adminFee_AccountRef = "Admin Fee";
	
	public static String proratedPetRent_AccountCode = "4311 - Pet Rent";
	public static String proratedPetRent_AccountRef = "Pro Rated Pet Rent";
	
	public static String petRent_AccountCode = "4005 - Pet Fee";
	public static String petRent_AccountRef = "Pet Fee";
	
	public static String petSecurityDeposit_AccountCode = "2050 - Security Deposit"; // Only if first check box is checked in Pet Addendum
	public static String petSecurityDeposit_AccountRef = "Pet Security Deposit";
	
	// **************************** Account Codes - Auto Charges *******************************
	public static String airFilterFee_AccountCode = "4102 - Air Filter Fee";
	public static String airFilterFee_Description = "HVAC Filter Fee";
	
	public static String Rent_AccountCode = "4000 - Rent";
	public static String Rent_AccountRef = "Rent";
	
	public static String PetRent_AccountCode = "4311 - Pet Rent";
	public static String PetRent_Description = "Pet Rent";
	
	
	
	
	
}
