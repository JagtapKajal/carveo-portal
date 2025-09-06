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
    private VisitorWithResidentDTO MappedWithDTO(Visitor visitor){
        return new VisitorWithResidentDTO(
                visitor.getVisitorname(),
                visitor.getVehiclename(),
                visitor.getVehicleRegistrationNumber(),
                visitor.getVisitpurpose(),
                visitor.getTimein(),
                visitor.getTimeout(),
                visitor.getPhonenumber(),
                visitor.getVisitorType(),
                visitor.getResident() != null ? visitor.getResident().getFname():null,
                visitor.getResident() != null ? visitor.getResident().getFlatno(): null
        );
    }

    // Method to get Resident by registration number
    public List<VisitorWithResidentDTO> getVisitorByRegNum(String regNum){
        List<Visitor> visitors = visitorRepository.findByVehicleRegistrationNumber(regNum);

        if(visitors.isEmpty()){
            throw  new RuntimeException("Visitor not Found with Registration Number " + regNum);
        }
        return visitors.stream().map(this::MappedWithDTO).toList();
    }

    //
}
