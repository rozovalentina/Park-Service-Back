package co.edu.javeriana.parkingApp.controller.gestionPraqueaderos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.parkingApp.model.Vehiculo;
import co.edu.javeriana.parkingApp.service.PisoService;
import co.edu.javeriana.parkingApp.service.VehiculoService;
import jakarta.validation.Valid;
import co.edu.javeriana.parkingApp.model.Piso;

@RestController
@RequestMapping("/gestionParqueaderos")
public class PisoControllerRest {
    @Autowired
    private VehiculoService vehiculoService;
    @Autowired
    private PisoService pisoService;

    @CrossOrigin("http://localhost:4200/")
    @PostMapping("/registrarVehiculo")
    public void registrarVehiculo(@Valid @RequestBody Vehiculo v, @RequestParam Long idPiso){    
        vehiculoService.guardarVehiculo(v);    
        Piso p = pisoService.recuperarPiso(idPiso);        
        p.agregarVehiculo(v);
    }    
}
