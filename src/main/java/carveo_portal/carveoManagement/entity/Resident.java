package carveo_portal.carveoManagement.entity;

import carveo_portal.carveoManagement.enums.ResidentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fname;

    @Column(nullable = false)
    private String lname;

    @Column(nullable = false, unique = true)
    private String flatno;

    @Column(nullable = false, unique = true)
    private long mobileno;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResidentType residenttype;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicleList = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        vehicle.setResident(this);
        this.vehicleList.add(vehicle);
    }
}
