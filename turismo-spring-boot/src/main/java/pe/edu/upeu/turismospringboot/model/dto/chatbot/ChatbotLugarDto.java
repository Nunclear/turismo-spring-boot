package pe.edu.upeu.turismospringboot.model.dto.chatbot;

import lombok.Data;

@Data
public class ChatbotLugarDto {
    private Long idLugar;
    private String nombre;
    private String descripcion;
    private String ciudad;
    private String provincia;
    private String imagenUrl;
}
