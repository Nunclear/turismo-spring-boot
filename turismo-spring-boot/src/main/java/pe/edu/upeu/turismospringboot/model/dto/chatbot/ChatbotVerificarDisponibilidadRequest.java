package pe.edu.upeu.turismospringboot.model.dto.chatbot;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ChatbotVerificarDisponibilidadRequest {
    private Long idEmprendimiento;
    private Long idServicio;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Integer cantidadPersonas;
}
