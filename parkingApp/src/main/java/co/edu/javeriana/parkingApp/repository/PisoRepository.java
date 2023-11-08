package co.edu.javeriana.parkingApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.parkingApp.model.Piso;

@Repository
public interface PisoRepository extends JpaRepository<Piso, Long> {
    // https://www.baeldung.com/spring-data-derived-queries
    List<Piso> findAllById(int id);
    
    // JPQL
    // https://www.baeldung.com/spring-data-jpa-query
    @Query("SELECT p FROM Piso p WHERE p.id = :id")
    List<Piso> findPisoById(int id);

    void deleteById(int id);    
    
    @Query("SELECT p FROM Piso p INNER JOIN  Vehiculo v ON  p.id = v.id WHERE v.id = :idVehiculo")
    List<Piso> findPisoByVehiculoId(@Param("idVehiculo") int idVehiculo);

}