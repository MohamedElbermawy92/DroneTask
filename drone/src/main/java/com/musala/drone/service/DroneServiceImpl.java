package com.musala.drone.service;

import com.musala.drone.controller.model.request.DroneRequest;
import com.musala.drone.controller.model.response.BatteryResponse;
import com.musala.drone.controller.model.response.DroneResponse;
import com.musala.drone.controller.model.response.MedicationLoadedResponse;
import com.musala.drone.enums.ModelEnum;
import com.musala.drone.enums.StatusEnum;
import com.musala.drone.model.Drone;
import com.musala.drone.model.LoadMedication;
import com.musala.drone.repositories.DroneRepository;
import com.musala.drone.repositories.LoadMedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService{

    private final DroneRepository droneRepository;
    private final LoadMedicationRepository loadMedicationRepository;

    @Override
    public DroneResponse register(DroneRequest droneRequest) {
        Drone drone = Drone.builder().serialNumber(droneRequest.getSerialNumber()).
                model(ModelEnum.valueOf(droneRequest.getModel()).name()).
                state(StatusEnum.valueOf(droneRequest.getState()).name()).
                weightLimit(droneRequest.getWeightLimit()).
                batteryCapacity(droneRequest.getBattery()).build();
        droneRepository.save(drone);
        DroneResponse droneResponse = DroneResponse.builder()
                .message("drone added successfully")
                .serialNumber(drone.getSerialNumber()).build();
        return droneResponse;
    }

    @Override
    public List<Drone> getAllAvailableDrones() {
        return droneRepository.findAllByState(StatusEnum.IDLE.name());
    }

    @Override
    public BatteryResponse getBatteryLevel(String serialNumber) {
        if (!droneRepository.findBySerialNumber(serialNumber).isPresent()) {
            throw new RuntimeException("invalid drone ");
        }

        Drone drone = droneRepository.findBySerialNumber(serialNumber).get();
        DecimalFormat format = new DecimalFormat("#%");
        BatteryResponse batteryResponse = BatteryResponse.builder().
        battery(format.format(drone.getBatteryCapacity()))
                .serialNumber(drone.getSerialNumber()).build();
        return batteryResponse;
    }

    @Override
    public MedicationLoadedResponse checkLoadedMedicationForDrone(String serialNumber) {
        LoadMedication loadMedication = loadMedicationRepository.findByDrone(serialNumber);
        if (loadMedication == null)
            throw new RuntimeException("this drone didn't load any medication");
        MedicationLoadedResponse medicationLoadedResponse = MedicationLoadedResponse.builder().
                serialNumber(serialNumber)
                .medication(loadMedication.getMedication())
                .message("OK").build();
        return medicationLoadedResponse;
    }

}
