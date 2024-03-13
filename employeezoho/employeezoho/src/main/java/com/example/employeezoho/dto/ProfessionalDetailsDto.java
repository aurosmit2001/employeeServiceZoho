package com.example.employeezoho.dto;

import com.example.employeezoho.model.ProfessionalDetails;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessionalDetailsDto {
    private Long id;
    private String practiceUnit;
    private String designation;
    private LocalDate joiningDate;
    private String workLocation;
    private String officeEmail;
    private String employmentType;
    private String experience;

    public static ProfessionalDetailsDto from(ProfessionalDetails professionalDetails) {
        return ProfessionalDetailsDto
                .builder()
                .id(professionalDetails.getId())
                .officeEmail(professionalDetails.getOfficeEmail())
                .workLocation(professionalDetails.getWorkLocation())
                .practiceUnit(professionalDetails.getPracticeUnit())
                .designation(professionalDetails.getDesignation())
                .joiningDate(professionalDetails.getJoiningDate())
                .employmentType(professionalDetails.getEmploymentType())
                .experience(professionalDetails.getExperience())
                .build();
    }
}
