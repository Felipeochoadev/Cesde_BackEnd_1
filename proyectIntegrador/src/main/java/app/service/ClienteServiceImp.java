package app.service;

import app.domain.Cliente;
import app.service.inputports.ClienteServiceInterface;
import app.service.ouputports.ClienteRepositoryPort;
import java.util.List;

public class ClienteServiceImp implements ClienteServiceInterface {
    private final ClienteRepositoryPort clienteRepository;

    public ClienteServiceImp(ClienteRepositoryPort clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente create(int id, String nombre, String correo, String telefono, int edad) {
        Cliente cliente = new Cliente(id, nombre, correo, telefono, edad);
        return clienteRepository.create(cliente);
    }

    @Override
    public Cliente getById(int id) {
        return clienteRepository.getById(id);
    }

    @Override
    public List<Cliente> getAll() {
        return clienteRepository.getAll();
    }

    @Override
    public Cliente update(int id, Cliente cliente) {
        return clienteRepository.update(id, cliente);
    }

    @Override
    public boolean delete(int id) {
        return clienteRepository.delete(id);
    }
}
