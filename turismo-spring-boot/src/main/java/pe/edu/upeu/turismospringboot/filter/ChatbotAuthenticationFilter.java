package pe.edu.upeu.turismospringboot.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ChatbotAuthenticationFilter extends OncePerRequestFilter {

    private static final String CHATBOT_TOKEN = "CHATBOT_SECRET_TOKEN_2024";
    private static final String CHATBOT_TOKEN_HEADER = "X-Chatbot-Token";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                  FilterChain filterChain) throws ServletException, IOException {
        
        String requestURI = request.getRequestURI();
        
        // Solo aplicar filtro a endpoints del chatbot
        if (requestURI.startsWith("/chatbot/")) {
            String token = request.getHeader(CHATBOT_TOKEN_HEADER);
            
            if (token == null || !CHATBOT_TOKEN.equals(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"error\":\"Token de chatbot inválido\"}");
                return;
            }
        }
        
        filterChain.doFilter(request, response);
    }
}
