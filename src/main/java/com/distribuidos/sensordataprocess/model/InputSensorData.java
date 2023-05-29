package com.distribuidos.sensordataprocess.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InputSensorData {
    private Long sensorId;

    private Location location;

    private String area;

    private Long timeStamp;

    private double waterFlow;
}
