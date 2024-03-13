package com.example.employeezoho.dto;


import com.example.employeezoho.model.PersonalDetails;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalDetailsDto{


    private Long id;
    private String employeeFirstName;
    private String employeeLastName;
    private String gender;
    private String dob;
    private String contactNumber;
    private String personalEmail;
    private String presentAddress;
    private String permanentAddress;

    public static PersonalDetailsDto from(PersonalDetails personalDetails) {
        return PersonalDetailsDto
                .builder()
                .id(personalDetails.getId())
                .employeeFirstName(personalDetails.getEmployeeFirstName())
                .employeeLastName(personalDetails.getEmployeeLastName())
                .gender(personalDetails.getGender())
                .dob(personalDetails.getDob())
                .contactNumber(personalDetails.getContactNumber())
                .personalEmail(personalDetails.getPersonalEmail())
                .presentAddress(personalDetails.getPresentAddress())
                .permanentAddress(personalDetails.getPermanentAddress())
                .build();
    }
}
