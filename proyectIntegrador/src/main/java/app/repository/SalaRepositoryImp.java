package app.repository;

import app.domain.Sala;
import app.service.ouputports.SalaRepositoryPort;
import java.util.ArrayList;
import java.util.List;

public class SalaRepositoryImp implements SalaRepositoryPort {
    private List<Sala> dataSource = new ArrayList<>();
    private int currentId = 0;

    @Override
    public Sala create(Sala sala) {
        sala.setId(++currentId);
        dataSource.add(sala);
        return sala;
    }

    @Override
    public Sala getById(int id) {
        for (Sala s : dataSource) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    @Override
    public List<Sala> getAll() {
        return new ArrayList<>(dataSource);
    }

    @Override
    public Sala update(int id, Sala sala) {
        for (int i = 0; i < dataSource.size(); i++) {
            if (dataSource.get(i).getId() == id) {
                sala.setId(id);
                dataSource.set(i, sala);
                return sala;
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return dataSource.removeIf(s -> s.getId() == id);
    }
}
