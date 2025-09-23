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


    // Method to delete Resident by id
    public String DeleteResidentById(int id);


    // Method to update resident
    Resident findByFname(String fname, Resident updateResident);

    // Update Resident by flat No
    Resident findByFlatNo(String flatno,Resident updateResident);

    // Delete resident by flat no
    public String DeleteByFlatNo(String flatno);

    //get resident by flatno
    public Resident getResidentByFlatNo(String flatno, Resident resident);

    public Resident getResidentByParkingLot(String parkingslot, Resident resident);

    List<Resident> filterByResidentType(String residenttype);
}
