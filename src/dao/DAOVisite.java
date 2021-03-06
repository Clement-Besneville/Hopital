package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
				Medecin c = new Medecin(rs.getInt("compte.id"),rs.getString("compte.nom"),rs.getString("compte.prenom"));
				Patient p=new Patient(rs.getInt("patient.id"),rs.getString("patient.nom"),rs.getString("patient.prenom"));
				v=new Visite(rs.getInt("id"),c,p,rs.getInt("salle"),LocalDate.parse(rs.getString("date_visite")),rs.getInt("prix"));
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

			PreparedStatement ps = conn.prepareStatement("INSERT INTO visite VALUES(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1,0);
			ps.setInt(2,m);
			ps.setInt(3,n);
			ps.setDouble(4,v.getPrix());
			ps.setInt(5,v.getSalle());
			ps.setString(6,v.getDate_visite().toString());

			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				p.setId(rs.getInt(1));
			}

			rs.close();
			
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





	public List<Visite> findAllByIdPatient(Integer id) { 

		List<Visite> visites=new ArrayList<Visite>();
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);

		PreparedStatement ps = conn.prepareStatement("SELECT * from visite JOIN patient on patient.id=visite.id_patient JOIN compte on compte.id=visite.id_medecin");

		ResultSet rs = ps.executeQuery();

		while(rs.next())
		{
			if (rs.getInt("id_patient")==id)
			{
				Medecin c = new Medecin(rs.getInt("compte.id"),rs.getString("compte.nom"),rs.getString("compte.prenom"));
				Patient p=new Patient(rs.getInt("patient.id"),rs.getString("patient.nom"),rs.getString("patient.prenom"));
				visites.add(new Visite(rs.getInt("id"),c,p,rs.getInt("salle"),LocalDate.parse(rs.getString("date_visite")),rs.getInt("prix")));
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

				PreparedStatement ps = conn.prepareStatement("SELECT * from visite JOIN patient on patient.id=visite.id_patient JOIN compte on compte.id=visite.id_medecin");

				ResultSet rs = ps.executeQuery();

				while(rs.next())
				{
					if (rs.getInt("id_medecin")==id)
					{
						Medecin c = new Medecin(rs.getInt("compte.id"),rs.getString("compte.nom"),rs.getString("compte.prenom"));
						Patient p=new Patient(rs.getInt("patient.id"),rs.getString("patient.nom"),rs.getString("patient.prenom"));
						visites.add(new Visite(rs.getInt("id"),c,p,rs.getInt("salle"),LocalDate.parse(rs.getString("date_visite")),rs.getInt("prix")));
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
