package com.openclassrooms.safetynetsalertsprojects.controller;

import com.openclassrooms.safetynetsalertsprojects.dto.FirestationsDto;
import com.openclassrooms.safetynetsalertsprojects.model.FireStations;
import com.openclassrooms.safetynetsalertsprojects.service.FireStationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/firestations")
public class FirestationsController {

    @Autowired
    private FireStationsService fireStationsService;


    @GetMapping
    public List<FirestationsDto> showFireStationsList() {

        return fireStationsService.getFireStationsList();
    }

    @GetMapping("/stationNumber")
    public List<FirestationsDto> findFirestationByStationNumber(@RequestParam String station) {

        return fireStationsService.getFirestationByStationNumber(station);

    }

//    @PostMapping("/firestation")
//    public FireStations createfirestation(@RequestBody FireStations fireStations){
//        FireStations fireStations1 =
//    }



}
