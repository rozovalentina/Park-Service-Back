package co.edu.javeriana.parkingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.parkingApp.model.Vehiculo;
import co.edu.javeriana.parkingApp.repository.VehiculoRepository;

@Service
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

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
