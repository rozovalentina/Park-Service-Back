package co.edu.javeriana.parkingApp.init;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import co.edu.javeriana.parkingApp.model.Edificio;
import co.edu.javeriana.parkingApp.model.Piso;
import co.edu.javeriana.parkingApp.model.Vehiculo;
import co.edu.javeriana.parkingApp.repository.EdificioRepository;
import co.edu.javeriana.parkingApp.repository.PisoRepository;
import co.edu.javeriana.parkingApp.repository.VehiculoRepository;

@Component
public class DBInitializer implements CommandLineRunner {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private EdificioRepository edificioRepository;

    @Autowired
    private PisoRepository pisoRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public void run(String... args) throws Exception {
        Edificio ed = edificioRepository.save(new Edificio("El propio parqueadero",1000, 1000));


        Piso p1 = pisoRepository.save(new Piso(ed, 'C', 3, 102, 47));
        Piso p2 = pisoRepository.save(new Piso(ed, 'M', 4, 60, 46));
        Piso p3 = pisoRepository.save(new Piso(ed, 'C', 1, 102, 49));



        Vehiculo v1 = vehiculoRepository.save(new Vehiculo(p1,'C',1600));
        Vehiculo v2 = vehiculoRepository.save(new Vehiculo(p1,'C',0000));
        Vehiculo v3 = vehiculoRepository.save(new Vehiculo(p1,'C',1200));
        Vehiculo v4 = vehiculoRepository.save(new Vehiculo(p2,'M',900));
        Vehiculo v5 = vehiculoRepository.save(new Vehiculo(p2,'M',2330));
        Vehiculo v6 = vehiculoRepository.save(new Vehiculo(p2,'M',1800));
        Vehiculo v7 = vehiculoRepository.save(new Vehiculo(p2,'M',1700));
        Vehiculo v8 = vehiculoRepository.save(new Vehiculo(p3,'C',1600));



        log.info("Database initialized");


        List<Piso> pisos = pisoRepository.findAllById(1);
        for (Piso piso : pisos) {
            log.info(piso.getTarifa() + " " + piso.getTipoVehiculoP() + " " + piso.getTotalVehiculos());
        }
    }

}

