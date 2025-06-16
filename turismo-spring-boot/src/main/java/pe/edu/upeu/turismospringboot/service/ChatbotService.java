package pe.edu.upeu.turismospringboot.service;

import pe.edu.upeu.turismospringboot.model.dto.chatbot.*;

import java.util.List;

public interface ChatbotService {
    List<ChatbotLugarDto> getLugaresPopulares();
    List<ChatbotEmprendimientoDto> getEmprendimientosPorCategoria(String categoria);
    List<ChatbotEmprendimientoDto> getEmprendimientosPorLugar(Long idLugar);
    List<ChatbotServicioDto> getServiciosPorEmprendimiento(Long idEmprendimiento);
    ChatbotUsuarioDto getPerfilUsuario(Long idUsuario);
    List<ChatbotReservaDto> getReservasUsuario(Long idUsuario);
    ChatbotEstadisticasDto getEstadisticasEmprendedor(Long idUsuario);
    ChatbotDashboardDto getDashboardAdmin();
    List<ChatbotRecomendacionDto> getRecomendaciones(Long idUsuario, String categoria);
    ChatbotDisponibilidadDto verificarDisponibilidad(ChatbotVerificarDisponibilidadRequest request);
    ChatbotBusquedaDto buscarGeneral(String query, String tipo);
}
