package carveo_portal.carveoManagement.entity;

import carveo_portal.carveoManagement.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
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

}
