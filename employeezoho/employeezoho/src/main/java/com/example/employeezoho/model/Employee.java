package com.example.employeezoho.model;

import com.example.employeezoho.dto.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    private String nexturnEmpId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personalDetailsid",referencedColumnName = "id")
    private PersonalDetails personalDetails;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emergencyContactpersonid",referencedColumnName = "id")
    private  EmergencyContact emergencyContact;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "professtionalDetailsid",referencedColumnName = "id")
    private ProfessionalDetails professionalDetails;


    public static Employee from(EmployeeDto employeeDto) {
        return Employee
                .builder()
                .nexturnEmpId(employeeDto.getNexturnEmpId())
                .personalDetails(employeeDto.getPersonalDetails())
                .professionalDetails(employeeDto.getProfessionalDetails())
                .emergencyContact(employeeDto.getEmergencyContact())
                .build();
    }

}
