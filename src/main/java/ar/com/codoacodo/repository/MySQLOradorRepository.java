package ar.com.codoacodo.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ar.com.codoacodo.entity.Orador;

public class MySQLOradorRepository implements OradorRepository {

	public void save(Orador orador) {
		String sql = "insert into orador (nombre,apellido,mail,tema,fecha_alta) values(?,?,?,?,?)";
		
		try(Connection conn = AdministradorDeConexiones.getConnection()) {
			//sql injection!!!
			PreparedStatement statement  = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			//cargar los ? con los valores 
			statement.setString(1, orador.getNombre());
			statement.setString(2, orador.getApellido());
			statement.setString(3, orador.getMail());
			statement.setString(4, orador.getTema());
			statement.setDate(5, Date.valueOf(orador.getFechaAlta()));
			statement.executeUpdate();
			ResultSet res = statement.getGeneratedKeys();

			if (res.next())
				orador.setId(res.getLong(1));

		}catch (Exception e) {
			throw new IllegalArgumentException("No se pudo crear el orador",e);
		}
	}

	public Orador getById(Long id) {
		String sql = "select id, nombre, apellido, tema, mail, fecha_alta from orador where id = ?";
		
		Orador orador = null;
		try(Connection conn = AdministradorDeConexiones.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setLong(1, id);
		
			ResultSet res = statement.executeQuery();//SELECT
			
			//hay datos?
			if(res.next()) {
				//obtengo los datos desde el ResultSet
				Long _id = res.getLong(1);
				String nombre = res.getString(2);
				String apellido = res.getString(3);
				String tema = res.getString(4);
				String email = res.getString(5);
				Date fechaAlta = res.getDate(6);
				
				orador = new Orador(id,nombre, apellido, email, tema, fechaAlta.toLocalDate());
			}
		}catch (Exception e) {
			throw new IllegalArgumentException("No se pudo obtener el orador", e);
		}
		return orador;
	}

	@Override
	public void update(Orador orador) {
		String sql = "update orador "
				+ "set nombre=?, apellido=?, tema=?, mail=?"
				+ "where id = ?";
		//try with resource
		try(Connection conn = AdministradorDeConexiones.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, orador.getNombre());
			statement.setString(2, orador.getApellido());
			statement.setString(3, orador.getTema());
			statement.setString(4, orador.getMail());
			statement.setLong(5, orador.getId());
			
			statement.executeUpdate();
		}catch(Exception e) {
			throw new IllegalArgumentException("No se pudo actualizar el orador", e);
		}
	}

	@Override
	public void delete(Long id) {
		String sql = "delete from orador where id = ?";
				
		try(Connection conn = AdministradorDeConexiones.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.executeUpdate();
		}catch(Exception e) {
			throw new IllegalArgumentException("No se pudo eliminar el orador", e);
		}
	}

	@Override
	public List<Orador> findAll() {
		String sql = "select id, nombre, apellido, tema, mail, fecha_alta from orador";
		
		List<Orador> oradores = new ArrayList<>();//ver bien power en Spring!
		try(Connection conn = AdministradorDeConexiones.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet res = statement.executeQuery();//SELECT
			
			//hay datos?
			while(res.next()) {
				//obtengo los datos desde el ResultSet
				Long _id = res.getLong(1);
				String nombre = res.getString(2);
				String apellido = res.getString(3);
				String tema = res.getString(4);
				String email = res.getString(5);
				Date fechaAlta = res.getDate(6);
				
				Orador orador = new Orador(_id,nombre, apellido, email, tema,fechaAlta.toLocalDate());
				oradores.add(orador);
			}
		}catch (Exception e) {
			throw new IllegalArgumentException("No se pudo obtener el orador", e);
		}
		
		return oradores;
	}
}