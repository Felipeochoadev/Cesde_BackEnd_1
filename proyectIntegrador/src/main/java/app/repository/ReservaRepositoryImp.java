package app.repository;

import app.domain.Reserva;
import app.service.ouputports.ReservaRepositoryPort;
import java.util.ArrayList;
import java.util.List;

public class ReservaRepositoryImp implements ReservaRepositoryPort {
    private List<Reserva> dataSource = new ArrayList<>();

    @Override
    public Reserva create(Reserva reserva) {
        dataSource.add(reserva);
        return reserva;
    }

    @Override
    public Reserva getById(int id) {
        for (Reserva r : dataSource) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    @Override
    public List<Reserva> getAll() {
        return new ArrayList<>(dataSource);
    }

    @Override
    public Reserva update(int id, Reserva reserva) {
        for (int i = 0; i < dataSource.size(); i++) {
            if (dataSource.get(i).getId() == id) {
                dataSource.set(i, reserva);
                return reserva;
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return dataSource.removeIf(r -> r.getId() == id);
    }
}
