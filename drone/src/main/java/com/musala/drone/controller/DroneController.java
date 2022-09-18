package com.musala.drone.controller;


import com.musala.drone.controller.model.request.DroneRequest;
import com.musala.drone.controller.model.response.*;
import com.musala.drone.model.Drone;
import com.musala.drone.service.DroneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/drone")
@Slf4j
@RequiredArgsConstructor
@Api(tags ={"Drone APIs"})
public class DroneController {

    private final DroneService droneService;

    @ApiOperation("addDrone")
    @PostMapping(path="/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DroneResponse> addDrone(@RequestBody DroneRequest droneRequest){
        return new ResponseEntity<DroneResponse>(droneService.register(droneRequest), HttpStatus.OK);
    }


    @ApiOperation("getAvailableDrone")
    @GetMapping(path= "/getAvailableDrones", produces = "application/json")
    public ResponseEntity<AvailableDronesResponse> getAvailableDrone() {
        List<Drone> drones = droneService.getAllAvailableDrones();
        AvailableDronesResponse availableDronesResponse = AvailableDronesResponse.builder()
                .drones(drones).build();
        availableDronesResponse.setDrones(drones);
        return new ResponseEntity<AvailableDronesResponse>(availableDronesResponse, HttpStatus.OK);
    }


    @ApiOperation("checkBatteryLevel")
    @GetMapping(path ="/checkBatteryLevel", produces = "application/json")
    public ResponseEntity<BatteryResponse> checkBatteryLevel(
            @RequestParam(required = true, name = "serialNumber") String serialNumber) {
        if ( serialNumber.isEmpty()) {
            throw new RuntimeException("SerialNumber cannot be empty");
        }
        return new ResponseEntity<BatteryResponse>(droneService.getBatteryLevel(serialNumber), HttpStatus.OK);
    }

}
