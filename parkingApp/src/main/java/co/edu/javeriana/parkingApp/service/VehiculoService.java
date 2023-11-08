package co.edu.javeriana.parkingApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.parkingApp.model.Vehiculo;
import co.edu.javeriana.parkingApp.model.dto.dtoVehiculo;
import co.edu.javeriana.parkingApp.repository.VehiculoRepository;

@Service
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    public List<Vehiculo> findALL(){
        return vehiculoRepository.findAll();
    }
    public Vehiculo recuperarVehiculo(Long idLong){
        return vehiculoRepository.findById(idLong).orElseThrow();
    }

    public Vehiculo guardarVehiculo(Vehiculo vehiculo){
        return vehiculoRepository.save(vehiculo);
    }
    
    public void borrarVehiculo(Long idLong){
        vehiculoRepository.deleteById(idLong);
    }    
}
