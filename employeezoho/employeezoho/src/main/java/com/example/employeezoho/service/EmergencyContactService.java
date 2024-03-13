//package com.example.employeezoho.service;
//
//import com.example.employeezoho.dto.EmergencyContactDto;
//import com.example.employeezoho.dto.PersonalDetailsDto;
//import com.example.employeezoho.model.EmergencyContact;
//import com.example.employeezoho.model.PersonalDetails;
//import com.example.employeezoho.repository.EmergencyContactRepository;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.SortedSet;
//
//@Service
//public class EmergencyContactService {
//    @Autowired
//    EmergencyContactRepository emergencyContactRepository;
//public void fetchingemergencycontact(String emergencycontact){
//    try {
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode responseNode = objectMapper.readTree(emergencycontact);
//        JsonNode resultNode = responseNode.path("response").path("result");
//        if (resultNode.isArray() && resultNode.size() > 0) {
//            for (JsonNode recordNode : resultNode) {
//                JsonNode recordArrayNode = recordNode.elements().next(); // Assuming only one record array
//                for (JsonNode record : recordArrayNode) {
//                    String emergencycontactpersonname = record.get("Emergency_Contact_Person_name").asText();
//                    String emergencycontactnumber = record.get("Emergency_Mobile_No").asText();
//                    EmergencyContactDto emergencyContactDto=new EmergencyContactDto();
//                    emergencyContactDto.setEmergencyContactPersonName(emergencycontactpersonname);
//                    emergencyContactDto.setEmergencyContactNumber(emergencycontactnumber);
//
//                    this.emergencyContactRepository.save(EmergencyContact.from(emergencyContactDto));
//
//                }
//            }
//        }
//    } catch (IOException e) {
//        System.out.println("Error occurred while parsing employeedata: " + e.getMessage());
//    }
//}
//}
//
