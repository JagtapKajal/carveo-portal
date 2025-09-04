package carveo_portal.carveoManagement.repository;

import carveo_portal.carveoManagement.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VisitorRepository extends JpaRepository<Visitor, Integer> {
    List<Visitor> findByVehicleRegistrationNumber(String registrationNumber);

}
