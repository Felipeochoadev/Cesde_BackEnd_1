package app.domain.interfaces;

/**
 * Principio de Responsabilidad Única (SRP) e Inversión de Dependencias (DIP):
 * Abstracción genérica para validación de reglas de negocio en el dominio.
 */
public interface IValidador<T> {
    void validar(T entidad) throws IllegalArgumentException, IllegalStateException;
}
