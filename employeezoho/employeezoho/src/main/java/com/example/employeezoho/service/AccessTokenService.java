package com.example.employeezoho.service;

import com.example.employeezoho.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

@Service
public class AccessTokenService {
    @Autowired
    RestTemplate restTemplate;
//    @Autowired
//    EmergencyContactService emergencyContactService;
//    @Autowired
//    PersonalDetailsService personalDetailsService;
//    @Autowired
//    ProfessionalDetailsService professionalDetailsService;
    @Autowired
    EmployeeService employeeService;

    public String accessToken;

    @Scheduled(fixedRate = 300000)
    public void getaccesstoken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://accounts.zoho.in/oauth/v2/token")
                .queryParam("grant_type", "refresh_token")
                .queryParam("refresh_token", "1000.9f0631211be7e36484c7ce936d11b603.5442ed0568045a28bd589d5790e3a0e5")
                .queryParam("client_id", "1000.C1X3VO57RR8Q9J8D55WBW8CYX4ESAT")
                .queryParam("client_secret", "a45c522e50cc5bfa953ad54ce6d22f147eb5df4740");
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            accessToken = responseEntity.getBody();
            System.out.println("Access token refreshed: " + accessToken);
            fetchDataAndSave();
        } else {
            System.out.println("Error occurred while fetching access token");
        }
    }


    private void fetchDataAndSave() {
        try {
            // Parse accessToken into a Map
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> accessTokenMap = objectMapper.readValue(accessToken, Map.class);
            String accessTokenValue = (String) accessTokenMap.get("access_token");

            // Construct the authorization header
            String authorizationHeader = "Zoho-oauthtoken " + accessTokenValue;

            // Set up headers for the API request
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authorizationHeader);

            // Create the HTTP entity with the headers
            HttpEntity<?> requestEntity = new HttpEntity<>(headers);

            // Define the API URL
            String apiUrl = "https://people.zoho.in/people/api/forms/employee/getRecords";

            // Make the API call
            String searchParamsJson = "{\"searchField\": \"Employeestatus\", \"searchOperator\": \"Is\", \"searchText\": \"Active\"}";
            String dateSearchJson = "{\"searchField\": \"Dateofjoining\", \"searchOperator\": \"Between\", \"searchText\": \"01-Feb-2024;02-Mar-2024\"}";
            // Create URI with query parameters
            URI uri = UriComponentsBuilder.fromUriString(apiUrl)
                    .queryParam("searchParams", searchParamsJson)
                    .queryParam("searchParams", dateSearchJson)
                    .build()
                    .toUri();

            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    requestEntity,
                    String.class
            );
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                String responseBody = responseEntity.getBody();
                System.out.println(responseBody);

//                this.emergencyContactService.fetchingemergencycontact(responseBody);
//                this.personalDetailsService.fetchingpersonaldetails(responseBody);
//                this.professionalDetailsService.fetchingprofesstionaldetails(responseBody);
                this.employeeService.fetchingemployeedetails(responseBody);


            }
        } catch (IOException e) {
            System.out.println("Error occurred while parsing employeedata: " + e.getMessage());
        }
    }
}
