package carveo_portal.carveoManagement.service;

import carveo_portal.carveoManagement.VehicleDTO;
import carveo_portal.carveoManagement.entity.Resident;
import carveo_portal.carveoManagement.entity.Vehicle;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VehicleService {

    // to save Vehicle with Validation
    public Vehicle createVehicle(Vehicle vehicle);

//    public List<Vehicle> getAllVehicles();

    // Method to get all vehicles
    public List<VehicleDTO> getAllVehicle();

    // method to update vehicles by resident id
    public Vehicle updateVehicle(Long id, Vehicle vehicle);

    public String deleteVehicle(Long id);
}
