package PDFScrapper;

import com.spire.pdf.PdfDocument;

import com.spire.pdf.fields.PdfField;

import com.spire.pdf.widget.*;

 

import java.io.FileWriter;

import java.io.IOException;


public class PDFFormFields {

    public static void main(String[] args)
    {
        //Load PDF document

        PdfDocument pdf = new PdfDocument();

        pdf.loadFromFile("C:\\Gopi\\Projects\\Property ware\\Lease Close Outs\\PDFS\\Lease_0722_0723_2790_Taylor_Hill_Dr_FL_Bass.pdf");

 

        //Get form fields

        PdfFormWidget formWidget = (PdfFormWidget)pdf.getForm();

 

        StringBuilder sb = new StringBuilder();

        //Loop through the form field widget collection and extract the value of each field

        for (int i = 0; i < formWidget.getFieldsWidget().getCount(); i++)

        {

            PdfField field = (PdfField)formWidget.getFieldsWidget().getList().get(i);

            if (field instanceof PdfTextBoxFieldWidget)

            {

                PdfTextBoxFieldWidget textBoxField = (PdfTextBoxFieldWidget)field ;

                //Get text of textbox

                String text = textBoxField.getText();

                sb.append("The text in textbox is: " + text + "\r\n");

            }

 

            if (field instanceof PdfListBoxWidgetFieldWidget)

            {

                PdfListBoxWidgetFieldWidget listBoxField = (PdfListBoxWidgetFieldWidget)field;

                sb.append("Listbox items are: \r\n");

                //Get values of listbox

               PdfListWidgetItemCollection items = listBoxField.getValues();

 

                for (PdfListWidgetItem item : (Iterable<PdfListWidgetItem>) items)

                {

                    sb.append(item.getValue() + "\r\n");

                }

                //Get selected value

                String selectedValue = listBoxField.getSelectedValue();

                sb.append("The selected value in the listbox is: " + selectedValue + "\r\n");

            }


            if (field instanceof PdfComboBoxWidgetFieldWidget)

            {

                PdfComboBoxWidgetFieldWidget comBoxField = (PdfComboBoxWidgetFieldWidget)field ;

                sb.append("comBoxField items are: \r\n");

                //Get values of comboBox

                PdfListWidgetItemCollection items = comBoxField.getValues();

 

                for (PdfListWidgetItem item : (Iterable<PdfListWidgetItem>) items)

                {

                    sb.append(item.getValue() + "\r\n");

                }

                //Get selected value

                String selectedValue = comBoxField.getSelectedValue();

                sb.append("The selected value in the comBoxField is: " + selectedValue + "\r\n");

            }

 

            if (field instanceof PdfRadioButtonListFieldWidget)

            {

                PdfRadioButtonListFieldWidget radioBtnField = (PdfRadioButtonListFieldWidget)field;

                //Get value of radio button

                String value = radioBtnField.getValue();

 

               sb.append("The text in radioButtonField is: " + value + "\r\n");

            }

 

            if (field instanceof PdfCheckBoxWidgetFieldWidget)

            {

                PdfCheckBoxWidgetFieldWidget checkBoxField = (PdfCheckBoxWidgetFieldWidget)field;

                //Get the checked state of the checkbox

                boolean state = checkBoxField.getChecked();

                sb.append("Is the checkBox checked? " + state + "\r\n");

            }

        }

 

        try {

            //Write text into a .txt file

            FileWriter writer = new FileWriter("GetAllValues.txt");

            writer.write(sb.toString());

            writer.flush();

        } catch (IOException e) {

            e.printStackTrace();

        }

 

        pdf.close();

    }
}

