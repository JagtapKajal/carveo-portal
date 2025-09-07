package carveo_portal.carveoManagement.controller;

import carveo_portal.carveoManagement.VisitorRequestDTO;
import carveo_portal.carveoManagement.VisitorWithResidentDTO;
import carveo_portal.carveoManagement.entity.Visitor;
import carveo_portal.carveoManagement.enums.VisitorType;
import carveo_portal.carveoManagement.service.VisitorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/visitors")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    // API to add/save  Visitor in DB
    @PostMapping("/addVisitor")
    public ResponseEntity<String> addVisitors(@Valid @RequestBody VisitorRequestDTO visitorRequestDTO) {
        visitorService.addVisitor(visitorRequestDTO);
        return ResponseEntity.ok("Visitor data Saved successfully");
    }

    // API to get visitor by vehicle Registration number
    @GetMapping("/getByRegNum/{RegNum}")
    public ResponseEntity<List<VisitorWithResidentDTO>> getVisitorByRegNum(@PathVariable String RegNum){
        List<VisitorWithResidentDTO> VisitorList = visitorService.getVisitorByRegNum(RegNum);
        return new ResponseEntity<>(VisitorList, HttpStatus.OK);
    }

    @PatchMapping("/updateEndTime/{vehicleRegNum}")
    public ResponseEntity<String> updateEndTime(
            @PathVariable String vehicleRegNum,
            @RequestParam("exitTime") String exitTimeStr) {

        try {
            LocalDateTime exitTime = LocalDateTime.parse(exitTimeStr.trim());

            visitorService.updateVisitorEndTime(vehicleRegNum, exitTime);

            return ResponseEntity.ok("Visitor exit time updated successfully");

        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid date-time format. Use: yyyy-MM-dd'T'HH:mm:ss");
        }
    }

    // API to get know visitor is active or not
    @GetMapping("/isactivevisitor")
    public ResponseEntity<List<Visitor>> getActiveVisitor(@RequestParam(required = false)List<VisitorType> types){
        List<Visitor> visitors = visitorService.getActiveVisitors(types);
        return ResponseEntity.ok(visitors);
    }

    //API to check visitor exit time
    @PatchMapping("/exit-time/{regNum}")
    public ResponseEntity<Visitor> updateExitTime(
            @PathVariable String regNum,
            @RequestBody Map<String, String> requestBody) {

        LocalDateTime exitTime = LocalDateTime.parse(requestBody.get("timeOut"));
        Visitor updatedVisitor = visitorService.updateVisitorEndTime(regNum, exitTime);

        return ResponseEntity.ok(updatedVisitor);
}

}
