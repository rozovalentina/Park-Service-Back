package co.edu.javeriana.parkingApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.parkingApp.model.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    // https://www.baeldung.com/spring-data-derived-queries
    List<Vehiculo> findAllById(int id);
    
    
    // JPQL
    // https://www.baeldung.com/spring-data-jpa-query
    @Query("SELECT p from Vehiculo p WHERE p.id = :id")
    List<Vehiculo> findVehiculoById(int id);

}
