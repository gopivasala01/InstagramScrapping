package PDFScrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
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
		///PDFClass1.pdfCheckbox();

		File file = new File("C:\\Gopi\\Projects\\Property ware\\Lease Close Outs\\PDFS\\Lease_1119_1020_1912_E_Chelsea_St_FL_Shaw_M.pdf");
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
	public static String textBetweenWords(String sentence, String firstWord, String secondWord)
	{
	    return sentence.substring(sentence.indexOf(firstWord) + firstWord.length(), 
	        sentence.indexOf(secondWord));
	}
	
	public static void pdfCheckbox() throws Exception
	{
		
	        PDDocument fdeb = null;

	        fdeb = PDDocument.load( new File("C:\\Gopi\\Projects\\Property ware\\Lease Close Outs\\PDFS\\Lease_0722_0723_2790_Taylor_Hill_Dr_FL_Bass.pdf"));

	         PDAcroForm form = fdeb.getDocumentCatalog().getAcroForm();
	         PDField feld3 = form.getField("Pet");
	         feld3.setValue(((PDCheckBox) feld3).getOnValue());
	         //((PDCheckBox) feld3).check();


	         fdeb.save("C:\\Users\\34\\Desktop\\complaintform.pdf");
	         fdeb.close();

	    
	}

	

}
