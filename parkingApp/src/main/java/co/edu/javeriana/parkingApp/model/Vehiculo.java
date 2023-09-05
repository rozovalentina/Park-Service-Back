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
@Table(name = "vehiculo")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name="piso_n")
    private Piso piso;

    @Column(name = "hora_llegada", nullable = false)
    private int horaLlegada;

    
    public Vehiculo(Piso piso, String placa, int horaLlegada) {
        this.piso = piso;
        this.placa = placa;
        this.horaLlegada = horaLlegada;
    }

    public Vehiculo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Piso getPiso() {
        return piso;
    }

    public void setPiso(Piso piso) {
        this.piso = piso;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(int horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

}
