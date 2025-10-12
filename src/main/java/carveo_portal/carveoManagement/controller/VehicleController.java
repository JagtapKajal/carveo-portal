package carveo_portal.carveoManagement.controller;

import carveo_portal.carveoManagement.service.VehicleService;
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
@CrossOrigin(origins = "http://localhost:5174", allowedHeaders = "*", methods = {RequestMethod.GET,
        RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
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


    //API to get all vehicles
    @GetMapping("/getAllVehicles")
    public ResponseEntity<List<VehicleDTO>> getAllVehicle() {
        List<VehicleDTO> vehicleDTOList = vehicleService.getAllVehicle();
        return new ResponseEntity<>(vehicleDTOList,HttpStatus.OK);
    }

    //API to update vehicle by resident id
    @PutMapping("/UpdateVehicleById/{id}")
    public ResponseEntity<Vehicle> updateVehicleById(@PathVariable("id") Long id, @RequestBody Vehicle vehicle){
    Vehicle updateVehicle = vehicleService.updateVehicle(id, vehicle);
    return new ResponseEntity<>(updateVehicle,HttpStatus.OK);
    }

    @DeleteMapping("/deleteVehicleById/{id}")
    public ResponseEntity<String> deleteVehicleById(@PathVariable("id") Long id){
        String deleteVehicle = vehicleService.deleteVehicle(id);
        return new ResponseEntity<>(deleteVehicle, HttpStatus.OK);
    }
}
