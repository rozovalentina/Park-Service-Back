package co.edu.javeriana.parkingApp.controller.gestionPraqueaderos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.parkingApp.model.TipoVehiculo;
import co.edu.javeriana.parkingApp.service.PisoService;
import co.edu.javeriana.parkingApp.service.TipoVehiculoService;
import co.edu.javeriana.parkingApp.service.VehiculoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/gestionParqueaderos/tipoVehiculo")
public class TipoVehiculoRest {
    @Autowired
    private VehiculoService vehiculoService;
    @Autowired
    private PisoService pisoService;
    @Autowired
    private TipoVehiculoService tipoVehiculoService;

    @GetMapping("/{idTipoVehiculo}")
    public TipoVehiculo recuperarTipoVehiculo(@PathVariable int idTipoVehiculo){
        return this.tipoVehiculoService.recuperarTipoVehiculo(Integer.toUnsignedLong(idTipoVehiculo));
    }

    @GetMapping("/list")
    public List<TipoVehiculo> listarTipoVehiculos(){
        return tipoVehiculoService.listarTipoVehiculos();
    }

    @PostMapping("/save")
    public TipoVehiculo guardarTipoVehiculo( @RequestBody TipoVehiculo tipoVehiculo){
        System.out.println(tipoVehiculo.toString());
        return tipoVehiculoService.guardarTipoVehiculoRest(tipoVehiculo);
    }
    
}
