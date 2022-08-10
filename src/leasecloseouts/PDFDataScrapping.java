package leasecloseouts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFDataScrapping 
{
	public void getDataFromPDF() throws Exception
	{
	File file = PropertyWare.getLastModified();
			//new File("C:\\Gopi\\Projects\\Property ware\\PDFS\\Lease_05.22_04.23_110_Southhaven_Cir_FL_Safi_.pdf");
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
	}
}
