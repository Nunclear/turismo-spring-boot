package pe.edu.upeu.turismospringboot.model.dto.chatbot;

import lombok.Data;

@Data
public class ChatbotEstadisticasDto {
    private Long totalReservas;
    private Long totalServicios;
    private Double ingresosTotales;
    private Long reservasPendientes;
    private Long reservasConfirmadas;
}
