package com.Dao;
import java.sql.*;
import java.sql.DriverManager;

import com.Vo.RegisterVo;

import com.Dao.*;

import com.Vo.*;

import com.Dao.*;

import com.Vo.*;
import java.util.*;


public class RegisterDao {
	public void save(RegisterVo regVo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jdbc", "root", "root@root");
			Statement s = con.createStatement();

			s.executeUpdate("insert into mvc (firstname,lastname) values('" + regVo.getFirstName() + "','" +regVo.getLastName()+ "')");

			s.close();
			con.close();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public List search(String str) {
		List ls = new ArrayList();

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jdbc", "root", "root@root");
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select * from mvc");

			while (rs.next()) {
				RegisterVo v = new RegisterVo();
				v.setId(rs.getInt("id"));
				v.setFirstName(rs.getString("firstname"));
				v.setLastName(rs.getString("lastname"));
				ls.add(v);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;

	}
	public List edit(String id) {

		List ls = new ArrayList();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jdbc", "root", "root@root");
			Statement s = con.createStatement();
			ResultSet r = s.executeQuery("select * from mvc where id='" + id + "'");
			while (r.next()) {

				RegisterVo v1 = new RegisterVo();
				v1.setId(r.getInt("id"));
				v1.setFirstName(r.getString("firstname"));
				v1.setLastName(r.getString("lastname"));

				ls.add(v1);
			}

			s.close();
			con.close();
		} catch (Exception e) {

			e.printStackTrace();

		}

		return ls;

	}
	public void update(RegisterVo v) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jdbc", "root", "root@root");
			Statement s = con.createStatement();
			s.executeUpdate(
					"update mvc set firstname='" + v.getFirstName() + "' , lastname='" + v.getLastName() + "' where id='" + v.getId() + "'");
			s.close();
			con.close();
		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	public void delete(String id) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jdbc", "root", "root@root");
			Statement s = con.createStatement();
			s.executeUpdate("delete from mvc where id='" + id + "'");
			s.close();
			con.close();
		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	
}
