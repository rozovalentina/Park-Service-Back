package co.edu.javeriana.parkingApp.controller.gestionPraqueaderos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @PostMapping("/crearVehiculo")
    public Vehiculo crearVehiculo(@Valid @RequestBody Vehiculo v){
        return vehiculoService.guardarVehiculo(v);
    }

    @CrossOrigin("http://localhost:4200/")
    @GetMapping("/listarVehiculos")
    public List<Vehiculo> listarVehiculos(){
        return vehiculoService.findALL();
    }
    @CrossOrigin("http://localhost:4200/")
    @GetMapping("/buscarVehiculo/{idVehiculo}")
    public ResponseEntity<Vehiculo> getVehiculo(@PathVariable("idVehiculo") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(vehiculoService.recuperarVehiculo(id));
    }
}
