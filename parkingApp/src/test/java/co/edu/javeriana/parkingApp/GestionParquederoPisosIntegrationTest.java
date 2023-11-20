package co.edu.javeriana.parkingApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import co.edu.javeriana.parkingApp.model.Edificio;
import co.edu.javeriana.parkingApp.model.Piso;
import co.edu.javeriana.parkingApp.model.TipoVehiculo;
import co.edu.javeriana.parkingApp.model.Vehiculo;
import co.edu.javeriana.parkingApp.model.dto.dtoVehiculo;
import co.edu.javeriana.parkingApp.repository.EdificioRepository;
import co.edu.javeriana.parkingApp.repository.PisoRepository;
import co.edu.javeriana.parkingApp.repository.TipoVehiculoRepository;
import co.edu.javeriana.parkingApp.repository.VehiculoRepository;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class GestionParquederoPisosIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private PisoRepository pisoRepository;
    @Autowired 
    private EdificioRepository edificioRepository;
    @Autowired 
    private TipoVehiculoRepository tipoVehiculoRepository;
    @Autowired 
    private VehiculoRepository vehiculoRepository;

    @BeforeEach
    void init(){
        Edificio ed1 = new Edificio("Pruebas", 100, 100);
        edificioRepository.save(ed1);
        TipoVehiculo tp1 = new TipoVehiculo("A", new BigDecimal(100.0),5,"Lunes");
        TipoVehiculo tp2 = new TipoVehiculo("B", new BigDecimal(150.0),5,"Fines de Semana");
        TipoVehiculo tp3 = new TipoVehiculo("A", new BigDecimal(100.0),5,"Fines de semana");
        TipoVehiculo tp4 = new TipoVehiculo("B", new BigDecimal(150.0),5,"Lunes");
        tipoVehiculoRepository.save(tp1);
        tipoVehiculoRepository.save(tp2);
        tipoVehiculoRepository.save(tp3);
        tipoVehiculoRepository.save(tp4);
        Vehiculo v1= new Vehiculo(tp1, "ABC123", 1200);
        Vehiculo v2= new Vehiculo(tp2, "ABC124", 1300);
        Vehiculo v3= new Vehiculo(tp3, "ABC125", 1400);
        Vehiculo v4= new Vehiculo(tp4, "ABC126", 1500);
        vehiculoRepository.save(v1);
        vehiculoRepository.save(v2);
        vehiculoRepository.save(v3);
        vehiculoRepository.save(v4);
        Piso pi1 = new Piso(ed1, tp1);
        Piso pi2 = new Piso(ed1, tp2);
        Piso pi3 = new Piso(ed1, tp3);
        Piso pi4 = new Piso(ed1, tp4);
        pi1.agregarVehiculo(v1);
        pi2.agregarVehiculo(v2);
        pi3.agregarVehiculo(v3);
        pi4.agregarVehiculo(v4);
        pisoRepository.save(pi1);
        pisoRepository.save(pi2);
        pisoRepository.save(pi3);
        pisoRepository.save(pi4);
    }
    @Autowired
    private TestRestTemplate rest;

    @Test
    void registrarVehiculo() throws Exception{
        TipoVehiculo tp4 = new TipoVehiculo("B", new BigDecimal(150.0),5,"Lunes");
        tipoVehiculoRepository.save(tp4);
        Vehiculo v = new Vehiculo(tp4, "abc127", 1600);
        vehiculoRepository.save(v);
        Piso p= rest.postForObject("http://localhost:"+port+"/gestionParqueaderos/pisos/registrarVehiculo?idPiso=1", v,Piso.class);
        assertEquals(1, p.getId());
    }

    @Test
    void registrarSalida() throws Exception{
        Vehiculo v = vehiculoRepository.findById(Integer.toUnsignedLong(1)).get();
        dtoVehiculo dtoVehiculo2 = rest.getForObject("http://localhost:"+port+"/gestionParqueaderos/pisos/registrarSalida/1", dtoVehiculo.class);
        assertEquals(v.getId(), dtoVehiculo2.getVehiculo().getId());
    }
    @Test
    void registrarSalida2() throws Exception {
        Vehiculo vehiculo = vehiculoRepository.findById(1L).orElse(null);
        dtoVehiculo dtoVehiculo = rest.getForObject("http://localhost:" + port + "/gestionParqueaderos/pisos/registrarSalida/1", dtoVehiculo.class);

        assertEquals(vehiculo.getId(), dtoVehiculo.getVehiculo().getId());
        assertTrue(dtoVehiculo.getTiempo() >= 0); 
    }

    @Test
    void mostrarPisosPorTipoVehiculo() throws Exception {
        List<Piso> pisos = rest.getForObject("http://localhost:" + port + "/gestionParqueaderos/pisos/tipoVehiculos/A", List.class);

        assertEquals(1, pisos.size()); 
    }

    @Test
    void listarPisos() throws Exception {
        List<Piso> pisos = rest.getForObject("http://localhost:" + port + "/gestionParqueaderos/pisos", List.class);

        assertEquals(1, pisos.size()); 
    }

    @Test
    void getVehiculosByPiso() throws Exception {
        List<Vehiculo> vehiculos = rest.getForObject("http://localhost:" + port + "/gestionParqueaderos/pisos/vehiculos/1", List.class);

        assertEquals(1, vehiculos.size()); 
    }

    @Test
    void getPisoById() throws Exception {
        Piso piso = rest.getForObject("http://localhost:" + port + "/gestionParqueaderos/pisos/1", Piso.class);

        assertEquals(1, piso.getId()); 
    }
}

