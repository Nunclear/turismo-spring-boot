package pe.edu.upeu.turismospringboot.model.dto.chatbot;

import lombok.Data;
import java.util.List;

@Data
public class ChatbotBusquedaDto {
    private List<ChatbotEmprendimientoDto> emprendimientos;
    private List<ChatbotLugarDto> lugares;
    private List<ChatbotServicioDto> servicios;
}
