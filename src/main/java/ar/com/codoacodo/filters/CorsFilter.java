package ar.com.codoacodo.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = {"/*"})
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        List<String> origenesPermitidos = List.of("http://127.0.0.1:5500", "http://localhost:5500");

        String origin = ((HttpServletRequest) request).getHeader("origin");

        if(origin != null && origenesPermitidos.contains(origin)){
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", origin);
        }

        filterChain.doFilter(request, response);
    }
}
