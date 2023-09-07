package co.edu.javeriana.parkingApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    private long espaciosDisponibles;
    
    @ManyToOne  
    @JoinColumn(name = "tipo_vehiculo_n")
    private TipoVehiculo tipoVehiculo;
    
    public Piso(Edificio edificio, TipoVehiculo tipoVehiculo) {
        this.edificio = edificio;
        this.tipoVehiculo = tipoVehiculo;
        long area = edificio.getAncho() * edificio.getLargo();
        long result = Math.round((area - Math.round(area * 0.2))/tipoVehiculo.getArea());
        this.espaciosDisponibles = result;
        this.totalVehiculos = 0;
    }

    public Piso() {
    }

    public void agregarVehiculo(){
        this.totalVehiculos++;
        this.espaciosDisponibles--;
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

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public long getEspaciosDisponibles() {
        return espaciosDisponibles;
    }

    public int getTotalVehiculos() {
        return totalVehiculos;
    }

    @Override
    public String toString() {
        return "total Vehiculos=" + totalVehiculos + ", espacios Disponibles=" + espaciosDisponibles
                + ", tipo Vehiculo= [" + tipoVehiculo.toString()+ "]";
    }
    

}
