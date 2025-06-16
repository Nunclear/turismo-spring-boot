package pe.edu.upeu.turismospringboot.service.auth;

import pe.edu.upeu.turismospringboot.model.dto.auth.ChatbotAuthRequest;
import pe.edu.upeu.turismospringboot.model.dto.auth.ChatbotAuthResponse;

public interface ChatbotAuthService {
    ChatbotAuthResponse authenticate(ChatbotAuthRequest request);
    ChatbotAuthResponse validateToken(String token);
    String generateChatbotToken();
}
