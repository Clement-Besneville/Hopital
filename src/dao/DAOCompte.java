package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Compte;
import model.Medecin;
import model.Secretaire;



public class DAOCompte implements IDAO <Compte, Integer> {

	public static Compte seConnecter(String login,String password) 
	{
		Compte connect = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * from compte where login=? and password=?");
			ps.setString(1,login);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			Compte c=null;

			while(rs.next()) 
			{

				if(rs.getString("type_compte").equals("Secretaire")) 
				{
					connect = new Secretaire(rs.getInt("id"),rs.getString("login"), rs.getString("password"));
				}
				else if(rs.getString("type_compte").equals("Medecin")) 
				{
					connect = new Medecin(rs.getInt("id"),rs.getString("login"), rs.getString("password"));
				}
			}

			rs.close();
			ps.close();
			conn.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connect;

	}

	public Compte findById(Integer id) {
		Compte c=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * from compte where id=?");
			ps.setInt(1,id);


			ResultSet rs = ps.executeQuery();


			while(rs.next()) 
			{

				if(rs.getString("typecompte").equals("Secretaire")) 
				{
					c = new Secretaire((Integer)rs.getInt("id"), rs.getString("login"), rs.getString("password"));
				}
				else if(rs.getString("typecompte").equals("Medecin")) 
				{
					c = new Medecin((Integer)rs.getInt("id"),rs.getString("login"), rs.getString("password"));
				}
			}
			rs.close();
			ps.close();
			conn.close();


		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return c;

	}

	@Override
	public List<Compte> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Compte o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Compte o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

}
