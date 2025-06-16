package pe.edu.upeu.turismospringboot.controller.chatbot;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.turismospringboot.model.dto.chatbot.*;
import pe.edu.upeu.turismospringboot.service.ChatbotService;

import java.util.List;

@RestController
@RequestMapping("/chatbot")
@RequiredArgsConstructor
public class ChatbotController {

    private final ChatbotService chatbotService;

    @GetMapping("/lugares/populares")
    public ResponseEntity<List<ChatbotLugarDto>> getLugaresPopulares(
            @RequestHeader("X-Chatbot-Token") String token) {
        return ResponseEntity.ok(chatbotService.getLugaresPopulares());
    }

    @GetMapping("/emprendimientos/categoria/{categoria}")
    public ResponseEntity<List<ChatbotEmprendimientoDto>> getEmprendimientosPorCategoria(
            @PathVariable String categoria,
            @RequestHeader("X-Chatbot-Token") String token) {
        return ResponseEntity.ok(chatbotService.getEmprendimientosPorCategoria(categoria));
    }

    @GetMapping("/emprendimientos/lugar/{idLugar}")
    public ResponseEntity<List<ChatbotEmprendimientoDto>> getEmprendimientosPorLugar(
            @PathVariable Long idLugar,
            @RequestHeader("X-Chatbot-Token") String token) {
        return ResponseEntity.ok(chatbotService.getEmprendimientosPorLugar(idLugar));
    }

    @GetMapping("/servicios/emprendimiento/{idEmprendimiento}")
    public ResponseEntity<List<ChatbotServicioDto>> getServiciosPorEmprendimiento(
            @PathVariable Long idEmprendimiento,
            @RequestHeader("X-Chatbot-Token") String token) {
        return ResponseEntity.ok(chatbotService.getServiciosPorEmprendimiento(idEmprendimiento));
    }

    @GetMapping("/usuario/{idUsuario}/perfil")
    public ResponseEntity<ChatbotUsuarioDto> getPerfilUsuario(
            @PathVariable Long idUsuario,
            @RequestHeader("X-Chatbot-Token") String token) {
        return ResponseEntity.ok(chatbotService.getPerfilUsuario(idUsuario));
    }

    @GetMapping("/usuario/{idUsuario}/reservas")
    public ResponseEntity<List<ChatbotReservaDto>> getReservasUsuario(
            @PathVariable Long idUsuario,
            @RequestHeader("X-Chatbot-Token") String token) {
        return ResponseEntity.ok(chatbotService.getReservasUsuario(idUsuario));
    }

    @GetMapping("/emprendedor/{idUsuario}/estadisticas")
    public ResponseEntity<ChatbotEstadisticasDto> getEstadisticasEmprendedor(
            @PathVariable Long idUsuario,
            @RequestHeader("X-Chatbot-Token") String token) {
        return ResponseEntity.ok(chatbotService.getEstadisticasEmprendedor(idUsuario));
    }

    @GetMapping("/admin/dashboard")
    public ResponseEntity<ChatbotDashboardDto> getDashboardAdmin(
            @RequestHeader("X-Chatbot-Token") String token) {
        return ResponseEntity.ok(chatbotService.getDashboardAdmin());
    }

    @GetMapping("/recomendaciones/usuario/{idUsuario}")
    public ResponseEntity<List<ChatbotRecomendacionDto>> getRecomendaciones(
            @PathVariable Long idUsuario,
            @RequestParam(required = false) String categoria,
            @RequestHeader("X-Chatbot-Token") String token) {
        return ResponseEntity.ok(chatbotService.getRecomendaciones(idUsuario, categoria));
    }

    @PostMapping("/reserva/verificar-disponibilidad")
    public ResponseEntity<ChatbotDisponibilidadDto> verificarDisponibilidad(
            @RequestBody ChatbotVerificarDisponibilidadRequest request,
            @RequestHeader("X-Chatbot-Token") String token) {
        return ResponseEntity.ok(chatbotService.verificarDisponibilidad(request));
    }

    @GetMapping("/buscar")
    public ResponseEntity<ChatbotBusquedaDto> buscarGeneral(
            @RequestParam String query,
            @RequestParam(required = false) String tipo,
            @RequestHeader("X-Chatbot-Token") String token) {
        return ResponseEntity.ok(chatbotService.buscarGeneral(query, tipo));
    }
}
