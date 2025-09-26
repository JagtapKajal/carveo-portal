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

    // get resident by flat no
   Resident findByFlatno(String flatno);

   // get resident by  firstname
    Resident findByFname(String fname);

    // find resident by parking slot
    Resident findByparkingslot(String parkingslot);

    // get resident by resident type
    List<Resident> findByResidenttype(ResidentType residenttype);
}