package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.time.LocalDate;
import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.repository.MySQLOradorRepository;
import ar.com.codoacodo.repository.OradorRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/orador/nuevo")
public class NuevoOradorController extends HttpServlet {

	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response)
	throws ServletException, IOException {

		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String tema = request.getParameter("tema");
		
		// validaciones 
		
		//if(nombre == null ││ apellido == null ││ email == null ││ tema == null) {
			
		//}
		OradorRepository repository = new MySQLOradorRepository();
		Orador nuevoOrador = new Orador(nombre, apellido, email, tema, LocalDate.now());

		repository.save(nuevoOrador);

		StringBuilder jsonBuilder = new StringBuilder();
		jsonBuilder.append("{");
		jsonBuilder.append("\"id\":").append(nuevoOrador.getId()).append(",");
		jsonBuilder.append("\"nombre\":\"").append(nuevoOrador.getNombre()).append("\",");
		jsonBuilder.append("\"apellido\":\"").append(nuevoOrador.getApellido()).append("\",");
		jsonBuilder.append("\"email\":\"").append(nuevoOrador.getMail()).append("\",");
		jsonBuilder.append("\"tema\":\"").append(nuevoOrador.getTema()).append("\",");
		jsonBuilder.append("\"fecha_alta\":\"").append(nuevoOrador.getFechaAlta()).append("\"");
		jsonBuilder.append("}");

		// respondo al frontend con el orador
		// Configurar la respuesta HTTP
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonBuilder);
	}
}
