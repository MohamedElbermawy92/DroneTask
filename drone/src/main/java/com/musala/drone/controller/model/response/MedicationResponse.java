package com.musala.drone.controller.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicationResponse {
     String code;
     String message;
}
