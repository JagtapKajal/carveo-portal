package carveo_portal.carveoManagement.Service;

import carveo_portal.carveoManagement.entity.Resident;

import java.util.List;

public interface ResidentService {

    //Method to save resident details
    public Resident saveResident(Resident resident);

    //method to save list of resident
    public List<Resident> saveAll(List<Resident> residents);

    // method to get resident by Id
    public Resident getResidentById(int id);

    // Method to get All Resident
    public List<Resident> getAllResident();

    //Method to find first name or last name or both
    public List<Resident> findByName(String fname, String lname);

    // Method to get Resident by registration number
    public Resident getResidentByRegistrationNumber(String registrationNumber);

    // Method to get Resident name By FlatNo
    public String getResidentNameByFlatNo(String flatno);

    // Method to update resident
    public Resident updateResident(int id, Resident updatedResident);

    // Method to delete Resident by id
    public String DeleteResidentById(int id);





}
