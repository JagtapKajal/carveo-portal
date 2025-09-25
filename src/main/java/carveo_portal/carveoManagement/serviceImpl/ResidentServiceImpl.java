package carveo_portal.carveoManagement.serviceImpl;

import carveo_portal.carveoManagement.Service.ResidentService;
import carveo_portal.carveoManagement.entity.Resident;
import carveo_portal.carveoManagement.entity.Vehicle;
import carveo_portal.carveoManagement.enums.ResidentType;
import carveo_portal.carveoManagement.exceptionHandling.InvalidRegistrationNumberException;
import carveo_portal.carveoManagement.exceptionHandling.ResourceNotFoundException;
import carveo_portal.carveoManagement.repository.ResidentRepository;
import carveo_portal.carveoManagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResidentServiceImpl implements ResidentService {

    private final ResidentRepository residentRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public ResidentServiceImpl(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    //Method to save resident details
//    @Override
//    public Resident saveResident(Resident resident) {
//        // Manual validation
//        if (resident.getFname() == null || resident.getFname().isBlank()) {
//            throw new InvalidRegistrationNumberException("Firstname is mandatory");
//        }
//        if (resident.getLname() == null || resident.getLname().isBlank()) {
//            throw new InvalidRegistrationNumberException("Lastname is mandatory");
//        }
//        if (resident.getMobileno() == 12345) {
//            throw new InvalidRegistrationNumberException("Contact number is mandatory");
//        }
//        if (resident.getFlatno() == null || resident.getFlatno().isBlank()) {
//            throw new InvalidRegistrationNumberException("Flat number is mandatory");
//        }
//        if (resident.getEmail() != null && !resident.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
//            throw new InvalidRegistrationNumberException("Email is invalid");
//        }
//        if (resident.getVehicleList() != null) {
//            resident.getVehicleList().forEach(v -> {
//                v.setResident(resident);
//                v.getIntime(LocalDateTime.now().withNano(0));
//            });
//        }
//
//        return residentRepository.save(residen    t);
//    }

    @Override
    public Resident saveResident(Resident resident) {
        return null;
    }

    //method to save list of resident
    @Override
    public List<Resident> saveAll(List<Resident> residents) {
        for (Resident resident : residents) {
            for (Vehicle v : resident.getVehicleList()) {
                v.setResident(resident);
            }
        }
        return residentRepository.saveAll(residents);
    }

    // method to get resident by Id
    @Override
    public Resident getResidentById(int id) {
        try {
            Resident resident = residentRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Resident not found with " + id));
            residentRepository.findById(id);
            return resident;
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Resident not found with " + id);

        }

    }

    // Method to get All Resident
    @Override
    public List<Resident> getAllResident() {
        return residentRepository.findAll();
    }

    //Method to find first name or last name or both
    @Override
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
    @Override
    public Resident getResidentByRegistrationNumber(String registrationNumber) {

        if (registrationNumber == null || registrationNumber.length() != 10) {
            throw new InvalidRegistrationNumberException("Invalid Registration number, Please enter 10 digit number");
        }

        Vehicle vehicle = vehicleRepository.findByRegistrationnumber(registrationNumber)
                .orElseThrow(() -> new InvalidRegistrationNumberException.VehicleNotFoundException("please enter valid number, Vehicle not found" + registrationNumber));
        return vehicle.getResident();
    }


    // Method to delete Resident by id
    @Override
    public String DeleteResidentById(int id) {
        try {
            Resident resident = residentRepository.findById(id).orElseThrow(()
                    -> new ResourceNotFoundException("Resident not found "));
            residentRepository.deleteById(id);
            return "Resident Deleted successfully with " + id;

        } catch (ResourceNotFoundException e) {
            return "Resident Not found with id " + id;
        }
    }

    // Method to update resident
    @Override
    public Resident findByFname(String fname, Resident updateResident) {

            Resident resident = residentRepository.findByFname(fname);

            if(resident == null){
            throw new ResourceNotFoundException("Resident not found with "+fname);

            }
            // update fields
            resident.setFlatno(updateResident.getFlatno());
            resident.setMobileno(updateResident.getMobileno());
            resident.setEmail(updateResident.getEmail());
            resident.setFname(updateResident.getFname());
            resident.setLname(updateResident.getLname());
            resident.setResidenttype(updateResident.getResidenttype());

            return residentRepository.save(resident);

    }

    // Update Resident by flat No
    @Override
    public Resident findByFlatNo(String flatno, Resident updateResident) {
        Resident resident = residentRepository.findByFlatno(flatno);
        if(resident == null){
            throw new ResourceNotFoundException("Resident not found with "+flatno);

        }
        // update fields
        resident.setFlatno(updateResident.getFlatno());
        resident.setMobileno(updateResident.getMobileno());
        resident.setEmail(updateResident.getEmail());
        resident.setFname(updateResident.getFname());
        resident.setLname(updateResident.getLname());
        resident.setResidenttype(updateResident.getResidenttype());

        return residentRepository.save(resident);

    }

    // Delete by flat no
    @Override
    public String DeleteByFlatNo(String flatno) {
       Resident resident =  residentRepository.findByFlatno(flatno);
       if(resident == null){
           throw new ResourceNotFoundException("Resident flat no not found with flat no "+flatno);
       }
       residentRepository.delete(resident);
        return "Resident Deleted Successfully";
    }

    // Get Resident by Flat No
    @Override
    public Resident getResidentByFlatNo(String flatno, Resident resident) {

        Resident listOfResident = residentRepository.findByFlatno(flatno);

        if (listOfResident == null){
            throw new ResourceNotFoundException("Resident not found with flatno " + flatno);
        }
            return listOfResident;
    }

    //get Resident by parkingLot
    @Override
    public Resident getResidentByParkingLot (String parkingslot, Resident resident) {
        Resident resident2 = residentRepository.findByparkingslot(parkingslot);
        if(resident == null){
            throw new ResourceNotFoundException("Resident not found with ParkingLot "+ parkingslot);
        }
        return resident2;
    }

    // Find resident by resident type
    @Override
    public List<Resident> filterByResidentType(String residenttype) {
        ResidentType residenttype1 = ResidentType.valueOf(residenttype.toUpperCase());
        return residentRepository.findByResidenttype(residenttype1);
    }
}