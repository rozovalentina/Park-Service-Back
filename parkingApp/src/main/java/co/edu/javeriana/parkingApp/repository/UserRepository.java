package co.edu.javeriana.parkingApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.javeriana.parkingApp.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    Optional <UserEntity> findByCorreo(String correo);
    Boolean existsByCorreo(String correo);
}