package carveo_portal.carveoManagement.service;

import carveo_portal.carveoManagement.VisitorDTO;
import carveo_portal.carveoManagement.VisitorRequestDTO;
import carveo_portal.carveoManagement.VisitorWithResidentDTO;
import carveo_portal.carveoManagement.entity.Visitor;
import carveo_portal.carveoManagement.enums.VisitorType;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitorService {

    // To save visitors with validation
    public Visitor addVisitor(VisitorRequestDTO dto);

    // To map visitor with resident DTO
    public VisitorWithResidentDTO MappedWithDTO(Visitor visitor);

    // Method to get Resident by registration number
    public List<VisitorWithResidentDTO> getVisitorByRegNum(String regNum);

    // to check visitor time out
    public Visitor checkoutVisitor(int id, LocalDateTime timeout);

    // Method to update Visitor end TIme using PATCH
    public Visitor updateVisitorEndTime(String vehicleRegNum, LocalDateTime exitTime);

    // method to know visitor is active or not
    public List<Visitor> getActiveVisitors(List<VisitorType> types);

    //to save visitor
    public Visitor saveOrUpdateVisitor(Visitor visitor);

    public List<VisitorDTO> getAllVisitor();
}
