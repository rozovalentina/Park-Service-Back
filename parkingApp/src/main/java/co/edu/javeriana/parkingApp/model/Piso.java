package co.edu.javeriana.parkingApp.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    
    @OneToMany(mappedBy = "piso")
    private List<Vehiculo> vehiculos;

    public Piso(Edificio edificio, TipoVehiculo tipoVehiculo) {
        this.edificio = edificio;
        this.tipoVehiculo = tipoVehiculo;
        calcularEspacioDisponible();
        this.totalVehiculos = 0;
        this.vehiculos= new ArrayList<>();
    }

    public Piso(){
    }

    public void agregarVehiculo(Vehiculo vehiculo){
        vehiculos.add(vehiculo);
        this.totalVehiculos++;
        this.espaciosDisponibles--;
    }

    public String sacarVehiculo(Vehiculo vehiculo){
        vehiculos.remove(vehiculo);
        this.totalVehiculos--;
        this.espaciosDisponibles++;
        return calcularCobro(vehiculo);
    }
    public String calcularCobro(Vehiculo vehiculo){
        int horaLlegada = vehiculo.getHoraLlegada();
        LocalTime horaActual = LocalTime.now();
        int horaActualHH = horaActual.getHour() * 100 + horaActual.getMinute(); // Hora actual en formato militar

        // Calcular la diferencia de tiempo
        int minutosDentro = horaActualHH - horaLlegada;
        double aCobrar= minutosDentro * this.tipoVehiculo.getTarifa();
        // Calcular las horas y minutos
        int horasDentro = minutosDentro / 100;
        int minutosRestantes = minutosDentro % 100;

        return "El vehículo con ID " + vehiculo.getId() + " ha estado dentro por " + horasDentro + " horas y " + minutosRestantes + " minutos." + "\nSe cobra entonces: "+ aCobrar;
    }
    public boolean estaVehiculo(Vehiculo vehiculo){
        return vehiculos.contains(vehiculo);
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

    public void calcularEspacioDisponible(){
        long area = edificio.getAncho() * edificio.getLargo();
        long result = Math.round((area - Math.round(area * 0.2))/tipoVehiculo.getArea());
        this.espaciosDisponibles = result;
    }    

}
