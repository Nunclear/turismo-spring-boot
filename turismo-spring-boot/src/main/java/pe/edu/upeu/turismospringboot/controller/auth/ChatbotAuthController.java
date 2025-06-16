package pe.edu.upeu.turismospringboot.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.turismospringboot.model.dto.auth.ChatbotAuthRequest;
import pe.edu.upeu.turismospringboot.model.dto.auth.ChatbotAuthResponse;
import pe.edu.upeu.turismospringboot.service.auth.ChatbotAuthService;

@RestController
@RequestMapping("/auth/chatbot")
@RequiredArgsConstructor
public class ChatbotAuthController {

    private final ChatbotAuthService chatbotAuthService;

    @PostMapping("/authenticate")
    public ResponseEntity<ChatbotAuthResponse> authenticate(@RequestBody ChatbotAuthRequest request) {
        return ResponseEntity.ok(chatbotAuthService.authenticate(request));
    }

    @PostMapping("/validate-token")
    public ResponseEntity<ChatbotAuthResponse> validateToken(@RequestHeader("X-Chatbot-Token") String token) {
        return ResponseEntity.ok(chatbotAuthService.validateToken(token));
    }
}
