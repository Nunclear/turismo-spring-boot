package pe.edu.upeu.turismospringboot.model.dto.chatbot;

import lombok.Data;

@Data
public class ChatbotDashboardDto {
    private Long totalUsuarios;
    private Long totalEmprendimientos;
    private Long totalReservas;
    private Long totalCategorias;
}
