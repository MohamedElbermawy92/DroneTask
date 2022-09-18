package com.musala.drone.controller.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoadMedicationRequest {

    private String serialNumber;
    private String sourceLocation;
    private String destinationLocation;
    private String code;

}
