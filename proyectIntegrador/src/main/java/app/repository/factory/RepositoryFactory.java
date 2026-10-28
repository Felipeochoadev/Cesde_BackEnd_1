package app.repository.factory;

import app.domain.enums.Ambiente;
import app.repository.*;
import app.repository.config.AppConfig;
import app.service.ouputports.*;

public class RepositoryFactory {

    public static ClienteRepositoryPort createClienteRepository() {
        if (AppConfig.getAmbiente() == Ambiente.MEMORIA) {
            return new ClienteRepositoryImp();
        }
        return new ClienteRepositoryImpDB();
    }

    public static PersonaRepositoryPort createPersonaRepository() {
        if (AppConfig.getAmbiente() == Ambiente.MEMORIA) {
            return new PersonaRepositoryImp();
        }
        return new PersonaRepositoryImpDB();
    }

    public static PeliculaRepositoryPort createPeliculaRepository() {
        if (AppConfig.getAmbiente() == Ambiente.MEMORIA) {
            return new PeliculaRepositoryImp();
        }
        return new PeliculaRepositoryImpDB();
    }

    public static SalaRepositoryPort createSalaRepository() {
        if (AppConfig.getAmbiente() == Ambiente.MEMORIA) {
            return new SalaRepositoryImp();
        }
        return new SalaRepositoryImpDB();
    }

    public static FuncionRepositoryPort createFuncionRepository() {
        if (AppConfig.getAmbiente() == Ambiente.MEMORIA) {
            return new FuncionRepositoryImp();
        }
        return new FuncionRepositoryImpDB();
    }

    public static BoletoRepositoryPort createBoletoRepository() {
        if (AppConfig.getAmbiente() == Ambiente.MEMORIA) {
            return new BoletoRepositoryImp();
        }
        return new BoletoRepositoryImpDB();
    }

    public static ReservaRepositoryPort createReservaRepository() {
        if (AppConfig.getAmbiente() == Ambiente.MEMORIA) {
            return new ReservaRepositoryImp();
        }
        return new ReservaRepositoryImpDB();
    }

    public static FacturaRepositoryPort createFacturaRepository() {
        if (AppConfig.getAmbiente() == Ambiente.MEMORIA) {
            return new FacturaRepositoryImp();
        }
        return new FacturaRepositoryImpDB();
    }
}
