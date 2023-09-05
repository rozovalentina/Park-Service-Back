package co.edu.javeriana.parkingApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.parkingApp.model.Piso;

@Repository
public interface PisoRepository extends JpaRepository<Piso, Long> {
    // https://www.baeldung.com/spring-data-derived-queries
    List<Piso> findAllById(int id);
    
    // JPQL
    // https://www.baeldung.com/spring-data-jpa-query
    @Query("SELECT p from piso p WHERE p.id = concat(:text)")
    List<Piso> findPisoById(int text);

}