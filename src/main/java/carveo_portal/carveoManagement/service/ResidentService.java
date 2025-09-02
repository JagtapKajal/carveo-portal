package carveo_portal.carveoManagement.service;

import carveo_portal.carveoManagement.entity.Resident;
import carveo_portal.carveoManagement.entity.Vehicle;
import carveo_portal.carveoManagement.exceptionHandling.InvalidRegistrationNumberException;
import carveo_portal.carveoManagement.repository.ResidentRepository;
import carveo_portal.carveoManagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResidentService {

    private final ResidentRepository residentRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public ResidentService(ResidentRepository residentRepository){
        this.residentRepository = residentRepository;
    }

    public Resident saveResident(Resident resident){
        return residentRepository.save(resident);
    }

    public List<Resident> saveAll(List<Resident> residents) {
        for (Resident resident : residents) {
            for (Vehicle v : resident.getVehicleList()) {
                v.setResident(resident);
            }
        }
        return residentRepository.saveAll(residents);
    }

    public Optional<Resident> getResidentById(int id){
        return residentRepository.findById(id);
    }

    public List<Resident> getAllResident(){
        return residentRepository.findAll();
    }

    public Resident getResidentByFnameAndLname(String fname, String lname) {
        return residentRepository.findByFnameAndLname(fname, lname);
    }

    public Resident getResidentByRegistrationNumber(String registrationNumber){

        if(registrationNumber == null || registrationNumber.length() != 10){
            throw new InvalidRegistrationNumberException("Invalid Registration number, Please enter 10 digit number");
        }

        Vehicle vehicle = vehicleRepository.findByRegistrationnumber(registrationNumber).orElseThrow(()
                ->new InvalidRegistrationNumberException.VehicleNotFoundException("please enter valid number, Vehicle not found" + registrationNumber));
        return vehicle.getResident();
    }

}
