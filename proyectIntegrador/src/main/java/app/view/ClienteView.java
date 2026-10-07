package app.view;

import app.service.inputports.ClienteServiceInterface;

public class ClienteView {
    private final ClienteServiceInterface clienteService;

    public ClienteView(ClienteServiceInterface clienteService) {
        this.clienteService = clienteService;
    }
}
