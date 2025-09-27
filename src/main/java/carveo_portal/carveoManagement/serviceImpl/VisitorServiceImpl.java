package carveo_portal.carveoManagement.serviceImpl;

import carveo_portal.carveoManagement.Service.VisitorService;
import carveo_portal.carveoManagement.VisitorRequestDTO;
import carveo_portal.carveoManagement.VisitorWithResidentDTO;
import carveo_portal.carveoManagement.entity.Resident;
import carveo_portal.carveoManagement.entity.Visitor;
import carveo_portal.carveoManagement.enums.VisitorType;
import carveo_portal.carveoManagement.repository.ResidentRepository;
import carveo_portal.carveoManagement.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private  ResidentRepository residentRepository;

    // To save visitors with validation
    @Override
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
    @Override
    public VisitorWithResidentDTO MappedWithDTO(Visitor visitor){
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
    @Override
    public List<VisitorWithResidentDTO> getVisitorByRegNum(String regNum){
        List<Visitor> visitors = visitorRepository.findByVehicleRegistrationNumber(regNum);

        if(visitors.isEmpty()){
            throw  new RuntimeException("Visitor not Found with Registration Number " + regNum);
        }
        return visitors.stream().map(this::MappedWithDTO).toList();
    }


    // to check visitor time out
    @Override
    public Visitor checkoutVisitor(int id, LocalDateTime timeout) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visitor not found"+id));

        visitor.setTimeout(timeout);
        visitor.setIsactivevisitor(false);

        // also calculate visit Duration
        if (visitor.getTimein() != null && timeout != null) {
            Duration duration = Duration.between(visitor.getTimein(), timeout);
            long hours = duration.toHours();
            long minutes = duration.toMinutesPart();
            visitor.setVisitorduration(String.format("%02d:%02d", hours, minutes));
        }

        return visitorRepository.save(visitor);
    }

    // Method to update Visitor end TIme using PATCH
    @Override
    public Visitor updateVisitorEndTime(String vehicleRegNum, LocalDateTime exitTime) {
        // 1. Fetch the latest visitor record for this vehicle
        Visitor visitor = visitorRepository
                .findTopByVehicleRegistrationNumberOrderByTimeinDesc(vehicleRegNum)
                .orElseThrow(() -> new RuntimeException(
                        "Visitor not found with vehicle number: " + vehicleRegNum));

        // 2. Set exit time and mark visitor as inactive
        visitor.setTimeout(exitTime);
        visitor.setIsactivevisitor(exitTime == null); // false if timeout is set


        // 3. Always calculate duration (method itself checks nulls)
        if (visitor.getTimein() != null && exitTime != null) {
            Duration duration = Duration.between(visitor.getTimein(), exitTime);
            long hours = duration.toHours();
            long minutes = duration.toMinutesPart();
            String formatted = String.format("%02d:%02d", hours, minutes);
            visitor.setVisitorduration(formatted);

            // mark visitor inactive
            visitor.setIsactivevisitor(false);
        }
        return visitorRepository.save(visitor);
}

    // method to know visitor is active or not
    @Override
    public List<Visitor> getActiveVisitors(List<VisitorType> types){
        if(types == null || types.isEmpty()){
            return visitorRepository.findByIsactivevisitorTrue();
        }
        else{
            return visitorRepository.findByVisitorTypeInAndIsactivevisitorTrue(types);
        }
    }

    @Override
    public Visitor saveOrUpdateVisitor(Visitor visitor) {
        // Set resident if residentId is provided
        if (visitor.getResidentId() != null) {
            Resident resident = residentRepository.findById(Math.toIntExact(visitor.getResidentId()))
                    .orElseThrow(() -> new RuntimeException(
                            "Resident not found with id: " + visitor.getResidentId()));
            visitor.setResident(resident);
        }

        // Calculate duration if timeout is provided
        if (visitor.getTimein() != null && visitor.getTimeout() != null) {
            Duration duration = Duration.between(visitor.getTimein(), visitor.getTimeout());
            visitor.setVisitorduration(String.format("%02d:%02d", duration.toHours(), duration.toMinutesPart()));
            visitor.setIsactivevisitor(false);
        }

        return visitorRepository.save(visitor);

    }


}
