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
    @Column(name = "dia")
    private String dia;
    
    @OneToMany(mappedBy = "tipoVehiculo")
    private List<Piso> pisos;

    public TipoVehiculo(){
    };
    

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public TipoVehiculo(char tipo, BigDecimal tarifa, int area, String dia) {
        this.tipo = tipo;
        this.tarifa = tarifa;
        this.area = area;
        this.dia = dia;
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
        if (dia.equals("Fines de semana")) {
            return tarifa.add(new BigDecimal(50));
        }else{
            return tarifa;
        }
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
