package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.*;

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
				v.setNumero(rs.getInt("id"));
				v.setCompte(c);
				v.setPatient(p);
				v.setSalle(rs.getInt("salle"));
				v.setDate_visite(LocalDate.parse(rs.getString("date_visite")));
			}
			rs.close();
			ps.close();
			conn.close();


		} catch (ClassNotFoundException | SQLException e) {
			//e.printStackTrace();
		}
		return p;
	}

	@Override
	public List<Visite> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Visite o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Visite o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}





	public List<Visite> findAllByIdPatient(Integer id) { 

		List<Visite> visites=new ArrayList<Visite>();
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

		PreparedStatement ps = conn.prepareStatement("SELECT * from visite");

		ResultSet rs = ps.executeQuery();

		while(rs.next())
		{
			if (rs.getString("id_patient").equals(id))
			{
				visites.add(new Visite(rs.getInt("numero"), rs.getInt("id_patient"),rs.getInt("id_medecin"),rs.getInt("prix"),rs.getInt("salle"),
						LocalDate.parse(rs.getString("date_visite"))));
			}
		}

		rs.close();
		ps.close();
		conn.close();

	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}

	return visites;

	}



		public List<Visite> findAllByIdMedecin(Integer id) {

			List<Visite> visites=new ArrayList<Visite>();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

				PreparedStatement ps = conn.prepareStatement("SELECT * from visite");

				ResultSet rs = ps.executeQuery();

				while(rs.next())
				{
					if (rs.getString("id_medecin").equals(id))
					{
						visites.add(new Visite(rs.getInt("numero"), rs.getInt("id_patient"),rs.getInt("id_medecin"),rs.getInt("prix"),rs.getInt("salle"),
								LocalDate.parse(rs.getString("date_visite"))));
					}
				}

				rs.close();
				ps.close();
				conn.close();

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			return visites;
			
		}

	}
