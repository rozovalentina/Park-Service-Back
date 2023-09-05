package co.edu.javeriana.parkingApp.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "piso")
public class Piso {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name="edificio_n")
    private Edificio edificio;

    @Column(name = "tipo_vehiculop", nullable = false)
    private char tipoVehiculoP;

    @Column(name = "total_vehiculos", nullable = false)
    private int totalVehiculos;

    @Column(name = "tarifa", nullable = false)
    private long tarifa;

    @Column(name = "espacios_disponibles", nullable = false)
    private int espaciosDisponibles;

    @OneToMany(mappedBy = "piso")
    private List<Vehiculo> vehiculos;
    
    public Piso(Edificio edificio, char tipoVehiculoP, int totalVehiculos, long tarifa, int espaciosDisponibles) {
        this.edificio = edificio;
        this.tipoVehiculoP = tipoVehiculoP;
        this.totalVehiculos = totalVehiculos;
        this.tarifa = tarifa;
        this.espaciosDisponibles = espaciosDisponibles;
    }

    public Piso() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public char getTipoVehiculoP() {
        return tipoVehiculoP;
    }

    public void setTipoVehiculoP(char tipoVehiculoP) {
        this.tipoVehiculoP = tipoVehiculoP;
    }

    public int getTotalVehiculos() {
        return totalVehiculos;
    }

    public void setTotalVehiculos(int totalVehiculos) {
        this.totalVehiculos = totalVehiculos;
    }

    public long getTarifa() {
        return tarifa;
    }

    public void setTarifa(long tarifa) {
        this.tarifa = tarifa;
    }

    public int getEspaciosDisponibles() {
        return espaciosDisponibles;
    }

    public void setEspaciosDisponibles(int espaciosDisponibles) {
        this.espaciosDisponibles = espaciosDisponibles;
    }

}
