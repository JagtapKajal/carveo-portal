package carveo_portal.carveoManagement;

import carveo_portal.carveoManagement.enums.VisitorType;

import java.time.LocalDateTime;

public class VisitorDTO {

    private int id;
    private String visitorname;

    private String vehiclename;
    private String vehicleRegistrationNumber;

    private String visitpurpose;
    private LocalDateTime timein;

    private LocalDateTime timeout;
    private Long phonenumber;

    private VisitorType visitorType;
   private String flatno;

   private String residentname;

   private Long ResidentId;

    public String getResidentname() {
        return residentname;
    }

    public void setResidentname(String residentname) {
        this.residentname = residentname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVisitorname() {
        return visitorname;
    }

    public void setVisitorname(String visitorname) {
        this.visitorname = visitorname;
    }

    public String getVehiclename() {
        return vehiclename;
    }

    public void setVehiclename(String vehiclename) {
        this.vehiclename = vehiclename;
    }

    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }

    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    }

    public String getVisitpurpose() {
        return visitpurpose;
    }

    public void setVisitpurpose(String visitpurpose) {
        this.visitpurpose = visitpurpose;
    }

    public LocalDateTime getTimein() {
        return timein;
    }

    public void setTimein(LocalDateTime timein) {
        this.timein = timein;
    }

    public LocalDateTime getTimeout() {
        return timeout;
    }

    public void setTimeout(LocalDateTime timeout) {
        this.timeout = timeout;
    }

    public Long getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Long phonenumber) {
        this.phonenumber = phonenumber;
    }

    public VisitorType getVisitorType() {
        return visitorType;
    }

    public void setVisitorType(VisitorType visitorType) {
        this.visitorType = visitorType;
    }

    public String getFlatno() {
        return flatno;
    }

    public void setFlatno(String flatno) {
        this.flatno = flatno;
    }

    public Long getResidentId() {
        return ResidentId;
    }

    public void setResidentId(Long residentId) {
        ResidentId = residentId;
    }
}
