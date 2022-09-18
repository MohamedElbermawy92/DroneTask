package com.musala.drone.controller.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoadMedicationResponse {

     String serialNumber;
     String code;
     String message;
}
