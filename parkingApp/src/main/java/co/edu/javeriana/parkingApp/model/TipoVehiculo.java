package co.edu.javeriana.parkingApp.model;

import java.math.BigDecimal;
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
    private BigDecimal tarifa;
    @Column(name = "area")
    private int area;
    
    @OneToMany(mappedBy = "tipoVehiculo")
    private List<Piso> pisos;

    public TipoVehiculo(){

    };
    
    public TipoVehiculo(char tipo, BigDecimal tarifa, int area) {
        this.tipo = tipo;
        this.tarifa = tarifa;
        this.area = area;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    public BigDecimal getTarifa() {
        return tarifa;
    }
    public void setTarifa(BigDecimal tarifa) {
        this.tarifa = tarifa;
    }
    public int getArea() {
        return area;
    }
    public void setArea(int area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "tipo=" + tipo + ", tarifa=" + tarifa + ", area=" + area;
    }
    
}
