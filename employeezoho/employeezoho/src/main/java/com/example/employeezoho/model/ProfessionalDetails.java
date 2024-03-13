package com.example.employeezoho.model;

import com.example.employeezoho.dto.ProfessionalDetailsDto;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
@Entity
@Table(name = "professionalDetails")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProfessionalDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    private String practiceUnit;
    @Column
    private String designation;
    @Column
    private LocalDate joiningDate;
    @Column
    private String workLocation;
    @Column
    private String officeEmail;
    @Column
    private String employmentType;
    @Column
    private String experience;
    public static ProfessionalDetails from(ProfessionalDetailsDto professionalDetailsDto) {
        return ProfessionalDetails
                .builder()
                .id(professionalDetailsDto.getId())
                .officeEmail(professionalDetailsDto.getOfficeEmail())
                .workLocation(professionalDetailsDto.getWorkLocation())
                .practiceUnit(professionalDetailsDto.getPracticeUnit())
                .designation(professionalDetailsDto.getDesignation())
                .joiningDate(professionalDetailsDto.getJoiningDate())
                .employmentType(professionalDetailsDto.getEmploymentType())
                .experience(professionalDetailsDto.getExperience())
                .build();
    }
}
