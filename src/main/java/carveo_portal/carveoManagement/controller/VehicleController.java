package carveo_portal.carveoManagement.controller;

import carveo_portal.carveoManagement.entity.Resident;
import carveo_portal.carveoManagement.entity.Vehicle;
import carveo_portal.carveoManagement.entity.Visitor;
import carveo_portal.carveoManagement.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // API to save/add Vehicle entity
    @PostMapping("/createVehicle")
    public ResponseEntity<?> createVehicle(@Valid @RequestBody Vehicle vehicle) {
         vehicleService.createVehicle(vehicle);
         return new ResponseEntity<>("Data saved", HttpStatus.CREATED);
    }

}
