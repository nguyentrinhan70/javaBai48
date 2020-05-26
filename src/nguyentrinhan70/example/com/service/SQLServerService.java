package nguyentrinhan70.example.com.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLServerService {

	protected Connection conn = null;
	public SQLServerService() {
		// TODO Auto-generated method stub
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = 
					"jdbc:sqlserver://TRINHANNGUYEN\\SQLEXPRESS:1433; "
					+ "databaseName=dbQuanLySanPham; integratedSecurity = true;";

			conn = DriverManager.getConnection(connectionUrl);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
