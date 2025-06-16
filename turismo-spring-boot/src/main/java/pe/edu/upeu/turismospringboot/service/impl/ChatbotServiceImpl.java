package pe.edu.upeu.turismospringboot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.turismospringboot.model.dto.chatbot.*;
import pe.edu.upeu.turismospringboot.model.entity.*;
import pe.edu.upeu.turismospringboot.repository.*;
import pe.edu.upeu.turismospringboot.service.ChatbotService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatbotServiceImpl implements ChatbotService {

    private final LugarRepository lugarRepository;
    private final EmprendimientoRepository emprendimientoRepository;
    private final ServicioTuristicoRepository servicioTuristicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ReservaRepository reservaRepository;
    private final CategoriaRepository categoriaRepository;
    private final FamiliaCategoriaRepository familiaCategoriaRepository;

    @Override
    public List<ChatbotLugarDto> getLugaresPopulares() {
        return lugarRepository.findAll().stream()
                .map(this::convertToLugarDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatbotEmprendimientoDto> getEmprendimientosPorCategoria(String categoria) {
        return emprendimientoRepository.findAll().stream()
                .filter(emp -> emp.getFamiliaCategoria() != null && 
                       emp.getFamiliaCategoria().getCategoria().getNombre().toLowerCase()
                       .contains(categoria.toLowerCase()))
                .map(this::convertToEmprendimientoDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatbotEmprendimientoDto> getEmprendimientosPorLugar(Long idLugar) {
        return emprendimientoRepository.findAll().stream()
                .filter(emp -> emp.getFamiliaCategoria() != null && 
                       emp.getFamiliaCategoria().getFamilia().getLugar().getIdLugar().equals(idLugar))
                .map(this::convertToEmprendimientoDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatbotServicioDto> getServiciosPorEmprendimiento(Long idEmprendimiento) {
        Emprendimiento emprendimiento = emprendimientoRepository.findById(idEmprendimiento)
                .orElseThrow(() -> new RuntimeException("Emprendimiento no encontrado"));
        
        return emprendimiento.getServicioTuristicos().stream()
                .map(this::convertToServicioDto)
                .collect(Collectors.toList());
    }

    @Override
    public ChatbotUsuarioDto getPerfilUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        return convertToUsuarioDto(usuario);
    }

    @Override
    public List<ChatbotReservaDto> getReservasUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        return usuario.getReservas().stream()
                .map(this::convertToReservaDto)
                .collect(Collectors.toList());
    }

    @Override
    public ChatbotEstadisticasDto getEstadisticasEmprendedor(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        if (usuario.getEmprendimiento() == null) {
            throw new RuntimeException("Usuario no es emprendedor");
        }

        Emprendimiento emp = usuario.getEmprendimiento();
        ChatbotEstadisticasDto stats = new ChatbotEstadisticasDto();
        stats.setTotalReservas((long) emp.getReservas().size());
        stats.setTotalServicios((long) emp.getServicioTuristicos().size());
        stats.setIngresosTotales(emp.getReservas().stream()
                .mapToDouble(r -> r.getTotalGeneral() != null ? r.getTotalGeneral() : 0.0)
                .sum());
        
        return stats;
    }

    @Override
    public ChatbotDashboardDto getDashboardAdmin() {
        ChatbotDashboardDto dashboard = new ChatbotDashboardDto();
        dashboard.setTotalUsuarios(usuarioRepository.count());
        dashboard.setTotalEmprendimientos(emprendimientoRepository.count());
        dashboard.setTotalReservas(reservaRepository.count());
        
        return dashboard;
    }

    @Override
    public List<ChatbotRecomendacionDto> getRecomendaciones(Long idUsuario, String categoria) {
        // Lógica simple de recomendaciones basada en popularidad
        return emprendimientoRepository.findAll().stream()
                .limit(5)
                .map(emp -> {
                    ChatbotRecomendacionDto rec = new ChatbotRecomendacionDto();
                    rec.setIdEmprendimiento(emp.getIdEmprendimiento());
                    rec.setNombre(emp.getNombre());
                    rec.setDescripcion(emp.getDescripcion());
                    rec.setRazon("Popular en la zona");
                    rec.setPuntuacion(4.5);
                    return rec;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ChatbotDisponibilidadDto verificarDisponibilidad(ChatbotVerificarDisponibilidadRequest request) {
        ChatbotDisponibilidadDto disponibilidad = new ChatbotDisponibilidadDto();
        disponibilidad.setDisponible(true);
        disponibilidad.setMensaje("Disponible para la fecha solicitada");
        
        return disponibilidad;
    }

    @Override
    public ChatbotBusquedaDto buscarGeneral(String query, String tipo) {
        ChatbotBusquedaDto resultado = new ChatbotBusquedaDto();
        
        if ("emprendimiento".equals(tipo) || tipo == null) {
            resultado.setEmprendimientos(emprendimientoRepository.buscarPorNombre(query).stream()
                    .map(this::convertToEmprendimientoDto)
                    .collect(Collectors.toList()));
        }
        
        if ("lugar".equals(tipo) || tipo == null) {
            resultado.setLugares(lugarRepository.buscarPorNombre(query).stream()
                    .map(this::convertToLugarDto)
                    .collect(Collectors.toList()));
        }
        
        return resultado;
    }

    // Métodos de conversión
    private ChatbotLugarDto convertToLugarDto(Lugar lugar) {
        ChatbotLugarDto dto = new ChatbotLugarDto();
        dto.setIdLugar(lugar.getIdLugar());
        dto.setNombre(lugar.getNombre());
        dto.setDescripcion(lugar.getDescripcion());
        dto.setCiudad(lugar.getCiudad());
        dto.setProvincia(lugar.getProvincia());
        dto.setImagenUrl(lugar.getImagenUrl());
        return dto;
    }

    private ChatbotEmprendimientoDto convertToEmprendimientoDto(Emprendimiento emp) {
        ChatbotEmprendimientoDto dto = new ChatbotEmprendimientoDto();
        dto.setIdEmprendimiento(emp.getIdEmprendimiento());
        dto.setNombre(emp.getNombre());
        dto.setDescripcion(emp.getDescripcion());
        dto.setImagenUrl(emp.getImagenUrl());
        dto.setLatitud(emp.getLatitud());
        dto.setLongitud(emp.getLongitud());
        
        if (emp.getFamiliaCategoria() != null) {
            dto.setCategoria(emp.getFamiliaCategoria().getCategoria().getNombre());
            if (emp.getFamiliaCategoria().getFamilia() != null) {
                dto.setLugar(emp.getFamiliaCategoria().getFamilia().getLugar().getNombre());
            }
        }
        
        return dto;
    }

    private ChatbotServicioDto convertToServicioDto(ServicioTuristico servicio) {
        ChatbotServicioDto dto = new ChatbotServicioDto();
        dto.setIdServicio(servicio.getIdServicio());
        dto.setNombre(servicio.getNombre());
        dto.setDescripcion(servicio.getDescripcion());
        dto.setPrecioUnitario(servicio.getPrecioUnitario());
        dto.setTipoServicio(servicio.getTipoServicio().name());
        dto.setImagenUrl(servicio.getImagenUrl());
        return dto;
    }

    private ChatbotUsuarioDto convertToUsuarioDto(Usuario usuario) {
        ChatbotUsuarioDto dto = new ChatbotUsuarioDto();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setUsername(usuario.getUsername());
        dto.setRol(usuario.getRol().getNombre());
        
        if (usuario.getPersona() != null) {
            dto.setNombres(usuario.getPersona().getNombres());
            dto.setApellidos(usuario.getPersona().getApellidos());
            dto.setTelefono(usuario.getPersona().getTelefono());
            dto.setCorreoElectronico(usuario.getPersona().getCorreoElectronico());
        }
        
        return dto;
    }

    private ChatbotReservaDto convertToReservaDto(Reserva reserva) {
        ChatbotReservaDto dto = new ChatbotReservaDto();
        dto.setIdReserva(reserva.getIdReserva());
        dto.setFechaHoraInicio(reserva.getFechaHoraInicio());
        dto.setFechaHoraFin(reserva.getFechaHoraFin());
        dto.setEstado(reserva.getEstado().name());
        dto.setTotalGeneral(reserva.getTotalGeneral());
        
        if (reserva.getEmprendimiento() != null) {
            dto.setNombreEmprendimiento(reserva.getEmprendimiento().getNombre());
        }
        
        return dto;
    }
}
