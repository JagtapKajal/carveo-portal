package carveo_portal.carveoManagement.controller;

import carveo_portal.carveoManagement.entity.Vehicle;
import carveo_portal.carveoManagement.serviceImpl.VehicleServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleServiceImpl vehicleServiceImpl;

    public VehicleController(VehicleServiceImpl vehicleServiceImpl) {
        this.vehicleServiceImpl = vehicleServiceImpl;
    }

    // API to save/add Vehicle entity
    @PostMapping("/createVehicle")
    public ResponseEntity<?> createVehicle(@Valid @RequestBody Vehicle vehicle) {
         vehicleServiceImpl.createVehicle(vehicle);
         return new ResponseEntity<>("Data saved", HttpStatus.CREATED);
    }

}
