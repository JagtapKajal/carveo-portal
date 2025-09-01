package carveo_portal.carveoManagement.repository;

import carveo_portal.carveoManagement.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {
    Resident findByFnameAndLname(String fname, String lname);}
