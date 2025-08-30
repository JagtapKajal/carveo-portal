package carveo_portal.carveoManagement.entity;

import carveo_portal.carveoManagement.enums.ResidentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "residents")
@Getter
@Setter
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false)
    private String fName;


    @Column(nullable = false)
    private String lName;

    @Column(nullable = false, unique = true)
    private String flatNo;

    @Column(nullable = false, unique = true)
    private long mobileNo;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResidentType residentType; // TENANT or OWNER


    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicleList = new ArrayList<>();

    //--- Getters and Setters---//

}
