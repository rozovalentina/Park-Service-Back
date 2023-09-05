package co.edu.javeriana.parkingApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.parkingApp.model.Piso;
import co.edu.javeriana.parkingApp.repository.PisoRepository;

@Service
public class PisoService {
    
    @Autowired
    private PisoRepository pisoRepository;

    public Piso recuperarPiso(Long id) {
        return pisoRepository.findById(id).orElseThrow();
    }

    public List<Piso> listarPisos() {
        return pisoRepository.findAll();
    }

    public void guardarPiso(Piso piso) {
        pisoRepository.save(piso);
    }
}
