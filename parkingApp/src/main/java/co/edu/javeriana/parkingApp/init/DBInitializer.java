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
import co.edu.javeriana.parkingApp.model.TipoVehiculo;
import co.edu.javeriana.parkingApp.repository.EdificioRepository;
import co.edu.javeriana.parkingApp.repository.PisoRepository;
import co.edu.javeriana.parkingApp.repository.VehiculoRepository;
import co.edu.javeriana.parkingApp.repository.TipoVehiculoRepository;


@Component
public class DBInitializer implements CommandLineRunner {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private EdificioRepository edificioRepository;

    @Autowired
    private PisoRepository pisoRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private TipoVehiculoRepository tipoVehiculoRepository;

    @Override
    public void run(String... args) throws Exception {
        Edificio ed = edificioRepository.save(new Edificio("El propio parqueadero",100, 100));

        TipoVehiculo tv1 = tipoVehiculoRepository.save(new TipoVehiculo('C', 160, 8));
        TipoVehiculo tv2 = tipoVehiculoRepository.save(new TipoVehiculo('M', 60, 2));
        TipoVehiculo tv3 = tipoVehiculoRepository.save(new TipoVehiculo('B', 200, 24));

        Piso p1 = pisoRepository.save(new Piso(ed, tv1));
        Piso p2 = pisoRepository.save(new Piso(ed, tv2));
        Piso p3 = pisoRepository.save(new Piso(ed, tv3));

        Vehiculo v1 = vehiculoRepository.save(new Vehiculo(tv1,"NC099",1600));
        Vehiculo v2 = vehiculoRepository.save(new Vehiculo(tv1,"JKE345",0000));
        Vehiculo v3 = vehiculoRepository.save(new Vehiculo(tv1,"DHR324",1200));
        Vehiculo v4 = vehiculoRepository.save(new Vehiculo(tv2,"UDJ14F",900));
        Vehiculo v5 = vehiculoRepository.save(new Vehiculo(tv2,"OPK35G",2330));
        Vehiculo v6 = vehiculoRepository.save(new Vehiculo(tv2,"LRH71H",1800));
        Vehiculo v7 = vehiculoRepository.save(new Vehiculo(tv3,"MOF983",1700));
        Vehiculo v8 = vehiculoRepository.save(new Vehiculo(tv3,"AID123",1600));

        log.info("Database initialized");

        List<Piso> pisos = pisoRepository.findAllById(1);
        for (Piso piso : pisos) {
            log.info(piso.getTipoVehiculo() + " " + piso.getTotalVehiculos());
        }
    }

}

