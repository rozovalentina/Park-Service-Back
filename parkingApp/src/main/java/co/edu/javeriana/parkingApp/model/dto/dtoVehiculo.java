package co.edu.javeriana.parkingApp.model.dto;

import java.math.BigDecimal;

import co.edu.javeriana.parkingApp.model.Vehiculo;

public class dtoVehiculo {
    private Vehiculo vehiculo;
    private int tiempo;
    private BigDecimal valor;    
    public dtoVehiculo() {}
    
    public dtoVehiculo(Vehiculo placa, int tiempo, BigDecimal valor) {
        vehiculo = placa;
        this.tiempo = tiempo;
        this.valor = valor;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    public void setVehiculo(Vehiculo placa) {
        vehiculo = placa;
    }
    public int getTiempo() {
        return tiempo;
    }
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    
}
