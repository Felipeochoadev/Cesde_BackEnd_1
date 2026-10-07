package app.view;

import app.service.inputports.BoletoServiceInterface;

public class BoletoView {
    private final BoletoServiceInterface boletoService;

    public BoletoView(BoletoServiceInterface boletoService) {
        this.boletoService = boletoService;
    }
}
