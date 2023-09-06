package co.edu.javeriana.parkingApp.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "tipo_vehiculo")
public class TipoVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "tipo")
    private char tipo;
    @Column(name = "tarifa")
    private double tarifa;
    @OneToMany(mappedBy = "tipoVehiculo")
    private List<Piso> pisos;

    public TipoVehiculo(){

    };
    
    public TipoVehiculo(char tipo, double tarifa) {
        this.tipo = tipo;
        this.tarifa = tarifa;
    }
    public Long getId() {
        return id;
    }
    public char getTipo() {
        return tipo;
    }
    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
    public double getTarifa() {
        return tarifa;
    }
    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    @Override
    public String toString() {
        return "tipo=" + tipo + ", tarifa=" + tarifa;
    }
    
}
