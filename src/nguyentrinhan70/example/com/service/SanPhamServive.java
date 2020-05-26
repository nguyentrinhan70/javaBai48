package nguyentrinhan70.example.com.service;

import java.security.spec.DSAGenParameterSpec;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import nguyentrinhan70.example.com.model.SanPham;

public class SanPhamServive extends SQLServerService {
	
	public int luuSanPham(SanPham sp){
		try{
			String sql = "insert into SanPham values(?,?,?,?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, sp.getMaSp());
			preparedStatement.setString(2, sp.getTenSp());
			preparedStatement.setInt(3, sp.getSoLuong());
			preparedStatement.setInt(4, sp.getDonGia());
			preparedStatement.setString(5, sp.getMaDM());
			preparedStatement.setInt(6, 0);
			return preparedStatement.executeUpdate();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return -1;
	}

	public ArrayList<SanPham> docSanPhamTheoDanhMuc(String madm){
		ArrayList<SanPham> dsSP = new ArrayList<SanPham>();
		try{
			
			String sql = "select * From SanPham where MaDM = ? and IsDeleted = 0";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, madm);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				SanPham sp = new SanPham();
				sp.setMaSp(resultSet.getString(1));
				sp.setTenSp(resultSet.getString(2));
				sp.setSoLuong(resultSet.getInt(3));
				sp.setDonGia(resultSet.getInt(4));
				sp.setMaDM(resultSet.getString(5));
				sp.setIsDeleted(0);
				dsSP.add(sp);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return dsSP;
	}
}
