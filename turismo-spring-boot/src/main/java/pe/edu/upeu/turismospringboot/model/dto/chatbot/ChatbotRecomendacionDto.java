package pe.edu.upeu.turismospringboot.model.dto.chatbot;

import lombok.Data;

@Data
public class ChatbotRecomendacionDto {
    private Long idEmprendimiento;
    private String nombre;
    private String descripcion;
    private String razon;
    private Double puntuacion;
}
