package carveo_portal.carveoManagement.Service;

import carveo_portal.carveoManagement.entity.Vehicle;
import org.springframework.http.ResponseEntity;

public interface VehicleService {

    // to save Vehicle with Validation
    public ResponseEntity<?> createVehicle(Vehicle vehicle);


}
