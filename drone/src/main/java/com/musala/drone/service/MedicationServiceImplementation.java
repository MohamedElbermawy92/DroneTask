package com.musala.drone.service;

import com.musala.drone.controller.model.request.LoadMedicationRequest;
import com.musala.drone.controller.model.request.MedicationRequest;
import com.musala.drone.controller.model.response.LoadMedicationResponse;
import com.musala.drone.controller.model.response.MedicationResponse;
import com.musala.drone.exceptionHandler.BusinessException;
import com.musala.drone.exceptionHandler.NotFoundException;
import com.musala.drone.model.Drone;
import com.musala.drone.model.LoadMedication;
import com.musala.drone.model.Medication;
import com.musala.drone.repositories.DroneRepository;
import com.musala.drone.repositories.LoadMedicationRepository;
import com.musala.drone.repositories.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class MedicationServiceImplementation implements MedicationService {
    private final MedicationRepository medicationRepository;
    private final DroneRepository droneRepository;
    private final LoadMedicationRepository loadMedicationRepository;
    @Override
    public MedicationResponse addMedication(MedicationRequest medicationRequest) {
        Medication medication =  Medication.builder()
                .code(medicationRequest.getCode())
                .name(medicationRequest.getName())
                .weight(medicationRequest.getWeight())
                .image(medicationRequest.getImage())
                .build();
        medicationRepository.save(medication);
        MedicationResponse medicationResponse = MedicationResponse.builder().
                code(medication.getCode()).
                message("medication added successfully").build();
        return medicationResponse;
    }

    @Override
    public LoadMedicationResponse loadMedication(LoadMedicationRequest loadMedicationRequest) {

        if (!droneRepository.findBySerialNumber(loadMedicationRequest.getSerialNumber()).isPresent())
            throw new NotFoundException("invalid drone serial number");
        if (!medicationRepository.findByCode(loadMedicationRequest.getCode()).isPresent())
            throw new NotFoundException("invalid medication code");

        Drone drone = droneRepository.findBySerialNumber(loadMedicationRequest.getSerialNumber()).get();
        Medication medication = medicationRepository.findByCode(loadMedicationRequest.getCode()).get();

        if (drone.getWeightLimit() < medication.getWeight()) {
            throw new BusinessException("The medication weight exceeds the weight limit of a drone");
        }

        if (drone.getBatteryCapacity().compareTo(new BigDecimal(0.25)) < 0) {
            throw new BusinessException("Drone can't load medication while battery capacity is  below 25%");
        }
        LoadMedication loadMedication = LoadMedication.builder()
                .destinationLocation(loadMedicationRequest.getDestinationLocation())
                .drone(drone)
                .medication(medication)
                .sourceLocation(loadMedicationRequest.getSourceLocation())
                .build();
        loadMedicationRepository.save(loadMedication);
        droneRepository.setUpdateState("LOADED", loadMedicationRequest.getSerialNumber());
        LoadMedicationResponse loadMedicationResponse = LoadMedicationResponse.builder().
                serialNumber(loadMedicationRequest.getSerialNumber())
                .message("Medication with code " + loadMedicationRequest.getCode() + " loaded successfully on drone with serial " + loadMedicationRequest.getSerialNumber())
                .code(loadMedicationRequest.getCode()).build();
        return loadMedicationResponse;
    }

}
