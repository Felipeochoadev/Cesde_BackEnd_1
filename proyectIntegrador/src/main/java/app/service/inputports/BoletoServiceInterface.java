package app.service.inputports;

import app.domain.Boleto;
import app.domain.Funcion;
import java.util.List;

public interface BoletoServiceInterface {
    public Boleto create(int id, String codigo, Funcion funcion, String asiento);
    public Boleto getById(int id);
    public List<Boleto> getAll();
    public Boleto update(int id, Boleto boleto);
    public boolean delete(int id);
}
