package carveo_portal.carveoManagement.entity;

import carveo_portal.carveoManagement.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
   @NotEmpty(message = "Registration number is required .....")
    private String registrationnumber;

    @Column(nullable = false)
    private String vname;

    private String color;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType type; // CAR / MOPED / BIKE

    private LocalDateTime associationactivatedat;

    private LocalDateTime associationdeactivatedat;

    @Column(nullable = false)
    private boolean isvehicleactive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resident_id", nullable = false)
    @JsonBackReference
    private Resident resident;

    public String getRegistrationNumber() {
        return registrationnumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationnumber = registrationNumber;
    }
}