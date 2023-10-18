package co.edu.javeriana.parkingApp.controller.gestionPraqueaderos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.parkingApp.model.Vehiculo;
import co.edu.javeriana.parkingApp.service.VehiculoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/gestionParqueaderos")
public class VehiculoControllerRest {
    @Autowired
    private VehiculoService vehiculoService;

    @CrossOrigin("http://localhost:4200/")
    @PostMapping("")
    public Vehiculo crearVehiculo(@Valid @RequestBody Vehiculo v){
        return vehiculoService.guardarVehiculo(v);
    }
}
