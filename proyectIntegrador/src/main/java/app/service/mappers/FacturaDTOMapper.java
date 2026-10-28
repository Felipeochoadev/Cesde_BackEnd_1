package app.service.mappers;

import app.domain.Factura;
import app.service.dto.FacturaResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class FacturaDTOMapper {

    public static FacturaResponseDTO toResponseDTO(Factura factura) {
        if (factura == null) return null;
        int reservaId = (factura.getReserva() != null) ? factura.getReserva().getId() : 0;
        String cliente = (factura.getReserva() != null && factura.getReserva().getCliente() != null) 
                ? factura.getReserva().getCliente().getNombre() : "";
        String pelicula = (factura.getReserva() != null && factura.getReserva().getFuncion() != null && factura.getReserva().getFuncion().getPelicula() != null) 
                ? factura.getReserva().getFuncion().getPelicula().getTitulo() : "";
        int cantidadBoletos = (factura.getReserva() != null) ? factura.getReserva().getCantidadBoletos() : 0;
        double total = (factura.getReserva() != null) ? factura.getReserva().calcularTotal() : 0.0;

        return new FacturaResponseDTO(
                factura.getId(),
                factura.getNumero(),
                reservaId,
                cliente,
                pelicula,
                cantidadBoletos,
                factura.getMetodoPago(),
                factura.isPagada(),
                total
        );
    }

    public static List<FacturaResponseDTO> toResponseDTOList(List<Factura> facturas) {
        if (facturas == null) return new ArrayList<>();
        List<FacturaResponseDTO> list = new ArrayList<>();
        for (Factura f : facturas) {
            list.add(toResponseDTO(f));
        }
        return list;
    }
}
