package app.view;

import app.service.inputports.ReservaServiceInterface;

public class ReservaView {
    private final ReservaServiceInterface reservaService;

    public ReservaView(ReservaServiceInterface reservaService) {
        this.reservaService = reservaService;
    }
}
