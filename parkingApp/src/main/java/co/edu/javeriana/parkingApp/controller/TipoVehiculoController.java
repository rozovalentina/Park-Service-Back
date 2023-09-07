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

import co.edu.javeriana.parkingApp.model.TipoVehiculo;
import co.edu.javeriana.parkingApp.service.TipoVehiculoService;

@Controller
@RequestMapping("/parkingApp/tipoVehiculo")
public class TipoVehiculoController {

    @Autowired
    private TipoVehiculoService tipoVehiculoService;

    Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/view/{idTipoVehiculo}")
    public ModelAndView recuperarTipoVehiculo(@PathVariable Long idTipoVehiculo) {
        TipoVehiculo tipoVehiculo = tipoVehiculoService.recuperarTipoVehiculo(idTipoVehiculo);
        ModelAndView tipoVehiculoView = new ModelAndView("tipoVehiculo-view");
        tipoVehiculoView.addObject("tipoVehiculo", tipoVehiculo);
        return tipoVehiculoView;
    }

    @GetMapping("/list")
    public ModelAndView listarTipoVehiculos() {
        List<TipoVehiculo> tipoVehiculos = tipoVehiculoService.listarTipoVehiculos();
        ModelAndView tipoVehiculoListView = new ModelAndView("tipoVehiculo-list");
        tipoVehiculoListView.addObject("allTipoVehiculos", tipoVehiculos);
        return tipoVehiculoListView;
    }

    @GetMapping("/edit/{idTipoVehiculo}")
    public ModelAndView editarPiso(@PathVariable Long idTipoVehiculo) {
        TipoVehiculo tipoVehiculo = tipoVehiculoService.recuperarTipoVehiculo(idTipoVehiculo);
        ModelAndView tipoVehiculoView = new ModelAndView("tipoVehiculo-edit");
        tipoVehiculoView.addObject("tipoVehiculo", tipoVehiculo);
        return tipoVehiculoView;
    }

    @PostMapping("/save")
    public RedirectView guardarTipoVehiculo(@ModelAttribute TipoVehiculo tipoVehiculo) {
        tipoVehiculoService.recuperarTipoVehiculo(tipoVehiculo.getId());
        tipoVehiculoService.guardarTipoVehiculo(tipoVehiculo);
        return new RedirectView("/parkingApp/tipoVehiculo/list");
    }
    @GetMapping("/create")
    public String mostrarTipoVehiculoNuevo(Model model){
        model.addAttribute("tipoVehiculo", new TipoVehiculo());
        return "tipoVehiculo-create";
    }
    @GetMapping("/delete/{idTipoVehiculo}")
    public RedirectView borrarTipoVehiculo(@PathVariable Long idTipoVehiculo){
        tipoVehiculoService.borrarTipoVehiculo(idTipoVehiculo);
        return new RedirectView("/parkingApp/tipoVehiculo/list");
    }
    
}

