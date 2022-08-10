package PDFScrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class PDFClass1 {

	public static void main(String[] args) throws Exception
	{
		/*
		Pattern NUMBERS_WITH_DOTS = Pattern.compile("\\d+\\.$");
		Matcher m = NUMBERS_WITH_DOTS.matcher("Gopi 1. test");
		//System.out.println(m.matches());
		//m.matches();
		if (m.find()) {
		    String urlStr = m.group(0);
		    System.out.println(urlStr);
		}
	   */
		//System.out.println(NUMBERS_WITH_DOTS.matcher("Gopi 1.").group(0));
		

		File file = new File("C:\\Gopi\\Projects\\Property ware\\PDFS\\Lease_05.22_04.23_110_Southhaven_Cir_FL_Safi_.pdf");
		FileInputStream fis = new FileInputStream(file);
		PDDocument document = PDDocument.load(fis);
	    String text = new PDFTextStripper().getText(document);
	   // System.out.println(text);
	    System.out.println("------------------------------------------------------------------");
	   // String dates = text.substring(text.indexOf("Commencement Date"), )
	    
	    String commensementDate = text.substring(text.indexOf("Commencement Date:"),text.indexOf("Expiration Date:"));
	    System.out.println("Commensement Date = "+commensementDate.substring(commensementDate.lastIndexOf(":")+1));
	    
	    String expirationDate = text.substring(text.indexOf("Expiration Date:"));
	    System.out.println("Expiration Date = "+expirationDate.substring(expirationDate.indexOf(":")+1,expirationDate.indexOf(":")+16));
	    
	    String tenantRaw = text.substring(text.indexOf("Tenant(s):"));
	    String tenantNames = tenantRaw.substring(tenantRaw.indexOf("Tenant(s):")+10,tenantRaw.indexOf("."));
	    System.out.println("Tenant names = "+tenantNames);
	    
	    //PDFCheckboxFinder pdfcheckbox = new PdfCheckboxFinder(document);
	   
	    
	    
	    
	}

}
