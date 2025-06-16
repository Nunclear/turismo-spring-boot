package pe.edu.upeu.turismospringboot.model.dto.chatbot;

import lombok.Data;

@Data
public class ChatbotEmprendimientoDto {
    private Long idEmprendimiento;
    private String nombre;
    private String descripcion;
    private String imagenUrl;
    private Double latitud;
    private Double longitud;
    private String categoria;
    private String lugar;
}
