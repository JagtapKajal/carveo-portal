package carveo_portal.carveoManagement.controller;

import carveo_portal.carveoManagement.service.ResidentService;
import carveo_portal.carveoManagement.entity.Resident;

import carveo_portal.carveoManagement.enums.ResidentType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5174")
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

    // Delete Resident by FlatNo
    @DeleteMapping("/DeleteByFlatNo/{flatno}")
    public ResponseEntity<String> DeleteResidentByFlatNo(@PathVariable("flatno") String flatno){
        String deleteResident = residentService.DeleteByFlatNo(flatno);
        return new ResponseEntity<>(deleteResident,HttpStatus.OK);
    }

    //get resindet by flat no
    @GetMapping("/getResidentByFlatNo/{flatno}")
    public ResponseEntity<Resident> getResidentByFlatNo(@PathVariable("flatno") String flatno, Resident resident){
        Resident resident2 = residentService.getResidentByFlatNo(flatno, resident);
        return new ResponseEntity<>(resident2, HttpStatus.OK);
    }

    //get resident by parking lot
    @GetMapping("/getResidentByParkingLot/{parkingslot}")
    public ResponseEntity<Resident> getResidentByParkingLot(@PathVariable("parkingslot") String parkingslot, Resident resident){
        Resident resident3 = residentService.getResidentByParkingLot(parkingslot, resident);
        return new ResponseEntity<>(resident3,HttpStatus.OK);
    }

    // API to get resident by its type
    @GetMapping("/filterByResidentType")
    public ResponseEntity<List<Resident>> filterByResidentType(@RequestParam("residentType") String residenttype){
        List<Resident> residentList = residentService.filterByResidentType(residenttype);
        return new ResponseEntity<>(residentList, HttpStatus.OK);
    }


    //Update resident by id
    @PutMapping("/UpdateResident/{id}")
    public ResponseEntity<Resident> UpdateResidentById(@PathVariable("id") int id, @RequestBody Resident resident){
        Resident resident2 = residentService.UpdateResidentById(id, resident);
        return new ResponseEntity<>(resident2, HttpStatus.OK);
    }
}

