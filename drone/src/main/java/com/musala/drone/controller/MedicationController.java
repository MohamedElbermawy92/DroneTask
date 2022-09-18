package com.musala.drone.controller;

import com.musala.drone.controller.model.request.LoadMedicationRequest;
import com.musala.drone.controller.model.request.MedicationRequest;
import com.musala.drone.controller.model.response.LoadMedicationResponse;
import com.musala.drone.controller.model.response.MedicationLoadedResponse;
import com.musala.drone.controller.model.response.MedicationResponse;
import com.musala.drone.service.DroneService;
import com.musala.drone.service.MedicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/medication")
@Slf4j
@RequiredArgsConstructor
@Api(tags ={"Medication APIs"})
public class MedicationController {

    private final MedicationService medicationService;
    private final DroneService droneService;

    @ApiOperation("loadMedication")
    @PostMapping(path = "/loadMedication", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LoadMedicationResponse> loadMedication(@RequestBody LoadMedicationRequest loadMedicationRequest) {
        return new ResponseEntity<LoadMedicationResponse>(medicationService.loadMedication(loadMedicationRequest), HttpStatus.OK);
    }


    @ApiOperation("addMedication")
    @PostMapping(path="/addMedication", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MedicationResponse> addMedication(@RequestBody MedicationRequest medicationRequest){

        MedicationResponse medicationResponse = medicationService.addMedication(medicationRequest);
        return new ResponseEntity<MedicationResponse>(medicationResponse, HttpStatus.OK);

    }

    @ApiOperation("checkLoadedMedicationForDrone")
    @GetMapping(path ="/checkLoadedMedicationForDrone", produces = "application/json")
    public ResponseEntity<MedicationLoadedResponse> checkLoadedMedicationForDrone(
            @RequestParam(required = true, name = "serialNumber") String serialNumber) {
        if ( serialNumber.isEmpty()) {
            throw new RuntimeException("SerialNumber cannot be empty");
        }
        return new ResponseEntity<MedicationLoadedResponse>(
                droneService.checkLoadedMedicationForDrone(serialNumber), HttpStatus.OK);
    }

}
