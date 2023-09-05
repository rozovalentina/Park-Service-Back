package co.edu.javeriana.parkingApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.parkingApp.model.TipoVehiculo;

@Repository
public interface TipoVehiculoRepository extends JpaRepository<TipoVehiculo, Long> {
    // https://www.baeldung.com/spring-data-derived-queries
    List<TipoVehiculo> findAllById(int id);
    
    // JPQL
    // https://www.baeldung.com/spring-data-jpa-query
    @Query("SELECT p FROM tipo_vehiculo p WHERE p.id = :id")
    List<TipoVehiculo> findTipoVehiculoById(int id);

    void deleteById(int id);
}
