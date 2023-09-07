package co.edu.javeriana.parkingApp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import co.edu.javeriana.parkingApp.model.Edificio;
import co.edu.javeriana.parkingApp.model.Piso;
import co.edu.javeriana.parkingApp.model.TipoVehiculo;
import co.edu.javeriana.parkingApp.service.EdificioServicie;
import co.edu.javeriana.parkingApp.service.PisoService;
import co.edu.javeriana.parkingApp.service.TipoVehiculoService;

@Controller
@RequestMapping("/parkingApp/piso")
public class PisoController {

    @Autowired
    private PisoService pisoService;
    @Autowired
    private TipoVehiculoService tipoVehiculoService;
    @Autowired
    private EdificioServicie edificioServicie;
    Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/view/{idPiso}")
    public ModelAndView recuperarPiso(@PathVariable Long idPiso) {
        Piso piso = pisoService.recuperarPiso(idPiso);
        ModelAndView pisoView = new ModelAndView("piso-view");
        pisoView.addObject("piso", piso);
        return pisoView;
    }

    @GetMapping("/list")
    public ModelAndView listarPisos() {
        List<Piso> pisos = pisoService.listarPisos();
        ModelAndView pisoListView = new ModelAndView("piso-list");
        pisoListView.addObject("allPisos", pisos);
        return pisoListView;
    }

    @GetMapping("/edit/{idPiso}")
    public ModelAndView editarPiso(@PathVariable Long idPiso) {
        Piso piso = pisoService.recuperarPiso(idPiso);
        ModelAndView pisoEditView = new ModelAndView("piso-edit");
        pisoEditView.addObject("piso", piso);
        return pisoEditView;
    }

    @PostMapping("/save")
    public RedirectView guardarPiso(@ModelAttribute Piso piso) {
        TipoVehiculo p=tipoVehiculoService.recuperTipoVehiculoPorTipo(piso.getTipoVehiculo().getTipo());
        Edificio e= edificioServicie.recuperarEdicio(Long.valueOf(1));
        if(p!=null){
            piso.setTipoVehiculo(p);
            piso.setEdificio(e);
            piso.calcularEspacioDisponible();
        }else{
            return new RedirectView("/parkingApp/tipoVehiculo/create");
        }
        
        pisoService.guardarPiso(piso);
        return new RedirectView("/parkingApp/piso/list");
    }
    @GetMapping("/create")
    public String mostrarPisoNuevo(Model model){
        Piso p= new Piso();
        model.addAttribute("piso", p);
        return "piso-create";
    }
    @GetMapping("/delete/{idPiso}")
    public ModelAndView borrarPiso(@PathVariable Long idPiso){
        String mensaje= pisoService.borrarPiso(idPiso);
        List<Piso> pisos = pisoService.listarPisos();
        ModelAndView pisoListView = new ModelAndView("piso-list");
        pisoListView.addObject("allPisos", pisos);
        if(mensaje.startsWith("No")){
            pisoListView.addObject("error", mensaje);
        }else{
            log.info(mensaje);
            pisoListView.addObject("success", mensaje);
        }
        return pisoListView;
    }
    
}

