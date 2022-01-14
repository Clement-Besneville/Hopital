package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import model.*;
import dao.*;

public class DAOVisite implements IDAO<Visite,Integer> {

	@Override
	public Visite findById(Integer id) {
		Visite v=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM visite JOIN compte ON id_medecin=compte.id JOIN patient ON id_patient=patient.id WHERE visite.id=?");
			ps.setInt(1,id);


			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				Compte c = new Compte(rs.getInt("compte.id"),rs.getString("compte.nom"),rs.getString("compte.prenom"),typeCompte.Medecin);
				Patient p=new Patient(rs.getInt("patient.id"),rs.getString("patient.nom"),rs.getString("patient.prenom"));
				v=new Visite(rs.getInt("id"),p,c,rs.getInt("salle"),LocalDate.parse(rs.getString("date_visite")));
			}
			rs.close();
			ps.close();
			conn.close();


		} catch (ClassNotFoundException | SQLException e) {
			//e.printStackTrace();
		}
		return v;
	}

	@Override
	public List<Visite> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Visite v) {
		
		Compte c=v.getCompte();
		Patient p=v.getPatient();
		Integer n=c.getId();
		Integer m=p.getId();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

			PreparedStatement ps = conn.prepareStatement("INSERT INTO visite VALUES(?,?,?,?,?,?)");

			ps.setInt(1,1);
			ps.setInt(2,n);
			ps.setInt(3,m);
			ps.setDouble(4,v.getPrix());
			ps.setInt(5,v.getSalle());
			ps.setString(6,v.getDate_visite().toString());

			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
		
	

	@Override
	public void update(Visite o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	public List<Visite> findAllByIdPatient() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	public List<Visite> findAllByIdMedecin() {
		// TODO Auto-generated method stub
		return null;
	}

}
