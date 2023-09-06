package co.edu.javeriana.parkingApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.parkingApp.model.Edificio;

@Repository
public interface EdificioRepository extends JpaRepository<Edificio, Long> {
    // https://www.baeldung.com/spring-data-derived-queries
    List<Edificio> findAllByName(String name);
    List<Edificio> findAllByNameStartingWith(String name);
    List<Edificio> findAllByNameStartingWithIgnoreCase(String name);
    
    // JPQL
    // https://www.baeldung.com/spring-data-jpa-query
    @Query("SELECT e FROM Edificio e WHERE e.name LIKE :text%")
    List<Edificio> findEdificioByNameStartingWith(@Param("text") String text);
    


}
