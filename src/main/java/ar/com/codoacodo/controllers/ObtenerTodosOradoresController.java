package ar.com.codoacodo.controllers;

import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.repository.MySQLOradorRepository;
import ar.com.codoacodo.repository.OradorRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/api/oradores/")
public class ObtenerTodosOradoresController extends HttpServlet {

	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {

		OradorRepository repository = new MySQLOradorRepository();

		List<Orador> oradores = repository.findAll();

		// Construir manualmente el JSON
		StringBuilder jsonBuilder = new StringBuilder();
		jsonBuilder.append("[");
		for (Orador orador : oradores) {
			jsonBuilder.append("{");
			jsonBuilder.append("\"id\":").append(orador.getId()).append(",");
			jsonBuilder.append("\"nombre\":\"").append(orador.getNombre()).append("\",");
			jsonBuilder.append("\"apellido\":\"").append(orador.getApellido()).append("\",");
			jsonBuilder.append("\"email\":\"").append(orador.getMail()).append("\",");
			jsonBuilder.append("\"tema\":\"").append(orador.getTema()).append("\"");
			// Agregar más campos si es necesario
			jsonBuilder.append("},");
		}
		if (!oradores.isEmpty()) {
			jsonBuilder.deleteCharAt(jsonBuilder.length() - 1); // Eliminar la última coma
		}
		jsonBuilder.append("]");

		// Configurar la respuesta HTTP
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// Responder al frontend con el JSON
		response.getWriter().print(jsonBuilder);
	}
}