package com.example.employeezoho.dto;

import com.example.employeezoho.model.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {
    private Long id;
    private String nexturnEmpId;
    private PersonalDetails personalDetails;
    private EmergencyContact emergencyContact;
    private ProfessionalDetails professionalDetails;


    public static EmployeeDto from(Employee employee) {
        return EmployeeDto
                .builder()
                .id(employee.getId())
                .nexturnEmpId(employee.getNexturnEmpId())
                .personalDetails(employee.getPersonalDetails())
                .emergencyContact(employee.getEmergencyContact())
                .professionalDetails(employee.getProfessionalDetails())
                .build();
    }
}
