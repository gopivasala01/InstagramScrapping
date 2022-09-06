package PDFScrapper;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDButton;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDComboBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDFieldTree;
import org.apache.pdfbox.pdmodel.interactive.form.PDNonTerminalField;
import org.apache.pdfbox.pdmodel.interactive.form.PDPushButton;
import org.apache.pdfbox.pdmodel.interactive.form.PDRadioButton;
import org.apache.pdfbox.pdmodel.interactive.form.PDTerminalField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;

public class PDFCheckBoxIdentification
{
public static void listFields(PDDocument doc) throws Exception
{
    PDDocumentCatalog catalog =doc.getDocumentCatalog();
    PDAcroForm form = catalog.getAcroForm();
    List<PDField> fields = form.getFields();

    for(PDField field: fields)
    {
        String name = field.getFullyQualifiedName();
        //System.out.println(name);
        String field2[] = field.toString().split(" ");
        for(String x : field2)
        {
        	System.out.println(x);
        }
        if (field instanceof PDTextField || field instanceof PDComboBox)
        {
             Object value = field.getValueAsString();
             System.out.print(name);
             System.out.print(" = ");
             System.out.print(value);
             System.out.println();
        }
        else 
        	if (field instanceof PDPushButton)
            ;
        else
        {
            if (field instanceof PDRadioButton)
            {
                PDRadioButton radioButton = (PDRadioButton)form.getField(name);
                String value=radioButton.getValue();
                System.out.print(name);
                System.out.print(" = ");
                System.out.print(value);
                System.out.println();
                /*List<String> exportValues = ((PDRadioButton) field).getSelectedExportValues();
                for (String string : exportValues)
                {
                    name = field.getFullyQualifiedName();
                    System.out.print(name);
                    System.out.print(" = ");
                    System.out.print(string);
                    System.out.println();
                } */
            }
            else if (field instanceof PDCheckBox)
            {
                PDButton box = (PDButton)field;
                String value = box.getValue();
                System.out.print(name);
                System.out.print(" = ");
                System.out.print(value);
                System.out.println();
            }

        }
    }}

public static void main(String[] args) throws Exception 
{
	PDFCheckBoxIdentification.method1();
	/*
    File file = new File("C:\\Gopi\\Projects\\Property ware\\Lease Close Outs\\PDFS\\Lease_0722_0723_2790_Taylor_Hill_Dr_FL_Bass.pdf");
    PDDocument doc = PDDocument.load(file);
  
    PDDocumentCatalog pdCatalog = doc.getDocumentCatalog();
    PDAcroForm pdAcroForm = pdCatalog.getAcroForm();
    
    PDCheckBox fullTimeSalary = (PDCheckBox) pdAcroForm.getField("Residential");
    if(fullTimeSalary.isChecked()) {
        //log.debug("The person earns a full-time salary");
    	System.out.println("Box is checked");
    } else {
        //log.debug("The person does not earn a full-time salary");
    	System.out.println("Box is not checked");
    }
    */

   
   
    // feld3.setValue("check");
   // PDPage  document = doc.getPage(0);
 //   System.out.println(doc.getPage(1).getBBox());
  // listFields(doc);
}
public static void method1() throws Exception
{
 	PDDocument pdfTemplate = PDDocument.load(new File("C:\\Gopi\\Projects\\Property ware\\Lease Close Outs\\PDFS\\Lease_0722_0723_2790_Taylor_Hill_Dr_FL_Bass.pdf"));
	pdfTemplate.getDocumentCatalog().getAcroForm().getFields().forEach(f -> {
	    listFields(f);              
	});

	// loop over PDNonTerminalField otherwise print field value
	
	
}
public static void listFields(PDField f)
{
    if(f instanceof PDNonTerminalField) {
        ((PDNonTerminalField) f).getChildren().forEach(ntf-> {
            listFields(ntf);
        });         
    }else {
        System.out.println(f.getValueAsString());
    }
}


}