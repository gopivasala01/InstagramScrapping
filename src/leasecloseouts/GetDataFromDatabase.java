package leasecloseouts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetDataFromDatabase 
  {
	static File file;
	static FileInputStream fis;
	static FileOutputStream fos;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	
     public static void getData() throws IOException 
     {
    	// FileOutputStream outputStream =null;
    	// try
    	// {
    	file  = new File("C:\\Gopi\\Projects\\Property ware\\Lease Close Outs\\LeaseInfoTable.xlsx");
    	//if(file.exists())
    	//{
    		
    	//fis = new FileInputStream(file);
    	//workbook = new XSSFWorkbook(fis);
    	//}
    	//else 
    	//{
    		//file.delete();
    		//file  = new File("C:\\Gopi\\Projects\\Property ware\\Paginated Reports\\Agents Info.xlsx");
    	//	 outputStream = new FileOutputStream(new File("C:\\Gopi\\Projects\\Property ware\\Paginated Reports\\Agents Info.xlsx"));
    	//	outputStream.close();
    	//}
    	// }
    	// catch(Exception e)
    	// {
    		 //file.createNewFile();
    		 //file  = new File("C:\\Gopi\\Projects\\Property ware\\Paginated Reports\\Agents Info.xlsx");
    	 //}
    	 fis = new FileInputStream(file);
    	 workbook = new XSSFWorkbook(fis);
    	sheet = workbook.getSheetAt(0);
    	if(sheet !=null)
    	{
    		workbook.removeSheetAt(0);
    		sheet = workbook.createSheet();
    	}
	        String Company = "";
	        String Portfolio ="";
	        String BuildingName ="";
	        String statusID ="";
	        int rowNumber =0;
	        try {
	            String connectionUrl = "jdbc:sqlserver://azrsrv001.database.windows.net;databaseName=HomeRiverDB;user=service_sql02;password=xzqcoK7T";
	            Connection con = null;
	            Statement stmt = null;
	            ResultSet rs = null;
	                //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	                con = DriverManager.getConnection(connectionUrl);
	                String SQL = "Select Company, Portfolio,BuildingName, StatusID from Automation.LeaseInfo where StatusID=2";
	                stmt = con.createStatement();
	                rs = stmt.executeQuery(SQL);
	                Row row = sheet.createRow(0);
	                Cell cell = row.createCell(0);
	                cell.setCellValue("Company");
	                
	               // Row row2 = sheet.createRow(0);
	                Cell cell2 = row.createCell(1);
	                cell2.setCellValue("Portfolio");
	                
	               // Row row2 = sheet.createRow(0);
	                Cell cell3 = row.createCell(2);
	                cell3.setCellValue("BuildingName");
	                
	                Cell cell4 = row.createCell(2);
	                cell3.setCellValue("StatusID");
	                while (rs.next())
	                {
	                	Company =  (String) rs.getObject(1);
	                	Portfolio = (String) rs.getObject(2);
	                	BuildingName = (String) rs.getObject(3);
	                	statusID = (String) rs.getObject(4);
	                	
	                	Row row2 = sheet.createRow(rowNumber++);
		                Cell cell5 = row2.createCell(0);
		                cell5.setCellValue(Company);
		                
		                Cell cell6 = row2.createCell(1);
		                cell6.setCellValue(Portfolio);
		                
		                Cell cell7 = row2.createCell(2);
		                cell7.setCellValue(BuildingName);
		                
		                Cell cell8 = row2.createCell(3);
		                cell8.setCellValue(statusID);
		                
		                
		                Cell cell9 = row2.createCell(5);
		                cell9.setCellValue("Pending");
		                
	                    System.out.println(Company);
	                    System.out.println(Portfolio);
	                    System.out.println(BuildingName);
	                }
	                rs.close();
	                
	                fos = new FileOutputStream(file);
	                workbook.write(fos);
	                workbook.close();
	                fos.close();
	        }
	        catch (Exception e) 
	        {
              e.printStackTrace();
	           // return "ERROR while retrieving data: " + e.getMessage();
	        }
	       // return output;
	    
   }
   
   
   
  }


