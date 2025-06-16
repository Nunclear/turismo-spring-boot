package pe.edu.upeu.turismospringboot.model.dto.chatbot;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ChatbotReservaDto {
    private Long idReserva;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private String estado;
    private Double totalGeneral;
    private String nombreEmprendimiento;
}
