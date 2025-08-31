package carveo_portal.carveoManagement.controller;

import carveo_portal.carveoManagement.entity.Resident;
import carveo_portal.carveoManagement.exceptionHandling.ResourceNotFoundException;
import carveo_portal.carveoManagement.service.ResidentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residents")
public class ResidentController {

    private final ResidentService residentService;

    public ResidentController(ResidentService residentService){
        this.residentService = residentService;
    }

    @PostMapping("/residents")
    public Resident saveResident(@RequestBody Resident resident) {
        // Link each vehicle back to this resident
        resident.getVehicleList().forEach(vehicle -> vehicle.setResident(resident));

        return residentService.saveResident(resident);
    }

    @PostMapping("/listofresident")
    public ResponseEntity<List<Resident>> saveAllResidents(@RequestBody List<Resident> residents) {
        return ResponseEntity.ok(residentService.saveAll(residents));
    }

    @GetMapping("/ResidentById/{id}")
    public Resident getResidentById(@PathVariable int id){
        return residentService.getResidentById(id).orElseThrow(()-> new ResourceNotFoundException("Resident not found with id: " +id));
    }

}

