package pe.edu.upeu.turismospringboot.model.dto.chatbot;

import lombok.Data;

@Data
public class ChatbotServicioDto {
    private Long idServicio;
    private String nombre;
    private String descripcion;
    private Double precioUnitario;
    private String tipoServicio;
    private String imagenUrl;
}
