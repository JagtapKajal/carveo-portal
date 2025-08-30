package carveo_portal.carveoManagement.repository;

import carveo_portal.carveoManagement.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {
}
