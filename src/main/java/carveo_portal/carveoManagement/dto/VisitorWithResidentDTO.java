package carveo_portal.carveoManagement.dto;

import carveo_portal.carveoManagement.enums.VisitorType;

import java.time.LocalDateTime;

public record VisitorWithResidentDTO (

        String visitorname,
        String vehiclename,
        String vehicleRegistrationNumber,
        String visitpurpose,
        LocalDateTime timein,
        LocalDateTime timeout,
        Long phonenumber,
        VisitorType visitorType,
        String fname,
        String flatno
){}
