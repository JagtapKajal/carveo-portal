package carveo_portal.carveoManagement.controller;

import carveo_portal.carveoManagement.VisitorRequestDTO;
import carveo_portal.carveoManagement.entity.Visitor;
import carveo_portal.carveoManagement.service.VisitorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitors")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @PostMapping("/addVisitor")
    public ResponseEntity<String> addVisitors(@Valid @RequestBody VisitorRequestDTO visitorRequestDTO) {
        visitorService.addVisitor(visitorRequestDTO);
        return ResponseEntity.ok("Visitor data Saved successfully");
    }

//    public ResponseEntity<List<VisitorRequestDTO>> addListOfVisitor(){
//
//    }


}
