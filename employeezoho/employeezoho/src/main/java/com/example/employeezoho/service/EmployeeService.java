package com.example.employeezoho.service;

import com.example.employeezoho.dto.EmployeeDto;
import com.example.employeezoho.model.EmergencyContact;
import com.example.employeezoho.model.Employee;
import com.example.employeezoho.model.PersonalDetails;
import com.example.employeezoho.model.ProfessionalDetails;
import com.example.employeezoho.repository.EmergencyContactRepository;
import com.example.employeezoho.repository.EmployeeRepository;
import com.example.employeezoho.repository.PersonalDetailsRepository;
import com.example.employeezoho.repository.ProfessionalDetailsRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PersonalDetailsRepository personalDetailsRepository;

    @Autowired
    ProfessionalDetailsRepository professionalDetailsRepository;

    @Autowired
    EmergencyContactRepository emergencyContactRepository;

    @Transactional
    public void fetchingemployeedetails(String employeedetails) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseNode = objectMapper.readTree(employeedetails);
            JsonNode resultNode = responseNode.path("response").path("result");
            if (resultNode.isArray() && resultNode.size() > 0) {
                for (JsonNode recordNode : resultNode) {
                    JsonNode recordArrayNode = recordNode.elements().next(); // Assuming only one record array
                    for (JsonNode record : recordArrayNode) {
                        // Parse employee details from JSON
                        String nexturnEmpId = record.get("EmployeeID").asText();
                        // Assuming other fields are present in the JSON response and are retrievable in a similar manner

                        // Parse and save PersonalDetails
                        PersonalDetails personalDetails = parseAndSavePersonalDetails(record);
                        // Parse and save ProfessionalDetails
                        ProfessionalDetails professionalDetails = parseAndSaveProfessionalDetails(record);

                        EmergencyContact emergencyContact = parseAndSaveEmergencyContact(record);

                        // Convert DTO to entity and set PersonalDetails and ProfessionalDetails
                        EmployeeDto employeeDto = new EmployeeDto();
                        employeeDto.setNexturnEmpId(nexturnEmpId);
                        // Set other fields of the employee DTO
                        Employee employee = Employee.from(employeeDto);
                        employee.setPersonalDetails(personalDetails);
                        employee.setProfessionalDetails(professionalDetails);

                        // Save the employee
                        employeeRepository.save(employee);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred while parsing employeedata: " + e.getMessage());
        }
    }

    private PersonalDetails parseAndSavePersonalDetails(JsonNode record) {
        PersonalDetails personalDetails = new PersonalDetails();
        personalDetails.setEmployeeFirstName(record.get("FirstName").asText());
        personalDetails.setEmployeeLastName(record.get("LastName").asText());
        personalDetails.setGender(record.get("Gender").asText());
        personalDetails.setDob(record.get("Date_of_birth").asText());
        personalDetails.setContactNumber(record.get("Mobile").asText());

        personalDetails.setPersonalEmail(record.get("Personal_email_id").asText());
        personalDetails.setPresentAddress(record.get("Present_Address").asText());
        personalDetails.setPermanentAddress(record.get("Permanent_Address").asText());



        // Set other fields of PersonalDetails
        return personalDetailsRepository.save(personalDetails);
    }

    private ProfessionalDetails parseAndSaveProfessionalDetails(JsonNode record) {
        ProfessionalDetails professionalDetails = new ProfessionalDetails();
        professionalDetails.setPracticeUnit(record.get("Department").asText());
        professionalDetails.setDesignation(record.get("Designation").asText());
        professionalDetails.setExperience(record.get("total_experience.displayValue").asText());
        professionalDetails.setEmploymentType(record.get("Employee_type").asText());

        // Correctly parse the joining date
        String joiningDateStr = record.get("Dateofjoining").asText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
        try {
            LocalDate joiningDate = LocalDate.parse(joiningDateStr, formatter);
            professionalDetails.setJoiningDate(joiningDate);
        } catch (DateTimeParseException e) {
            // Handle parsing exception
            System.out.println("Error parsing joining date: " + e.getMessage());
        }

        professionalDetails.setOfficeEmail(record.get("EmailID").asText());
        professionalDetails.setWorkLocation(record.get("Work_location").asText());

        // Set other fields of ProfessionalDetails
        return professionalDetailsRepository.save(professionalDetails);
    }
    private EmergencyContact parseAndSaveEmergencyContact(JsonNode record) {
        EmergencyContact emergencyContact = new EmergencyContact();
        emergencyContact.setEmergencyContactNumber(record.get("Emergency_Mobile_No").asText());
        emergencyContact.setEmergencyContactPersonName(record.get("Emergency_Contact_Person_name").asText());




        // Set other fields of PersonalDetails
        return emergencyContactRepository.save(emergencyContact);

    }






    }

