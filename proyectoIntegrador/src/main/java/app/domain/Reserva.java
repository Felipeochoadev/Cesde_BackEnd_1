package app.domain;

import app.domain.enums.EstadoReserva;
import app.domain.interfaces.ICalculable;
import app.domain.interfaces.IValidador;
import app.domain.strategy.DescuentoCineMaxRegla;
import app.domain.strategy.IDescuentoStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad Reserva (Tabla Reservas en BD CineMaxCesde).
 * 
 * Principios SOLID aplicados:
 * - SRP: Modela únicamente el estado de la reserva y delega cálculos de descuento y validación.
 * - OCP: El cálculo de descuentos se adapta mediante IDescuentoStrategy sin modificar esta clase.
 * - ISP: Implementa ICalculable para ofrecer solo la funcionalidad de cálculo de importe.
 * - DIP: Depende de las interfaces IDescuentoStrategy e IValidador, no de implementaciones fijas.
 */
public class Reserva implements ICalculable {
    private int id;
    private Cliente cliente;
    private Funcion funcion;
    private List<Boleto> boletos;
    private int cantidadBoletos;
    private LocalDateTime fechaReserva;
    private EstadoReserva estado;

    // Dependencias inyectadas (DIP)
    private IDescuentoStrategy descuentoStrategy;
    private IValidador<Reserva> validador;

    public Reserva() {
        this.boletos = new ArrayList<>();
        this.fechaReserva = LocalDateTime.now();
        this.estado = EstadoReserva.PENDIENTE;
        this.descuentoStrategy = new DescuentoCineMaxRegla();
        this.validador = new ValidadorReserva();
    }

    public Reserva(int id, Cliente cliente, Funcion funcion, int cantidadBoletos) {
        this();
        this.id = id;
        this.cliente = cliente;
        this.funcion = funcion;
        this.cantidadBoletos = cantidadBoletos;
    }

    public Reserva(int id, Cliente cliente, Funcion funcion, int cantidadBoletos, IDescuentoStrategy descuentoStrategy, IValidador<Reserva> validador) {
        this.boletos = new ArrayList<>();
        this.fechaReserva = LocalDateTime.now();
        this.estado = EstadoReserva.PENDIENTE;
        this.id = id;
        this.cliente = cliente;
        this.funcion = funcion;
        this.cantidadBoletos = cantidadBoletos;
        this.descuentoStrategy = (descuentoStrategy != null) ? descuentoStrategy : new DescuentoCineMaxRegla();
        this.validador = (validador != null) ? validador : new ValidadorReserva();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public List<Boleto> getBoletos() {
        return boletos;
    }

    public void setBoletos(List<Boleto> boletos) {
        this.boletos = boletos;
        if (boletos != null && !boletos.isEmpty()) {
            this.cantidadBoletos = boletos.size();
        }
    }

    public void agregarBoleto(Boleto boleto) {
        if (boleto != null) {
            this.boletos.add(boleto);
            this.cantidadBoletos = this.boletos.size();
        }
    }

    public int getCantidadBoletos() {
        return (boletos != null && !boletos.isEmpty()) ? boletos.size() : cantidadBoletos;
    }

    public void setCantidadBoletos(int cantidadBoletos) {
        this.cantidadBoletos = cantidadBoletos;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public IDescuentoStrategy getDescuentoStrategy() {
        return descuentoStrategy;
    }

    public void setDescuentoStrategy(IDescuentoStrategy descuentoStrategy) {
        this.descuentoStrategy = descuentoStrategy;
    }

    public IValidador<Reserva> getValidador() {
        return validador;
    }

    public void setValidador(IValidador<Reserva> validador) {
        this.validador = validador;
    }

    /**
     * Valida la reserva aplicando las reglas de negocio configuradas.
     */
    public void validarReserva() {
        if (validador != null) {
            validador.validar(this);
        }
    }

    /**
     * Calcula el subtotal sin descuentos.
     */
    public double getSubtotal() {
        if (boletos != null && !boletos.isEmpty()) {
            double suma = 0.0;
            for (Boleto b : boletos) {
                suma += b.calcularTotal();
            }
            return suma;
        }
        double precioUnitario = (funcion != null) ? funcion.getPrecioBoletoBase() : 0.0;
        return precioUnitario * cantidadBoletos;
    }

    /**
     * Calcula el monto del descuento aplicado según la estrategia.
     */
    public double getMontoDescuento() {
        if (descuentoStrategy == null) {
            return 0.0;
        }
        return descuentoStrategy.calcularDescuento(getSubtotal(), getCantidadBoletos(), cliente);
    }

    @Override
    public double calcularTotal() {
        return getSubtotal() - getMontoDescuento();
    }

    public void confirmar() {
        validarReserva();
        this.estado = EstadoReserva.CONFIRMADA;
    }

    public void cancelar() {
        this.estado = EstadoReserva.CANCELADA;
        if (boletos != null) {
            for (Boleto b : boletos) {
                if (b.getPuesto() != null) {
                    b.getPuesto().setOcupado(false);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Reserva #" + id + " [" + estado.getDescripcion() + "] - Cliente: " +
                (cliente != null ? cliente.getNombre() : "N/A") +
                " - Boletos: " + getCantidadBoletos() +
                " - Total: $" + calcularTotal() + " COP";
    }
}
