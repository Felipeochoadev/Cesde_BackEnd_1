package app.service.mappers;

import app.domain.Reserva;
import app.service.dto.ReservaResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class ReservaDTOMapper {

    public static ReservaResponseDTO toResponseDTO(Reserva reserva) {
        if (reserva == null) return null;
        String clienteNombre = (reserva.getCliente() != null) ? reserva.getCliente().getNombre() : "";
        String peliculaTitulo = (reserva.getFuncion() != null && reserva.getFuncion().getPelicula() != null) 
                ? reserva.getFuncion().getPelicula().getTitulo() : "";
        String salaNombre = (reserva.getFuncion() != null && reserva.getFuncion().getSala() != null) 
                ? reserva.getFuncion().getSala().getNombre() : "";
        String fecha = (reserva.getFuncion() != null) ? reserva.getFuncion().getFecha() : "";
        String hora = (reserva.getFuncion() != null) ? reserva.getFuncion().getHora() : "";

        return new ReservaResponseDTO(
                reserva.getId(),
                clienteNombre,
                peliculaTitulo,
                salaNombre,
                fecha,
                hora,
                reserva.getCantidadBoletos(),
                reserva.calcularSubtotal(),
                reserva.calcularDescuento(),
                reserva.calcularTotal(),
                reserva.getEstado()
        );
    }

    public static List<ReservaResponseDTO> toResponseDTOList(List<Reserva> reservas) {
        if (reservas == null) return new ArrayList<>();
        List<ReservaResponseDTO> list = new ArrayList<>();
        for (Reserva r : reservas) {
            list.add(toResponseDTO(r));
        }
        return list;
    }
}
