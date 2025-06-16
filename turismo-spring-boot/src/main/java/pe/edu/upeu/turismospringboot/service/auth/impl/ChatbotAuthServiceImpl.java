package pe.edu.upeu.turismospringboot.service.auth.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.turismospringboot.model.dto.auth.ChatbotAuthRequest;
import pe.edu.upeu.turismospringboot.model.dto.auth.ChatbotAuthResponse;
import pe.edu.upeu.turismospringboot.service.auth.ChatbotAuthService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatbotAuthServiceImpl implements ChatbotAuthService {

    private static final String CHATBOT_CLIENT_ID = "turismobot-client";
    private static final String CHATBOT_CLIENT_SECRET = "CHATBOT_SECRET_TOKEN_2024";
    private static final String CHATBOT_TOKEN = "CHATBOT_SECRET_TOKEN_2024";
    private static final Long TOKEN_EXPIRY_HOURS = 24L;

    @Override
    public ChatbotAuthResponse authenticate(ChatbotAuthRequest request) {
        ChatbotAuthResponse response = new ChatbotAuthResponse();
        
        if (CHATBOT_CLIENT_ID.equals(request.getClientId()) && 
            CHATBOT_CLIENT_SECRET.equals(request.getClientSecret())) {
            
            response.setToken(generateChatbotToken());
            response.setTokenType("Bearer");
            response.setExpiresIn(TOKEN_EXPIRY_HOURS * 3600); // En segundos
            response.setScope("chatbot:read chatbot:write");
            response.setValid(true);
            response.setMessage("Autenticación exitosa");
            
        } else {
            response.setValid(false);
            response.setMessage("Credenciales inválidas");
        }
        
        return response;
    }

    @Override
    public ChatbotAuthResponse validateToken(String token) {
        ChatbotAuthResponse response = new ChatbotAuthResponse();
        
        if (CHATBOT_TOKEN.equals(token)) {
            response.setValid(true);
            response.setMessage("Token válido");
            response.setTokenType("Bearer");
            response.setScope("chatbot:read chatbot:write");
        } else {
            response.setValid(false);
            response.setMessage("Token inválido");
        }
        
        return response;
    }

    @Override
    public String generateChatbotToken() {
        // En producción, esto debería ser un JWT con expiración
        return CHATBOT_TOKEN + "_" + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }
}
