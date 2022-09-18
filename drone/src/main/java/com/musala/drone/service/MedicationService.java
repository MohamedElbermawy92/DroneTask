package com.musala.drone.service;

import com.musala.drone.controller.model.request.LoadMedicationRequest;
import com.musala.drone.controller.model.request.MedicationRequest;
import com.musala.drone.controller.model.response.LoadMedicationResponse;
import com.musala.drone.controller.model.response.MedicationResponse;
import org.springframework.stereotype.Component;

@Component
public interface MedicationService {
    MedicationResponse addMedication(MedicationRequest medicationRequest);
    LoadMedicationResponse loadMedication(LoadMedicationRequest loadMedicationRequest);

}
