package CompanyWisePDFDataScrap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import PDFScrapper.PDFClass1;
import leasecloseouts.PropertyWare;

public class Arizona 
{
	
	public void arizona() throws Exception 
	{
		File file = PropertyWare.getLastModified();
		//File file = new File("C:\\Gopi\\Projects\\Property ware\\Lease Close Outs\\PDFS\\Arizona\\Lease_922_923_3661_W_Alice_Ave_AZ_Duff_COPYp.pdf");
		FileInputStream fis = new FileInputStream(file);
		PDDocument document = PDDocument.load(fis);
	    String text = new PDFTextStripper().getText(document);
	    System.out.println(text);
	    System.out.println("------------------------------------------------------------------");
	   // String dates = text.substring(text.indexOf("Commencement Date"), )
	    
	    String commensementDate = text.substring(text.indexOf(AppConfig.AZ_commencementDate_Prior)+AppConfig.AZ_commencementDate_Prior.length(),text.indexOf(AppConfig.AZ_commencementDate_After));
	    System.out.println("Commensement Date = "+commensementDate.trim());//.substring(commensementDate.lastIndexOf(":")+1));
	    
	    String expirationDate = text.substring(text.indexOf(AppConfig.AZ_expirationDate_Prior)+AppConfig.AZ_expirationDate_Prior.length(),text.indexOf(AppConfig.AZ_expirationDate_After));
	    System.out.println("Expiration Date = "+expirationDate.trim());
	    
	    String proratedRent = text.substring(text.indexOf(AppConfig.AZ_proratedRent_Prior)+AppConfig.AZ_proratedRent_Prior.length(),text.indexOf(AppConfig.AZ_proratedRent_After));
	    //String tenantNames = tenantRaw.substring(tenantRaw.indexOf("Tenant(s):")+10,tenantRaw.indexOf("."));
	    System.out.println("Prorated Rent = "+proratedRent);
	    
	    String proratedRentDate = text.substring(text.indexOf(AppConfig.AZ_proratedRentDate_Prior)+AppConfig.AZ_proratedRentDate_Prior.length(),text.indexOf(AppConfig.AZ_proratedRentDate_After));
	    System.out.println("Prorated Rent Date= "+proratedRentDate.trim());
	    
	    String monthlyRent = text.substring(text.indexOf(AppConfig.AZ_fullRent_Prior)+AppConfig.AZ_fullRent_Prior.length(),text.indexOf(AppConfig.AZ_fullRent_After));
	    System.out.println("Monthly Rent "+monthlyRent.trim());
	    
	   //Float fullMonthRent = Float.parseFloat(monthlyRent.trim().replaceAll("[-+$^:,]",""))- Float.parseFloat(proratedRentDate);
	    
	    //System.out.println("Full Monthly Rent "+monthlyRent);
	    //PDFCheckboxFinder pdfcheckbox = new PdfCheckboxFinder(document);
	   
	    String adminFee = text.substring(text.indexOf(AppConfig.AZ_adminFee_Prior)+AppConfig.AZ_adminFee_Prior.length()).split(" ")[0];
	    System.out.println("Admin Fee = "+adminFee.trim());
	    
	    String airFilterFee = text.substring(text.indexOf(AppConfig.AZ_airFilterFee_Prior)+AppConfig.AZ_airFilterFee_Prior.length(),text.indexOf(AppConfig.AZ_airFilterFee_After));
	    System.out.println("Air Filter Fee = "+airFilterFee.trim());
	    
	    String earlyTermiantion = text.substring(text.indexOf(AppConfig.AZ_earlyTerminationFee_Prior)+AppConfig.AZ_earlyTerminationFee_Prior.length(),text.indexOf(AppConfig.AZ_earlyTerminationFee_After));
	    System.out.println("Early Termination  = "+earlyTermiantion.trim());
	    
	    String occupants = text.substring(text.indexOf(AppConfig.AZ_occupants_Prior)+AppConfig.AZ_occupants_Prior.length(),text.indexOf(AppConfig.AZ_occupants_After));
	    System.out.println("Occupants = "+occupants.trim());
	    
	    String lateChargeDay = text.substring(text.indexOf(AppConfig.AZ_lateChargeDay_Prior)+AppConfig.AZ_lateChargeDay_Prior.length(),text.indexOf(AppConfig.AZ_lateChargeDay_After));
	    System.out.println("Late Charge Day = "+lateChargeDay.trim());
	    
	    String lateChargeFee = text.substring(text.indexOf(AppConfig.AZ_lateFee_Prior)+AppConfig.AZ_lateFee_Prior.length(),text.indexOf(AppConfig.AZ_lateFee_After));
	    System.out.println("Late Charge Fee = "+lateChargeFee.trim());
	    
	    // Check if Pet Agreement is available or not
	    
	    boolean petFlag = text.replace(" ","").contains("PETAGREEMENTADDENDUMTORESIDENTIALLEASECONCERNING");
	    System.out.println(petFlag);

	}

}
