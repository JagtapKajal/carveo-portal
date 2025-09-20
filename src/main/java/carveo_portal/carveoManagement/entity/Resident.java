package carveo_portal.carveoManagement.entity;

import carveo_portal.carveoManagement.enums.ResidentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
// This annotation use for T19 because its need to add on resident details they don't need vehicle details
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Firstname is required")
    //defines a search pattern for text.
    @Pattern(regexp = "^[A-Za-z]+$", message = "Invalid first name, spaces and special numbers are not allowed")
    private String fname;


    @Pattern(regexp = "^[A-Za-z]+$", message = "Invalid last name, spaces and special numbers are not allowed")
    private String lname;


    @Pattern(regexp = "^[A-Za-z0-9-]+$", message = "Invalid flat number")
    private String flatno;


    @Digits(integer = 10, fraction = 0, message = "Mobile number must be exactly 10 digits")
    private long mobileno;

   @NotEmpty(message = "Email is Required")
   @Email(message = "Invalid email format")
    private String email;

   // Enum of Resident type
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResidentType residenttype;

    //Mapping of vehicle
    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Vehicle> vehicleList = new ArrayList<>();

    // Mapping of Visitor
    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Visitor> visitorList = new ArrayList<>();



    public void addVehicle(Vehicle vehicle) {
        vehicle.setResident(this);
        this.vehicleList.add(vehicle);
    }

    public long getMobileno() {
        return mobileno;
    }

    public void setMobileno(long mobileno) {
        this.mobileno = mobileno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname)  {
        this.fname = fname.trim();
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname.trim();
    }


    public String getFlatno() {
        return flatno;
    }

    public void setFlatno(String flatno) {
        this.flatno = flatno.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public ResidentType getResidenttype() {
        return residenttype;
    }

    public void setResidenttype(ResidentType residenttype) {
        this.residenttype = residenttype;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
}
