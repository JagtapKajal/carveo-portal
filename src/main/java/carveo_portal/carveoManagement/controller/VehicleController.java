package carveo_portal.carveoManagement.controller;

import carveo_portal.carveoManagement.Service.VehicleService;
import carveo_portal.carveoManagement.entity.Vehicle;
import carveo_portal.carveoManagement.serviceImpl.VehicleServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // API to save/add Vehicle entity
    @PostMapping("/createVehicle")
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle) {
         Vehicle savevehicle = vehicleService.createVehicle(vehicle);
         return new ResponseEntity<>(savevehicle, HttpStatus.CREATED);
    }




}
