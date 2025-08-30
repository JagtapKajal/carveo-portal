package carveo_portal.carveoManagement.service;

import carveo_portal.carveoManagement.entity.Resident;
import carveo_portal.carveoManagement.repository.ResidentRepository;
import org.springframework.stereotype.Service;

@Service
public class ResidentService {

    private final ResidentRepository residentRepository;

    public ResidentService(ResidentRepository residentRepository){
        this.residentRepository = residentRepository;
    }

    public Resident addResident(Resident resident){
        return residentRepository.save(resident);
    }
}
