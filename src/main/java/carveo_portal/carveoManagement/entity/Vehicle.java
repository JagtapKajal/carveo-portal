package carveo_portal.carveoManagement.entity;

import carveo_portal.carveoManagement.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
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

    @Column(nullable = false)
   //@NotEmpty(message = "Registration number is required .....")
    private String registrationnumber;

    @Column(nullable = false)
    private String vname;

    private String color;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType type; // CAR / MOPED / BIKE

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime intime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime outtime;

    @Column(nullable = false)
    private boolean isvehicleactive;

    @ManyToOne(fetch = FetchType.LAZY)  // Vehicle belongs to one Resident
    @JoinColumn(nullable = false)// foreign key column
    @JsonBackReference
    private Resident resident;


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

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }
}
