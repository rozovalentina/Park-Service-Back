package co.edu.javeriana.parkingApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import co.edu.javeriana.parkingApp.model.Edificio;
import co.edu.javeriana.parkingApp.model.Piso;
import co.edu.javeriana.parkingApp.model.TipoVehiculo;
import co.edu.javeriana.parkingApp.model.Vehiculo;
import co.edu.javeriana.parkingApp.repository.EdificioRepository;
import co.edu.javeriana.parkingApp.repository.PisoRepository;
import co.edu.javeriana.parkingApp.repository.TipoVehiculoRepository;
import co.edu.javeriana.parkingApp.repository.VehiculoRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class GestionParqueaderoVehiculoTest {
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
    @Autowired
    private TestRestTemplate rest;
    
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
    @Test
    void crearVehiculo() throws Exception {
        TipoVehiculo tipoVehiculo = tipoVehiculoRepository.findById(1L).orElse(null);
        Vehiculo vehiculo = new Vehiculo(tipoVehiculo, "ABC123", 1200);

        Vehiculo vehiculoCreado = rest.postForObject("http://localhost:" + port + "/gestionParqueaderos/crearVehiculo", vehiculo, Vehiculo.class);

        assertNotNull(vehiculoCreado.getId());
        assertEquals(vehiculo.getPlaca(), vehiculoCreado.getPlaca());
    }

    @Test
    void listarVehiculos() throws Exception {
        List<Vehiculo> vehiculos = rest.getForObject("http://localhost:" + port + "/gestionParqueaderos/listarVehiculos", List.class);

        assertTrue(vehiculos.size() > 0); // Ajusta según tu lógica
    }

    @Test
    void getVehiculo() throws Exception {
        Vehiculo vehiculo = vehiculoRepository.findById(1L).orElse(null);
        ResponseEntity<Vehiculo> responseEntity = rest.getForEntity("http://localhost:" + port + "/gestionParqueaderos/buscarVehiculo/1", Vehiculo.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(vehiculo.getId(), responseEntity.getBody().getId());
    }


}
