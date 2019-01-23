package com.mx.rest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mx.rest.dto.Persona;

public class PersonaDao {

	public List<Persona> getPersonas() {
		List<Persona> personas = new ArrayList<Persona>();
		String sql = "select * from Persona";

		try {
			Statement st = ConexionBD.conectar().prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			rs = st.getResultSet();
			while (rs.next()) {
				Persona p = new Persona();
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setEdad(rs.getInt("edad"));
				personas.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personas;
	}

	public List<Persona> getPersonas(String nombre) {
		List<Persona> personas = new ArrayList<Persona>();
		String sql = "SELECT id,nombre,apellido,edad FROM Persona WHERE nombre=?;";
		try {
			PreparedStatement st = ConexionBD.conectar().prepareStatement(sql);
			st.setString(1, nombre);
			ResultSet rs = st.executeQuery();
			rs = st.getResultSet();
			while (rs.next()) {
				Persona p = new Persona();
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setEdad(rs.getInt("edad"));
				personas.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personas;
	}

	public void addPersona(Persona persona) {
		PreparedStatement ps = null;
		String sql = "INSERT INTO Persona (id,nombre,apellido,edad) VALUES(?,?,?,?)";
		try {
			ps = ConexionBD.conectar().prepareStatement(sql);
			ps.setInt(1, persona.getId());
			ps.setString(2, persona.getNombre());
			ps.setString(3, persona.getApellido());
			ps.setInt(4, persona.getEdad());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Persona getPersona(int id) {
		Persona persona = new Persona();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM Persona  where id=? ";
		try {
			ps = ConexionBD.conectar().prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeQuery();
			rs = ps.getResultSet();
			while (rs.next()) {
				persona.setId(rs.getInt("id"));
				persona.setNombre(rs.getString("nombre"));
				persona.setApellido(rs.getString("apellido"));
				persona.setEdad(rs.getInt("edad"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return persona;
	}

	public void deletePersona(int id) {
		PreparedStatement ps = null;
		String sql = " DELETE FROM  Persona  WHERE id=?";
		try {
			ps = ConexionBD.conectar().prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
