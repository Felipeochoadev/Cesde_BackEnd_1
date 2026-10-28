package app.service.mappers;

import app.domain.Cliente;
import app.service.dto.ClienteResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class ClienteDTOMapper {

    public static ClienteResponseDTO toResponseDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getCorreo(),
                cliente.getTelefono(),
                cliente.getEdad(),
                cliente.esMayorDeEdad()
        );
    }

    public static List<ClienteResponseDTO> toResponseDTOList(List<Cliente> clientes) {
        if (clientes == null) {
            return new ArrayList<>();
        }
        List<ClienteResponseDTO> dtos = new ArrayList<>();
        for (Cliente c : clientes) {
            dtos.add(toResponseDTO(c));
        }
        return dtos;
    }
}
