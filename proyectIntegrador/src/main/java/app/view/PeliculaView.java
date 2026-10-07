package app.view;

import app.service.inputports.PeliculaServiceInterface;

public class PeliculaView {
    private final PeliculaServiceInterface peliculaService;

    public PeliculaView(PeliculaServiceInterface peliculaService) {
        this.peliculaService = peliculaService;
    }
}
