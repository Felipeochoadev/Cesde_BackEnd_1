package app.view;

import app.service.inputports.SalaServiceInterface;

public class SalaView {
    private final SalaServiceInterface salaService;

    public SalaView(SalaServiceInterface salaService) {
        this.salaService = salaService;
    }
}
