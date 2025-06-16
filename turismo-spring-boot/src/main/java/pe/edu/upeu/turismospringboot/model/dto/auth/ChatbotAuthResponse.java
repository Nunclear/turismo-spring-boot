package pe.edu.upeu.turismospringboot.model.dto.auth;

import lombok.Data;

@Data
public class ChatbotAuthResponse {
    private String token;
    private String tokenType;
    private Long expiresIn;
    private String scope;
    private Boolean valid;
    private String message;
}
