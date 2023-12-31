package co.edu.javeriana.parkingApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import co.edu.javeriana.parkingApp.model.Piso;
import co.edu.javeriana.parkingApp.model.Vehiculo;
import co.edu.javeriana.parkingApp.model.dto.dtoVehiculo;
import co.edu.javeriana.parkingApp.repository.PisoRepository;
import co.edu.javeriana.parkingApp.repository.VehiculoRepository;
import jakarta.transaction.Transactional;

@Service
public class PisoService {
    
    @Autowired
    private PisoRepository pisoRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;
    public Piso recuperarPiso(int id) {
        return pisoRepository.findById(Integer.toUnsignedLong(id)).orElseThrow();
    }

    public List<Piso> listarPisos() {
        return pisoRepository.findAll();
    }

    public void editarPiso(long idPiso){
        pisoRepository.findById(idPiso).orElseThrow();
    }

    public void guardarPiso(Piso piso) {
        pisoRepository.save(piso);
    }
    public List<Vehiculo> findVehiculosByPiso(int idPiso){
        return vehiculoRepository.findVehiculosByPiso(idPiso);
    }
    public List<Piso> getPisosTipo(char tipo){
        return pisoRepository.findPisosBytipo(tipo);
    }    

    public String borrarPiso(Long id){
        Piso p=pisoRepository.findById(id).orElseThrow();
        if(p.getTotalVehiculos()>0){
            return "No se puede Borrar! Hay vehiculos en el piso";
        }
        else{
            pisoRepository.deleteById(id);
            return "Borrado Exitoso";
        }        
    }
    
    public dtoVehiculo sacarVehiculo(Vehiculo vehiculo){
        return null;
    }
    @Transactional
    public dtoVehiculo registrarSalida(int idVehiculo){
        List<Piso> p = pisoRepository.findPisosByVehiculoId(idVehiculo);
        if( !p.isEmpty()){
            Piso p2 = p.get(0);
            Vehiculo v = (Vehiculo) vehiculoRepository.findAllById(idVehiculo).get(0);
            dtoVehiculo dto= p2.sacarVehiculo(v);
            vehiculoRepository.deleteById(idVehiculo);
            pisoRepository.save(p2);
            return dto;
        }else{
            dtoVehiculo d = new dtoVehiculo();
            return d;
        }
        
    }        
}
