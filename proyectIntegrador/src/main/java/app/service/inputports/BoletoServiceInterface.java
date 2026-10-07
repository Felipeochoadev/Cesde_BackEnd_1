package app.service.inputports;

import app.domain.Boleto;
import java.util.List;

public interface BoletoServiceInterface {
    public Boleto create(Boleto boleto);
    public Boleto getById(int id);
    public List<Boleto> getAll();
    public Boleto update(int id, Boleto boleto);
    public boolean delete(int id);
}
