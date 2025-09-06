package carveo_portal.carveoManagement.repository;

import carveo_portal.carveoManagement.entity.Visitor;
import carveo_portal.carveoManagement.enums.VisitorType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VisitorRepository extends JpaRepository<Visitor, Integer> {

    // repo to find by registration number
    List<Visitor> findByVehicleRegistrationNumber(String registrationNumber);

    // repo to get visitor by endtime
    Optional<Visitor> findTopByVehicleRegistrationNumberOrderByTimeinDesc(String registrationNumber);

    //to get visitor type to find visitor is active or not
    List<Visitor> findByVisitorTypeInAndIsactivevisitorTrue(List<VisitorType> types);

    // to get visitor is active or not
    List<Visitor> findByIsactivevisitorTrue();

//    // multiple visitors ke liye (list return karega)
//    List<Visitor> findAllByVehicleRegistrationNumber(String vehicleRegistrationNumber);
}
