package app.view;

import app.service.inputports.PersonaServiceInterface;

public class PersonaView {
    private final PersonaServiceInterface personaService;

    public PersonaView(PersonaServiceInterface personaService) {
        this.personaService = personaService;
    }
}
