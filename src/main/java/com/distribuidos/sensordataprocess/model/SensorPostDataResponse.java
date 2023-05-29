package com.distribuidos.sensordataprocess.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SensorPostDataResponse {
    private boolean success;

    private Long sensorId;
}
