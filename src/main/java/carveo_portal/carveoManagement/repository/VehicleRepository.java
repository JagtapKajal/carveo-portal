package carveo_portal.carveoManagement.repository;

import carveo_portal.carveoManagement.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {


Optional<Vehicle> findByRegistrationnumber(String registrationnumber);
}