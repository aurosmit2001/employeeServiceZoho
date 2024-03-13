//package com.example.employeezoho.service;
//
//import com.example.employeezoho.dto.EmergencyContactDto;
//import com.example.employeezoho.dto.ProfessionalDetailsDto;
//import com.example.employeezoho.model.EmergencyContact;
//import com.example.employeezoho.model.PersonalDetails;
//import com.example.employeezoho.model.ProfessionalDetails;
//import com.example.employeezoho.repository.PersonalDetailsRepository;
//import com.example.employeezoho.repository.ProfessionalDetailsRepository;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;
//import java.util.Date;
//import java.util.Locale;
//
//@Service
//public class ProfessionalDetailsService {
//    @Autowired
//    ProfessionalDetailsRepository professionalDetailsRepository;
//    public void fetchingprofesstionaldetails(String professtionaldetails){
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode responseNode = objectMapper.readTree(professtionaldetails);
//            JsonNode resultNode = responseNode.path("response").path("result");
//            if (resultNode.isArray() && resultNode.size() > 0) {
//                for (JsonNode recordNode : resultNode) {
//                    JsonNode recordArrayNode = recordNode.elements().next(); // Assuming only one record array
//                    for (JsonNode record : recordArrayNode) {
//                        String practiseunit = record.get("Department").asText();
//                        String designation = record.get("Designation").asText();
//                        String joiningdate = record.get("Dateofjoining").asText();
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
//                        String worklocation = record.get("Work_location").asText();
//                        String officeemail = record.get("EmailID").asText();
//                        String employmenttype = record.get("Employee_type").asText();
//                        String experience = record.get("total_experience.displayValue").asText();
//                        LocalDate joiningDate = null;
//                        try {
//                            joiningDate = LocalDate.parse(joiningdate, formatter);
//                        } catch (DateTimeParseException e) {
//                            e.printStackTrace();
//                        }
//
//                        ProfessionalDetailsDto professionalDetailsDto=new ProfessionalDetailsDto();
//                        professionalDetailsDto.setPracticeUnit(practiseunit);
//                        professionalDetailsDto.setDesignation(designation);
//                        professionalDetailsDto.setJoiningDate(joiningDate);
//                        professionalDetailsDto.setWorkLocation(worklocation);
//                        professionalDetailsDto.setOfficeEmail(officeemail);
//                        professionalDetailsDto.setEmploymentType(employmenttype);
//                        professionalDetailsDto.setExperience(experience);
//
//                        this.professionalDetailsRepository.save(ProfessionalDetails.from(professionalDetailsDto));
//
//                    }
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Error occurred while parsing employeedata: " + e.getMessage());
//        }
//    }
//}
