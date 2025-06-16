package pe.edu.upeu.turismospringboot.model.dto.auth;

import lombok.Data;

@Data
public class ChatbotAuthRequest {
    private String clientId;
    private String clientSecret;
    private String scope;
}
