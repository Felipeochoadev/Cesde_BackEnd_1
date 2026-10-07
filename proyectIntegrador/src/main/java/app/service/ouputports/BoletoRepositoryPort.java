package app.service.ouputports;

import app.domain.Boleto;
import java.util.List;

public interface BoletoRepositoryPort {
    public Boleto create(Boleto boleto);
    public Boleto getById(int id);
    public List<Boleto> getAll();
    public Boleto update(int id, Boleto boleto);
    public boolean delete(int id);
}
