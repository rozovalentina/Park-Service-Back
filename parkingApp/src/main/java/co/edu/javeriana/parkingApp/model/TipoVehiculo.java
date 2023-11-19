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
    @Column(name = "urlImagen")
    private String urlImagen;
    @OneToMany(mappedBy = "tipoVehiculo")
    private List<Piso> pisos;

    public TipoVehiculo(){
    };
    public TipoVehiculo(final String tipo, final BigDecimal tarifa, final int area, final String dia) {
        this.tipo = tipo;
        this.tarifa = tarifa;
        this.area = area;
        this.dia = dia;
        this.urlImagen = decidirUrl(tipo);
    }
    private String decidirUrl(String tipo){
        switch (tipo) {
            case "B":
                return "https://firebasestorage.googleapis.com/v0/b/taller-3-6cd33.appspot.com/o/images%2Fbici.jpg?alt=media&token=ce1feeaa-e21b-44b5-9f13-ad9f14e15108";
            case "M":
                return "https://firebasestorage.googleapis.com/v0/b/taller-3-6cd33.appspot.com/o/images%2Fmoto.jpg?alt=media&token=f379509f-5d3f-4e2d-a5d9-5c85f80f4505";
            case "C":
                return "https://firebasestorage.googleapis.com/v0/b/taller-3-6cd33.appspot.com/o/images%2Fcarro.jpg?alt=media&token=3ae0ff16-9df5-449d-a5bd-addf8bd69c68";
            default:
                return " ";
        }
    }
    
    public String getUrlImagen() {
        return urlImagen;
    }
    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
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
}
