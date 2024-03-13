package com.example.employeezoho.dto;

import com.example.employeezoho.model.EmergencyContact;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EmergencyContactDto {
    private Long id;
    private String emergencyContactPersonName;
    private String emergencyContactNumber;

    public static EmergencyContactDto from(EmergencyContact emergencyContact) {
        return EmergencyContactDto
                .builder()
                .id(emergencyContact.getId())
                .emergencyContactPersonName(emergencyContact.getEmergencyContactPersonName())
                .emergencyContactNumber(emergencyContact.getEmergencyContactNumber())
                .build();
    }
}
