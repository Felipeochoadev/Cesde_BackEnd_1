package app.view;

import app.service.inputports.FuncionServiceInterface;

public class FuncionView {
    private final FuncionServiceInterface funcionService;

    public FuncionView(FuncionServiceInterface funcionService) {
        this.funcionService = funcionService;
    }
}
