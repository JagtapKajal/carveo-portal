package carveo_portal.carveoManagement.repository;

import carveo_portal.carveoManagement.entity.Resident;
import carveo_portal.carveoManagement.enums.ResidentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Integer> {

    // get Resident List with first name
    List<Resident> findByFnameIgnoreCase(String fname);

    // get List of resident with last name
    List<Resident> findByLnameIgnoreCase(String lname);

    // get list of resident of first name and last name
    List<Resident> findByFnameIgnoreCaseAndLnameIgnoreCase(String fname, String lname);

   Resident findByFlatno(String flatno);


    Resident findByFname(String fname);

    Resident findByparkingslot(String parkingslot);

    List<Resident> findByResidenttype(ResidentType residenttype);
}