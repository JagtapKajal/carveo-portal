package carveo_portal.carveoManagement.controller;

import carveo_portal.carveoManagement.Service.ResidentService;
import carveo_portal.carveoManagement.entity.Resident;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/residents")
public class ResidentController {

    @Autowired
   private ResidentService residentService;

    // API to save Resident
    @PostMapping("/saveResident")
    public ResponseEntity<?> addResident(@Valid @RequestBody Resident resident) {
        residentService.saveResident(resident);
        return new ResponseEntity<>("Data Saved", HttpStatus.CREATED);
    }

    //API to save list of resident
    @PostMapping("/listofresident")
    public ResponseEntity<List<Resident>> saveAllResidents(@RequestBody List<Resident> residents) {
        return ResponseEntity.ok(residentService.saveAll(residents));
    }

    // API to get Resident by Id
    @GetMapping("/ResidentById/{id}")
    public Resident getResidentById(@PathVariable int id) {
        return residentService.getResidentById(id);
    }

    // API to get List of Resident
    @GetMapping("/getAllResident")
    public ResponseEntity<List<Resident>> getAllResidents() {
        return ResponseEntity.ok(residentService.getAllResident());
    }

    // API to get Resident by first name and lastname or both
    @GetMapping("/getByName")
    public ResponseEntity<?> getByName(@RequestParam(required = false) String fname,
                                       @RequestParam(required = false) String lname) {

        // validation: no numbers allowed
        if ((fname != null && fname.matches(".*\\d.*")) ||
                (lname != null && lname.matches(".*\\d.*"))) {
            return ResponseEntity.badRequest().body("Fname/Lname should not contain numbers.");
        }

        List<Resident> residents = residentService.findByName(fname, lname);

        if (residents.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No resident found with given input(s).");
        }
        return ResponseEntity.ok(residents);
    }

    //API to get resident by registration number
    @GetMapping("/getByRegNum/{regNum}")
    public Resident getResidentByRegNum(@PathVariable String regNum){
        return residentService.getResidentByRegistrationNumber(regNum);
    }


    // API to get Resident Name By Flat no
    @GetMapping("/getNameByFlatNo/{flatno}")
    public String getResidentName(@PathVariable String flatno){
        Resident resident = residentService.getResidentNameByFlatNo(flatno);
        return "Resident with flatNo "+ flatno;
    }

    // API to Update Resident
    @PutMapping("/updateResident")
    public ResponseEntity<Resident> updateResident(@RequestParam("fname") String fname,
                                                   @RequestBody Resident resident) {
        Resident resident1 = residentService.findByFname(fname, resident);
        return new ResponseEntity<>(resident1, HttpStatus.OK);
    }

    // API to Delete Resident by Id
    @DeleteMapping("/DeleteResident/{id}")
    public ResponseEntity<String> DeleteResidentById(@PathVariable("id") int id){
        String deleteResident = residentService.DeleteResidentById(id);
        return new ResponseEntity<>(deleteResident, HttpStatus.OK);
    }

    // Update Resident by flat No
    @PutMapping("/UpdateResidentByFlatNo")
    public ResponseEntity<Resident> updateResidentByFlatNo(@RequestParam("flatno") String flatno,@RequestBody Resident resident){
        Resident resident2 = residentService.findByFlatNo(flatno, resident);
        return new ResponseEntity<>(resident2, HttpStatus.OK);
    }

}

