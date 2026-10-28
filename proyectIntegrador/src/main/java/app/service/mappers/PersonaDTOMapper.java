package app.service.mappers;

import app.domain.Persona;
import app.service.dto.PersonaResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class PersonaDTOMapper {

    public static PersonaResponseDTO toResponseDTO(Persona persona) {
        if (persona == null) return null;
        return new PersonaResponseDTO(
                persona.getId(),
                persona.getNombre(),
                persona.getCorreo(),
                persona.getTelefono()
        );
    }

    public static List<PersonaResponseDTO> toResponseDTOList(List<Persona> personas) {
        if (personas == null) return new ArrayList<>();
        List<PersonaResponseDTO> list = new ArrayList<>();
        for (Persona p : personas) {
            list.add(toResponseDTO(p));
        }
        return list;
    }
}
