package app.view;

import app.service.inputports.FacturaServiceInterface;

public class FacturaView {
    private final FacturaServiceInterface facturaService;

    public FacturaView(FacturaServiceInterface facturaService) {
        this.facturaService = facturaService;
    }
}
