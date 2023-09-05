package co.edu.javeriana.parkingApp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import co.edu.javeriana.parkingApp.model.Piso;
import co.edu.javeriana.parkingApp.service.PisoService;

@Controller
@RequestMapping("/parkingApp")
public class ParkingController {

    @Autowired
    private PisoService pisoService;

    Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/piso/view/{idPiso}")
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

    @PostMapping("/piso/save")
    public RedirectView guardarPiso(@ModelAttribute Piso piso) {
        log.info("Entre!");
        pisoService.guardarPiso(piso);
        return new RedirectView("/parkingApp/list");
    }
}

