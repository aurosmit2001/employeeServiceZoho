//package com.example.employeezoho.service;
//
//import com.example.employeezoho.dto.PersonalDetailsDto;
//import com.example.employeezoho.model.PersonalDetails;
//import com.example.employeezoho.repository.PersonalDetailsRepository;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.Optional;
//
//@Service
//public class PersonalDetailsService {
//    @Autowired
//    PersonalDetailsRepository personalDetailsRepository;
//    public void fetchingpersonaldetails(String personaldetails) {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode responseNode = objectMapper.readTree(personaldetails);
//            JsonNode resultNode = responseNode.path("response").path("result");
//            if (resultNode.isArray() && resultNode.size() > 0) {
//                for (JsonNode recordNode : resultNode) {
//                    JsonNode recordArrayNode = recordNode.elements().next(); // Assuming only one record array
//                    for (JsonNode record : recordArrayNode) {
//
//                        String firstname = record.get("FirstName").asText();
//                        String lastname = record.get("LastName").asText();
//                        String gender = record.get("Gender").asText();
//                        String dob = record.get("Date_of_birth").asText();
//                        String contactnumber= record.get("Mobile").asText();
//                        String personalemail = record.get("Personal_email_id").asText();
//                        String presentaddress = record.get("Present_Address").asText();
//                        String permanentaddress = record.get("Permanent_Address").asText();
//
//
//                        PersonalDetailsDto personalDetailsDto=new PersonalDetailsDto();
//                        personalDetailsDto.setEmployeeFirstName(firstname);
//                        personalDetailsDto.setEmployeeLastName(lastname);
//                        personalDetailsDto.setGender(gender);
//                        personalDetailsDto.setDob(dob);
//                        personalDetailsDto.setContactNumber(contactnumber);
//                        personalDetailsDto.setPersonalEmail(personalemail);
//                        personalDetailsDto.setPresentAddress(presentaddress);
//                        personalDetailsDto.setPermanentAddress(permanentaddress);
//                        this.personalDetailsRepository.save(PersonalDetails.from(personalDetailsDto));
//
//                    }
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Error occurred while parsing employeedata: " + e.getMessage());
//        }
//    }
//
//}
//
