package co.edu.javeriana.parkingApp.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name= "tipo_vehiculo")
public class TipoVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "tarifa")
    private BigDecimal tarifa;

    @Column(name = "area")
    private int area;

    @Column(name = "dia")
    private String dia;
    
    @OneToMany(mappedBy = "tipoVehiculo")
    private List<Piso> pisos;

    public TipoVehiculo(){
    };
    public TipoVehiculo(final String tipo, final BigDecimal tarifa, final int area, final String dia) {
        this.tipo = tipo;
        this.tarifa = tarifa;
        this.area = area;
        this.dia = dia;
    }
    

    public String getDia() {
        return dia;
    }

    public void setDia(final String dia) {
        this.dia = dia;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }
    public BigDecimal getTarifa() {
        if (dia != null) {
            if ("Fines de semana".equals(dia)) {
                return tarifa.add(new BigDecimal(50));
            } else {
                return tarifa;
            }
        } else {
            return tarifa;
        }
    }
        
    public void setTarifa(final BigDecimal tarifa) {
        this.tarifa = tarifa;
    }
    public int getArea() {
        return area;
    }
    public void setArea(final int area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "tipo=" + tipo + ", tarifa=" + tarifa + ", area=" + area;
    }
    public List<Piso> getPisos() {
        return pisos;
    }
    public void setPisos(final List<Piso> pisos) {
        this.pisos = pisos;
    }
}
