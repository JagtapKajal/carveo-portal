package carveo_portal.carveoManagement.repository;

import carveo_portal.carveoManagement.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
