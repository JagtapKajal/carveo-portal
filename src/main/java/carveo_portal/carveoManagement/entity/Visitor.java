package carveo_portal.carveoManagement.entity;

import carveo_portal.carveoManagement.enums.VisitorType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String visitorname;

    private String vehiclename;

    private String vehicleRegistrationNumber;

    private String visitpurpose;

    private LocalDateTime timein;

    private LocalDateTime timeout;

    private Long phonenumber;

    @Column(name = "isactivevisitor", nullable = false)
    private boolean isactivevisitor;

    @Enumerated(EnumType.STRING)
    private VisitorType visitorType; //Guest / Delivery

    @ManyToOne
    @JoinColumn(name = "resident_id", nullable = true)
    private Resident resident;

    @Column(name = "visitor_duration")
    private String visitorduration;

    @PreUpdate
    public void calculateDuration() {
        if (timein != null && timeout != null) {
            Duration duration = Duration.between(timein, timeout);
            long totalMinutes = duration.toMinutes();
            long hours = totalMinutes / 60;
            long minutes = totalMinutes % 60;
            this.visitorduration = String.format("%02d:%02d", hours, minutes);
        } else {
            this.visitorduration = "00:00"; // safe fallback
        }
    }
    public String getVisitorduration() {
        return visitorduration;
    }

    public void setVisitorduration(String visitorduration) {
        this.visitorduration = visitorduration;
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

    public boolean isIsactivevisitor() {
        return isactivevisitor;
    }

    public void setIsactivevisitor(boolean isactivevisitor) {
        this.isactivevisitor = isactivevisitor;
    }

    public VisitorType getVisitorType() {
        return visitorType;
    }

    public void setVisitorType(VisitorType visitorType) {
        this.visitorType = visitorType;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }
}
