package carveo_portal.carveoManagement.dto;

import carveo_portal.carveoManagement.enums.VehicleType;

import java.time.LocalDateTime;

public class VehicleDTO {

        private Long id;
        private String registrationnumber;
        private String vname;
        private String color;
        private VehicleType type;
        private LocalDateTime intime;
        private LocalDateTime outtime;
        private boolean isvehicleactive;

        private Long residentId;      // For reference
        private String residentName;  // New field
        private String flatno;        // Optional, if you want to show flat number

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationnumber() {
        return registrationnumber;
    }

    public void setRegistrationnumber(String registrationnumber) {
        this.registrationnumber = registrationnumber;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public LocalDateTime getIntime() {
        return intime;
    }

    public void setIntime(LocalDateTime intime) {
        this.intime = intime;
    }

    public LocalDateTime getOuttime() {
        return outtime;
    }

    public void setOuttime(LocalDateTime outtime) {
        this.outtime = outtime;
    }

    public boolean isIsvehicleactive() {
        return isvehicleactive;
    }

    public void setIsvehicleactive(boolean isvehicleactive) {
        this.isvehicleactive = isvehicleactive;
    }

    public Long getResidentId() {
        return residentId;
    }

    public void setResidentId(Long residentId) {
        this.residentId = residentId;
    }

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public String getFlatno() {
        return flatno;
    }

    public void setFlatno(String flatno) {
        this.flatno = flatno;
    }
}
