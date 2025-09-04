package carveo_portal.carveoManagement.service;

import carveo_portal.carveoManagement.VisitorRequestDTO;
import carveo_portal.carveoManagement.VisitorWithResidentDTO;
import carveo_portal.carveoManagement.entity.Resident;
import carveo_portal.carveoManagement.entity.Visitor;
import carveo_portal.carveoManagement.repository.ResidentRepository;
import carveo_portal.carveoManagement.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private  ResidentRepository residentRepository;

    // To save visitors with validation
    public Visitor addVisitor(VisitorRequestDTO dto){

        //set visitor entity with DTO
        Resident resident = residentRepository.findById(dto.getResidentId())
                .orElseThrow(()->new RuntimeException("Resident not found"));

        Visitor visitor = new Visitor();
        visitor.setVisitorname(dto.getVisitorname());
        visitor.setVehiclename(dto.getVehiclename());
        visitor.setVisitorType(dto.getVisitorType());
        visitor.setIsactivevisitor(dto.isActiveVisitor());
        visitor.setPhonenumber(dto.getPhonenumber());
        visitor.setTimein(dto.getTimein());
        visitor.setTimeout(dto.getTimeout());
        visitor.setVehicleRegistrationNumber(dto.getVehicleRegistrationNumber());
        visitor.setVisitpurpose(dto.getVisitpurpose());
        visitor.setResident(resident);

        return visitorRepository.save(visitor);
    }

    // To map visitor with resident DTO



    public List<VisitorWithResidentDTO>  getVisitorByRegNum(String regNum){
        List<Visitor> visitor = visitorRepository.findByVehicleRegistrationNumber(regNum);
        return visitor.stream()
                .map(this::mapToDto)   // method jo entity ko DTO me convert karega
                .collect(Collectors.toList());

    }
}
