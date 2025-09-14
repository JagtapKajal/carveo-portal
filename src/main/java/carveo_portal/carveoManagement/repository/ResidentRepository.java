package carveo_portal.carveoManagement.repository;

import carveo_portal.carveoManagement.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {

    // get Resident List with first name
    List<Resident> findByFnameIgnoreCase(String fname);

    // get List of resident with last name
    List<Resident> findByLnameIgnoreCase(String lname);

    // get list of resident of first name and last name
    List<Resident> findByFnameIgnoreCaseAndLnameIgnoreCase(String fname, String lname);

}