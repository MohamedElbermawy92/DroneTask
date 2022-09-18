package com.musala.drone.service;


import com.musala.drone.controller.model.request.DroneRequest;
import com.musala.drone.controller.model.request.LoadMedicationRequest;
import com.musala.drone.controller.model.response.BatteryResponse;
import com.musala.drone.controller.model.response.DroneResponse;
import com.musala.drone.controller.model.response.LoadMedicationResponse;
import com.musala.drone.controller.model.response.MedicationLoadedResponse;
import com.musala.drone.model.Drone;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface DroneService {

    DroneResponse register(DroneRequest drone);
    List<Drone> getAllAvailableDrones();
    BatteryResponse getBatteryLevel(String serialNumber) ;
    MedicationLoadedResponse checkLoadedMedicationForDrone(String serialNumber);

}
