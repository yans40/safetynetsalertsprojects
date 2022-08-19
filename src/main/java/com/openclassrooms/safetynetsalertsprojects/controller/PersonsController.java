package com.openclassrooms.safetynetsalertsprojects.controller;

import com.openclassrooms.safetynetsalertsprojects.dto.*;
import com.openclassrooms.safetynetsalertsprojects.model.Persons;
import com.openclassrooms.safetynetsalertsprojects.service.FireStationsService;
import com.openclassrooms.safetynetsalertsprojects.service.MedicalRecordsService;
import com.openclassrooms.safetynetsalertsprojects.service.PersonsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController

public class PersonsController {
    private static final Logger logger = LogManager.getLogger("PersonsController");
    @Autowired
    private PersonsService personsService;
    @Autowired
    private FireStationsService fireStationsService;
    @Autowired
    private MedicalRecordsService medicalRecordsService;


    @GetMapping("/persons")
    public List<FirestationByStationNumberDto> showPersonsList() {
        logger.info("showPersonList");
        return personsService.getPersonList();
    }

    @GetMapping("/address")
    public List<FirestationByStationNumberDto> findPersonsByAddress(@RequestParam String address) {
        logger.info("findPersonsByAddress!");
        return personsService.getPersonsByAddress(address);

    }

    @GetMapping("/firestation")
    public FirestationByStationNumberParentDto personInPotentialRisk(@RequestParam String station) throws ParseException {
        logger.info("personInPotentialRisk where stationNumber is!");
        return personsService.getFirestationByStationNumberDto(station, fireStationsService, medicalRecordsService);
    }

    @GetMapping("/childAlert")
    public ChildAlertListAndFamilyDto findChildAndFamily(@RequestParam String address) throws ParseException {
        logger.info("find a list of children were address is and persons in the same family !");
        List<FirestationByStationNumberDto> personsByAddressList = personsService.getPersonsByAddress(address);
        return personsService.getChildAlertListAndFamilyDto(address, personsByAddressList, medicalRecordsService);
    }

    @GetMapping("/phoneAlert")
    public List<PhoneListDto> phoneAlertListByStation(@RequestParam String firestation) {
        logger.info("find list of phoneNumber where firestation is .. !");
        List<FirestationsDto> fireStationsList = fireStationsService.getFirestationByStationNumber(firestation);
        List<FirestationByStationNumberDto> listOfPersons = personsService.getPersonList();
        return personsService.getPhoneListDtos(fireStationsList, listOfPersons);

    }

    @GetMapping("/fire")
    public List<FireByAddressDto> fireByAddressList(String address) throws Exception {
        logger.info("find a List of persons giving an address where fire is declared!");
        return personsService.getFireByAddressDtos(address);
    }

    @GetMapping("/communityEmail")
    public List<CommunityEmailByCityDto> emailByCity(@RequestParam String city) {
        logger.info("the list of mail of this city");
        return personsService.emailListByCity(city);
    }

    @GetMapping("/flood/stations")
    public List<FloodByListOfStationDto> floodListByStation(@RequestParam List<String> stations) throws ParseException {

        return personsService.getFloodByListOfStationDtos(stations);
    }


    @GetMapping("/personInfo")
    public List<PersonInfoByNameDto> personsListByFirstNameAndLastName(@RequestParam String firstName, String lastName) throws ParseException {

        return personsService.getPersonInfoByNameDtos(firstName, lastName);
    }


    @PostMapping("/person")
    public void addNewPerson(@RequestBody PersonDto personDto) {
        personsService.addNewPerson(personDto);
    }

    @PutMapping("/person/updateAddress")
    public void updatePersonAddress(@RequestBody PersonDto personDto, @PathVariable String firstName, String lastName) throws ParseException {
        personsService.updatePersonAddress(firstName, lastName, personDto);
    }

    @DeleteMapping("/person/delete")
    public void deletePerson(@PathVariable String firstName) {

    }


}
