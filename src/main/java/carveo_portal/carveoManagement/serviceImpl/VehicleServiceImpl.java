package carveo_portal.carveoManagement.serviceImpl;

import carveo_portal.carveoManagement.Service.VehicleService;
import carveo_portal.carveoManagement.entity.Resident;
import carveo_portal.carveoManagement.entity.Vehicle;
import carveo_portal.carveoManagement.repository.ResidentRepository;
import carveo_portal.carveoManagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // method to create save vehicles
    @Override
    public Vehicle createVehicle(Vehicle vehicle) {

       Vehicle vehiclelist =  vehicleRepository.save(vehicle);
        return vehiclelist;
    }

//    public Vehicle createVehicle(Vehicle vehicle) {
//        if (vehicle.getRegistrationnumber() == null || vehicle.getRegistrationnumber().trim().isEmpty()) {
//            throw new IllegalArgumentException("Vehicle registration number is required.");
//        }
//
//        if (vehicle.getResident() == null || vehicle.getResident().getId() == null) {
//            throw new IllegalArgumentException("Vehicle must be associated with a valid Resident.");
//        }
//
//        Resident resident = residentRepository.findById(Math.toIntExact(vehicle.getResident().getId()))
//                .orElseThrow(() -> new IllegalArgumentException("Resident not found, cannot assign vehicle."));
//
//        vehicle.setResident(resident);
//
//        return vehicleRepository.save(vehicle);
//    }



}
