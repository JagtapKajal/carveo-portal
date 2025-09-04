package carveo_portal.carveoManagement.repository;

import carveo_portal.carveoManagement.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {

    List<Resident> findByFnameIgnoreCase(String fname);

    List<Resident> findByLnameIgnoreCase(String lname);

    List<Resident> findByFnameIgnoreCaseAndLnameIgnoreCase(String fname, String lname);

}