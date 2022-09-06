package CompanyWisePDFDataScrap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import PDFScrapper.PDFClass1;

public class Alabama 
{

	public static void main(String[] args) throws Exception 
	{
		File file = new File("C:\\Gopi\\Projects\\Property ware\\Lease Close Outs\\PDFS\\Florida\\Lease_09.21_09.22_2660_San_Simeon_Way_FL_Aguero_-_Vizcaya.pdf");
		FileInputStream fis = new FileInputStream(file);
		PDDocument document = PDDocument.load(fis);
	    String text = new PDFTextStripper().getText(document);
	     System.out.println(text);
	    System.out.println("------------------------------------------------------------------");
	   // String dates = text.substring(text.indexOf("Commencement Date"), )
	    
	    String commensementDate = text.substring(text.indexOf("Commencement Date:"),text.indexOf("Expiration Date:"));
	    System.out.println("Commensement Date = "+commensementDate.substring(commensementDate.lastIndexOf(":")+1));
	    
	    String expirationDate = text.substring(text.indexOf("Expiration Date:"));
	    System.out.println("Expiration Date = "+expirationDate.substring(expirationDate.indexOf(":")+1,expirationDate.indexOf(":")+16));
	    
	    String tenantRaw = text.substring(text.indexOf("Tenant(s):"));
	    String tenantNames = tenantRaw.substring(tenantRaw.indexOf("Tenant(s):")+10,tenantRaw.indexOf("."));
	    System.out.println("Tenant names = "+tenantNames);
	    
	    String prorated = PDFClass1.textBetweenWords(text, "Tenant will pay Landlord $","as prorated");
	    System.out.println("Prorated Rent = "+prorated.trim());
	    
	    String monthlyRent = PDFClass1.textBetweenWords(text.replace(" ",""), "TenantwillpayLandlordmonthlyrentintheamountof","foreachfullmonth");
	    System.out.println("Monthly Rent "+monthlyRent.trim());
	    
	    Float fullMonthRent = Float.parseFloat(monthlyRent.trim().replaceAll("[-+$^:,]",""))- Float.parseFloat(prorated);
	    
	    System.out.println("Full Monthly Rent "+fullMonthRent);
	    //PDFCheckboxFinder pdfcheckbox = new PdfCheckboxFinder(document);
	   
	    String fullMonthDate = PDFClass1.textBetweenWords(text.replace(" ",""), "Thefirstfullmonth’srentisdueandpayablenotlaterthan",".");
	    System.out.println("Full Month Date = "+fullMonthDate.trim());
	    
	    String adminFee = PDFClass1.textBetweenWords(text, "An annual lease preparation fee in the amount of","prior to the");
	    System.out.println("Admin Fee = "+adminFee.trim());
	    
	    // Check if Pet Agreement is available or not
	    
	    boolean petFlag = text.replace(" ","").contains("PETAGREEMENTADDENDUMTORESIDENTIALLEASECONCERNING");
	    System.out.println(petFlag);

	}

}
