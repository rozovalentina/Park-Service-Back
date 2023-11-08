package co.edu.javeriana.parkingApp.controller.gestionPraqueaderos;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.parkingApp.model.Vehiculo;
import co.edu.javeriana.parkingApp.model.dto.dtoVehiculo;
import co.edu.javeriana.parkingApp.service.PisoService;
import co.edu.javeriana.parkingApp.service.VehiculoService;
import jakarta.validation.Valid;
import co.edu.javeriana.parkingApp.model.Piso;
import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/gestionParqueaderos")
public class PisoControllerRest {
    Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private VehiculoService vehiculoService;
    @Autowired
    private PisoService pisoService;

    @CrossOrigin("http://localhost:4200/")
    @PostMapping("/registrarVehiculo")
    public Piso registrarVehiculo(@Valid @RequestBody Vehiculo v, @RequestParam Long idPiso){   
        Piso p = pisoService.recuperarPiso(idPiso);    
        v.setPiso(p);    
        vehiculoService.guardarVehiculo(v);  
        p.agregarVehiculo(v);
        pisoService.guardarPiso(p);
        return p;
    }   
    @CrossOrigin("http://localhost:4200/")
    @GetMapping("/registrarSalida/{idVehiculo}")
    public dtoVehiculo registrarSalida(@PathVariable("idVehiculo") int idVehiculo){
        return pisoService.registrarSalida(idVehiculo);
    }
    @CrossOrigin("http://localhost:4200/")
    @GetMapping("/pisosPorTipoVehiculo/{tipoVehiculo}")
    public List<Piso> mostrarPisosPorTipoVehiculo(@PathVariable("tipoVehiculo") char tipoVehiculo){
        List<Piso> pisos= pisoService.listarPisos();
        List<Piso> aRetornar= new ArrayList<>();
        for (Piso piso : pisos) {
            if(piso.getTipoVehiculo().getTipo()==tipoVehiculo){
                aRetornar.add(piso);
            }
        }
        return aRetornar;
    }

}
