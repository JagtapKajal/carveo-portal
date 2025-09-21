package carveo_portal.carveoManagement.serviceImpl;

import carveo_portal.carveoManagement.Service.VehicleService;
import carveo_portal.carveoManagement.entity.Resident;
import carveo_portal.carveoManagement.entity.Vehicle;
import carveo_portal.carveoManagement.repository.ResidentRepository;
import carveo_portal.carveoManagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private final VehicleRepository vehicleRepository;

    @Autowired
    private final ResidentRepository residentRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, ResidentRepository residentRepository) {
        this.vehicleRepository = vehicleRepository;
        this.residentRepository = residentRepository;
    }

    // to save Vehicle with Validation
    public ResponseEntity<?> createVehicle(Vehicle vehicle) {
        // Check registration number
        if (vehicle.getRegistrationNumber() == null || vehicle.getRegistrationNumber().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Vehicle registration number is required.");
        }

        // Check resident object
        if (vehicle.getResident() == null || vehicle.getResident().getId() == null) {
            return ResponseEntity.badRequest().body("Vehicle must be associated with a valid Resident.");
        }

        // Verify resident in DB
        Optional<Resident> residentOpt = residentRepository.findById(Math.toIntExact(vehicle.getResident().getId()));
        if (residentOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Resident not found, cannot assign vehicle.");
        }

        vehicle.setResident(residentOpt.get());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return ResponseEntity.ok(savedVehicle);
    }


}
