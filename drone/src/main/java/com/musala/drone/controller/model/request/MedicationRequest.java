package com.musala.drone.controller.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicationRequest {
     String code;
     String name;
     double weight;
     String image;
}
