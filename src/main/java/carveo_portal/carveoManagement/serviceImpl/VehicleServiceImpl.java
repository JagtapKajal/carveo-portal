package carveo_portal.carveoManagement.serviceImpl;

import carveo_portal.carveoManagement.Service.VehicleService;
import carveo_portal.carveoManagement.VehicleDTO;
import carveo_portal.carveoManagement.entity.Resident;
import carveo_portal.carveoManagement.entity.Vehicle;
import carveo_portal.carveoManagement.repository.ResidentRepository;
import carveo_portal.carveoManagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

//    @Override
//    public List<Vehicle> getAllVehicles() {
//        return vehicleRepository.findAll();
//    }

    @Override
    public List<VehicleDTO> getAllVehicle() {

        List<Vehicle> vehicles = vehicleRepository.findAll();

        return vehicles.stream().map(vehicle -> {
            VehicleDTO dto = new VehicleDTO();
            dto.setId(vehicle.getId());
            dto.setRegistrationnumber(vehicle.getRegistrationnumber());
            dto.setVname(vehicle.getVname());
            dto.setColor(vehicle.getColor());
            dto.setType(vehicle.getType());
            dto.setIntime(vehicle.getIntime());
            dto.setOuttime(vehicle.getOuttime());
            dto.setIsvehicleactive(vehicle.isIsvehicleactive());

            // ✅ Map Resident details
            if (vehicle.getResident() != null) {

                dto.setResidentName(vehicle.getResident().getFname() + " " + vehicle.getResident().getLname());


            }

            return dto;
        }).collect(Collectors.toList());
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
