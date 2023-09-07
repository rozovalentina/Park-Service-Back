package co.edu.javeriana.parkingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.parkingApp.model.Edificio;
import co.edu.javeriana.parkingApp.repository.EdificioRepository;

@Service
public class EdificioServicie {
    @Autowired
    EdificioRepository edificioRepository;
    public Edificio recuperarEdicio(Long id){
        return edificioRepository.findById(id).orElseThrow();
    }
}
