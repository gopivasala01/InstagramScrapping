package leasecloseouts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertDataToDatabase 
{
	public static void main(String[] args) throws Exception 
	{
		String connectionUrl = "jdbc:sqlserver://azrsrv001.database.windows.net;databaseName=HomeRiverDB;user=service_sql02;password=xzqcoK7T";
        Connection con = null;
        Statement stmt = null;
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            String SQL = "Select Company, Portfolio,BuildingName, StatusID from Automation.LeaseInfo where StatusID=2";
            stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            
	}

}
