package carveo_portal.carveoManagement;

import carveo_portal.carveoManagement.enums.VisitorType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VisitorRequestDTO {

    @NotBlank(message = "name should not be blank")
    private String visitorname;

    private String vehiclename;

    @NotBlank(message = "Registration number should not be blank")
    @NotNull(message = "Registration number should be 10 digits")
    private String vehicleRegistrationNumber;

    @NotNull(message = "Visitor purpose is mandatory")
    private String visitpurpose;

    @NotNull(message = "Time is required")
    private LocalDateTime timein;

    private LocalDateTime timeout;

    private boolean isActiveVisitor = true;


    @NotNull(message = "Phone number is mandatory")
    private Long phonenumber;

    @NotNull(message = "Visitor type is mandatory")
   private VisitorType visitorType;

    @NotNull(message = "Resident Id is required")
   private int residentId;

    public int getResidentId() {
        return residentId;
    }

    public void setResidentId(int residentId) {
        this.residentId = residentId;
    }
}
