package pe.edu.upeu.turismospringboot.model.dto.chatbot;

import lombok.Data;

@Data
public class ChatbotUsuarioDto {
    private Long idUsuario;
    private String username;
    private String rol;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correoElectronico;
}
