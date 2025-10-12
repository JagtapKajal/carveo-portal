package carveo_portal.carveoManagement.serviceImpl;

import carveo_portal.carveoManagement.service.VehicleService;
import carveo_portal.carveoManagement.VehicleDTO;
import carveo_portal.carveoManagement.entity.Resident;
import carveo_portal.carveoManagement.entity.Vehicle;
import carveo_portal.carveoManagement.exceptionHandling.ResourceNotFoundException;
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

    // Method to get all vehicles
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

    // method to update vehicle
    @Override
    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        Vehicle v1 = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found " + id));

        v1.setRegistrationnumber(vehicle.getRegistrationnumber());
        v1.setVname(vehicle.getVname());
        v1.setColor(vehicle.getColor());
        v1.setType(vehicle.getType());
        v1.setIntime(vehicle.getIntime());
        v1.setOuttime(vehicle.getOuttime());
        v1.setIsvehicleactive(vehicle.isIsvehicleactive());

        if (vehicle.getId() != null) {
            Resident r = residentRepository.findById(vehicle.getId().intValue())
                    .orElseThrow(() -> new ResourceNotFoundException("Resident not found"));
            v1.setResident(r);
        }

        return vehicleRepository.save(v1);
    }

    // Delete vehicle by id
    @Override
    public String deleteVehicle(Long id) {
        try{
            Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(()
                    -> new ResourceNotFoundException("Vehicle not found " + id));
            vehicleRepository.deleteById(id);
            return "Vehicle deleted successfully" + id;
        }
        catch (ResourceNotFoundException e){
            return "Vehicle not found " + id;
        }

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
