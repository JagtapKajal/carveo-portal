package carveo_portal.carveoManagement.controller;

import carveo_portal.carveoManagement.VisitorRequestDTO;
import carveo_portal.carveoManagement.VisitorWithResidentDTO;
import carveo_portal.carveoManagement.entity.Visitor;
import carveo_portal.carveoManagement.service.VisitorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
