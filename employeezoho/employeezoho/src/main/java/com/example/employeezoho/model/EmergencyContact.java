package com.example.employeezoho.model;

import com.example.employeezoho.dto.EmergencyContactDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "emergencyDetails")
@Entity
public class EmergencyContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    private String emergencyContactPersonName;
    @Column
    private String emergencyContactNumber;



    public static EmergencyContact from(EmergencyContactDto emergencyContactDto) {
        return EmergencyContact
                .builder()
                .id(emergencyContactDto.getId())
                .emergencyContactPersonName(emergencyContactDto.getEmergencyContactPersonName())
                .emergencyContactNumber(emergencyContactDto.getEmergencyContactNumber())
                .build();
    }

}
