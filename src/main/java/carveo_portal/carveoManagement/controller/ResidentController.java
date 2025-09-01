package carveo_portal.carveoManagement.controller;

import carveo_portal.carveoManagement.entity.Resident;
import carveo_portal.carveoManagement.exceptionHandling.ResourceNotFoundException;
import carveo_portal.carveoManagement.service.ResidentService;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/getAllResident")
    public ResponseEntity<List<Resident>> getAllResidents() {
        return ResponseEntity.ok(residentService.getAllResident());
    }

    @GetMapping("/getByName")
    public ResponseEntity<?> getResidentByFnameAndLname(
            @RequestParam String fname,
            @RequestParam String lname) {


        // Validation: no numeric values allowed
        if (!fname.matches("[a-zA-Z]+") || !lname.matches("[a-zA-Z]+")) {
            return ResponseEntity.badRequest().body("First Name and Last Name should not contain numbers.");
        }

        //it will find resident from database
        Resident resident = residentService.getResidentByFnameAndLname(fname, lname);

        //if data is not found in db
        if (resident == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No resident found with the name " + fname + " " + lname);
        }
        return ResponseEntity.ok(resident);
    }
}

