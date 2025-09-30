package carveo_portal.carveoManagement.controller;

import carveo_portal.carveoManagement.Service.VehicleService;
import carveo_portal.carveoManagement.VehicleDTO;
import carveo_portal.carveoManagement.entity.Vehicle;
import carveo_portal.carveoManagement.serviceImpl.VehicleServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
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

//    @GetMapping("/getAllVehicle")
//    public ResponseEntity<List<Vehicle>> getAllVehicles(){
//        return ResponseEntity.ok(vehicleService.getAllVehicles());
//    }

    @GetMapping("/getAllVehicles")
    public ResponseEntity<List<VehicleDTO>> getAllVehicle() {
        List<VehicleDTO> vehicleDTOList = vehicleService.getAllVehicle();
        return new ResponseEntity<>(vehicleDTOList,HttpStatus.OK);
    }

}
