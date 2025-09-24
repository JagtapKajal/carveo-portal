package carveo_portal.carveoManagement.Service;

import carveo_portal.carveoManagement.entity.Resident;
import carveo_portal.carveoManagement.entity.Vehicle;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VehicleService {

    // to save Vehicle with Validation
    public Vehicle createVehicle(Vehicle vehicle);



}
