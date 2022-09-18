package com.musala.drone.controller.model.response;

import com.musala.drone.model.Medication;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicationLoadedResponse {
     String serialNumber;
     Medication medication;
     String message;
}
