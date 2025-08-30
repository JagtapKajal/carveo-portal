package carveo_portal.carveoManagement.entity;

import carveo_portal.carveoManagement.enums.VehicleType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String registrationNumber;

    @Column(nullable = false)
    private String vName;

    private String color;

    // stores CAR/MOPED/BIKE as string in DB
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType vehicleType;

    @Column(nullable = false)
    private LocalDateTime associationActivatedAt;

    private LocalDateTime getAssociationDeactivatedAt;

    @Column(nullable = false)
    private boolean isVehicleActive;

    // Many vehicles can belong to one resident
    @ManyToOne
    @JoinColumn(name = "resident_id", nullable = false)
    private Resident resident;


    //---Getters and Setters---//


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public LocalDateTime getAssociationActivatedAt() {
        return associationActivatedAt;
    }

    public void setAssociationActivatedAt(LocalDateTime associationActivatedAt) {
        this.associationActivatedAt = associationActivatedAt;
    }

    public LocalDateTime getGetAssociationDeactivatedAt() {
        return getAssociationDeactivatedAt;
    }

    public void setGetAssociationDeactivatedAt(LocalDateTime getAssociationDeactivatedAt) {
        this.getAssociationDeactivatedAt = getAssociationDeactivatedAt;
    }

    public boolean isVehicleActive() {
        return isVehicleActive;
    }

    public void setVehicleActive(boolean vehicleActive) {
        isVehicleActive = vehicleActive;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }
}
