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
@Table(name = "edificio")
public class Edificio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "ancho", nullable = false)
    private long ancho;

    @Column(name = "largo", nullable = false)
    private long largo;
    
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "edificio")
    private List<Piso> pisos;

    public Edificio() {

    }

    public Edificio(String name, long ancho, long largo) {
        this.name = name;
        this.ancho = ancho;
        this.largo = largo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLargo() {
        return largo;
    }

    public void setLargo(Long largo) {
        this.largo = largo;
    }

    public Long getAncho() {
        return ancho;
    }

    public void setAncho(Long ancho) {
        this.ancho = ancho;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
