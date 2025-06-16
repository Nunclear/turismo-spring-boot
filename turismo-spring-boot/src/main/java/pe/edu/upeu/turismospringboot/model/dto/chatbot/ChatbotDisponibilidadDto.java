package pe.edu.upeu.turismospringboot.model.dto.chatbot;

import lombok.Data;

@Data
public class ChatbotDisponibilidadDto {
    private Boolean disponible;
    private String mensaje;
    private Double precio;
}
