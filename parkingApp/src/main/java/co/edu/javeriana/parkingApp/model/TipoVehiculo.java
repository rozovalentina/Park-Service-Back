package co.edu.javeriana.parkingApp.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name= "tipo_vehiculo")
public class TipoVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "tipo_vehiculo")
    private char tipoPlaca;
    @Column(name = "tarifa")
    private double tarifa;
    @OneToMany
    @JoinColumn(name = "piso_id")
    private List<Piso> pisos;

    public TipoVehiculo(char tipoPlaca, double tarifa) {
        this.tipoPlaca = tipoPlaca;
        this.tarifa = tarifa;
    }
    public Long getId() {
        return id;
    }
    public char getTipoPlaca() {
        return tipoPlaca;
    }
    public void setTipoPlaca(char tipoPlaca) {
        this.tipoPlaca = tipoPlaca;
    }
    public double getTarifa() {
        return tarifa;
    }
    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }
}
