package carveo_portal.carveoManagement.service;

import carveo_portal.carveoManagement.entity.Resident;
import carveo_portal.carveoManagement.entity.Vehicle;
import carveo_portal.carveoManagement.exceptionHandling.InvalidRegistrationNumberException;
import carveo_portal.carveoManagement.exceptionHandling.ResourceNotFoundException;
import carveo_portal.carveoManagement.repository.ResidentRepository;
import carveo_portal.carveoManagement.repository.VehicleRepository;
import ch.qos.logback.core.CoreConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ResidentService {

    private final ResidentRepository residentRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public ResidentService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    //Method to save resident details
    public Resident saveResident(Resident resident) {
        // Manual validation
        if (resident.getFname() == null || resident.getFname().isBlank()) {
            throw new InvalidRegistrationNumberException("Firstname is mandatory");
        }
        if (resident.getLname() == null || resident.getLname().isBlank()) {
            throw new InvalidRegistrationNumberException("Lastname is mandatory");
        }
        if (resident.getMobileno() == 12345) {
            throw new InvalidRegistrationNumberException("Contact number is mandatory");
        }
        if (resident.getFlatno() == null || resident.getFlatno().isBlank()) {
            throw new InvalidRegistrationNumberException("Flat number is mandatory");
        }
        if (resident.getEmail() != null && !resident.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidRegistrationNumberException("Email is invalid");
        }
        if (resident.getVehicleList() != null) {
            resident.getVehicleList().forEach(v -> {
                v.setResident(resident);
                v.setAssociationactivatedat(LocalDateTime.now().withNano(0));
            });
        }

        return residentRepository.save(resident);
    }

    //method to save list of resident
    public List<Resident> saveAll(List<Resident> residents) {
        for (Resident resident : residents) {
            for (Vehicle v : resident.getVehicleList()) {
                v.setResident(resident);
            }
        }
        return residentRepository.saveAll(residents);
    }

    // method to get resident by Id
    public Optional<Resident> getResidentById(int id) {
        return residentRepository.findById(id);
    }

    // Method to get All Resident
    public List<Resident> getAllResident() {
        return residentRepository.findAll();
    }


    //Method to find first name or last name or both
    public List<Resident> findByName(String fname, String lname) {
        if (fname != null && !fname.isBlank() && lname != null && !lname.isBlank()) {
            return residentRepository.findByFnameIgnoreCaseAndLnameIgnoreCase(fname, lname);
        } else if (fname != null && !fname.isBlank()) {
            return residentRepository.findByFnameIgnoreCase(fname);
        } else if (lname != null && !lname.isEmpty()) {
            return residentRepository.findByLnameIgnoreCase(lname);
        }
        return List.of();
    }

    // Method to get Resident by registration number
    public Resident getResidentByRegistrationNumber(String registrationNumber) {

        if (registrationNumber == null || registrationNumber.length() != 10) {
            throw new InvalidRegistrationNumberException("Invalid Registration number, Please enter 10 digit number");
        }

        Vehicle vehicle = vehicleRepository.findByRegistrationnumber(registrationNumber)
                .orElseThrow(() -> new InvalidRegistrationNumberException.VehicleNotFoundException("please enter valid number, Vehicle not found" + registrationNumber));
        return vehicle.getResident();
    }


    // Method to get Resident name By FlatNo
    public String getResidentNameByFlatNo(String flatno) {
        return residentRepository.findByFlatno(flatno)
                .map(resident -> resident.getFname() + " " + resident.getLname())
                .orElse("Resident not found with " + flatno);
    }

    // Method to update resident
    public Resident updateResident(int id, Resident updatedResident) {
        return residentRepository.findById(id).map(resident -> {
            resident.setFname(updatedResident.getFname());
            resident.setLname(updatedResident.getLname());
            resident.setFlatno(updatedResident.getFlatno());
            resident.setMobileno(updatedResident.getMobileno());
            resident.setEmail(updatedResident.getEmail());
            resident.setResidenttype(updatedResident.getResidenttype());
            return residentRepository.save(resident);
        }).orElseThrow(() -> new RuntimeException("Resident not found with id: " + id));
    }

    // Method to delete Resident by id
    public String DeleteResidentById(int id) {
        try {
            Resident resident = residentRepository.findById(id).orElseThrow(()
                    -> new ResourceNotFoundException("Resident not found "));
            residentRepository.deleteById(id);
            return "Resident Deleted with " + id;
        } catch (ResourceNotFoundException e) {
            return "Resident Not found with id " + id;
        }
    }
}
