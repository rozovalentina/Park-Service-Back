package co.edu.javeriana.parkingApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.parkingApp.model.TipoVehiculo;
import co.edu.javeriana.parkingApp.repository.TipoVehiculoRepository;

@Service
public class TipoVehiculoService {
    
    @Autowired
    private TipoVehiculoRepository tipoVehiculoRepository;

    public TipoVehiculo recuperarTipoVehiculo(Long id) {
        return tipoVehiculoRepository.findById(id).orElseThrow();
    }

    public List<TipoVehiculo> listarTipoVehiculo() {
        return tipoVehiculoRepository.findAll();
    }

    public void guardarTipoVehiculo(TipoVehiculo tipoVehiculo) {
        tipoVehiculoRepository.save(tipoVehiculo);
    }

    public String borrarTipoVehiculo(Long id){
        tipoVehiculoRepository.deleteById(id);
        return "Borrado Exitoso";      
    }
}
