package com.example.employeezoho.model;

import com.example.employeezoho.dto.PersonalDetailsDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "personalDetails")
public class PersonalDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String employeeFirstName;
    @Column
    private String employeeLastName;
    @Column
    private String gender;
    @Column
    private String dob;
    @Column
    private String contactNumber;
    @Column
    private String personalEmail;
    @Column
    private String presentAddress;
    @Column
    private String permanentAddress;


    public static PersonalDetails from(PersonalDetailsDto personalDetailsDto) {
        return PersonalDetails
                .builder()
                .id(personalDetailsDto.getId())
                .employeeFirstName(personalDetailsDto.getEmployeeFirstName())
                .employeeLastName(personalDetailsDto.getEmployeeLastName())
                .gender(personalDetailsDto.getGender())
                .dob(personalDetailsDto.getDob())
                .contactNumber(personalDetailsDto.getContactNumber())
                .personalEmail(personalDetailsDto.getPersonalEmail())
                .presentAddress(personalDetailsDto.getPresentAddress())
                .permanentAddress(personalDetailsDto.getPermanentAddress())
                .build();
    }
}
