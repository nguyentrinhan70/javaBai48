package nguyentrinhan70.example.com.service;

import java.beans.Statement;
import java.sql.ResultSet;
import java.util.Vector;

import nguyentrinhan70.example.com.model.DanhMuc;

public class DanhMucService extends SQLServerService {
	
	public Vector<DanhMuc> docToanBoDanhMuc(){
		Vector<DanhMuc> vec = new Vector<DanhMuc>();
		try{
			
			String sql = "Select * From DanhMuc where isDeleted = 0";
			java.sql.Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				DanhMuc dm = new DanhMuc();
				dm.setMaDM(resultSet.getString(1));
				dm.setTenDM(resultSet.getString(2));
				dm.setIsDeleted(0);
				vec.add(dm);
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return vec;
	}

}
