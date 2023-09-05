package co.edu.javeriana.parkingApp.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

    @Column(name = "total_vehiculos", nullable = false)
    private int totalVehiculos;

    @Column(name = "espacios_disponibles", nullable = false)
    private int espaciosDisponibles;

    @OneToOne(mappedBy = "piso")
    private List<TipoVehiculo> tipoVehiculos;
    
    public Piso(Edificio edificio, TipoVehiculo tipoVehiculo) {
        this.edificio = edificio;
        this.tipoVehiculo = tipoVehiculo;
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

    public int getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public int getEspaciosDisponibles() {
        return espaciosDisponibles;
    }

    public int getTotalVehiculos() {
        return totalVehiculos;
    }

}
