package app.service.mappers;

import app.domain.Boleto;
import app.service.dto.BoletoResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class BoletoDTOMapper {

    public static BoletoResponseDTO toResponseDTO(Boleto boleto) {
        if (boleto == null) return null;
        int funcionId = (boleto.getFuncion() != null) ? boleto.getFuncion().getId() : 0;
        String pelicula = (boleto.getFuncion() != null && boleto.getFuncion().getPelicula() != null) 
                ? boleto.getFuncion().getPelicula().getTitulo() : "";
        String sala = (boleto.getFuncion() != null && boleto.getFuncion().getSala() != null) 
                ? boleto.getFuncion().getSala().getNombre() : "";

        return new BoletoResponseDTO(
                boleto.getId(),
                boleto.getCodigo(),
                funcionId,
                pelicula,
                sala,
                boleto.getAsiento(),
                boleto.getPrecio()
        );
    }

    public static List<BoletoResponseDTO> toResponseDTOList(List<Boleto> boletos) {
        if (boletos == null) return new ArrayList<>();
        List<BoletoResponseDTO> list = new ArrayList<>();
        for (Boleto b : boletos) {
            list.add(toResponseDTO(b));
        }
        return list;
    }
}
