package com.distribuidos.sensordataprocess.service;

import com.distribuidos.sensordataprocess.model.SensorPostDataResponse;
import com.distribuidos.sensordataprocess.model.InputSensorData;
import com.distribuidos.sensordataprocess.repository.DataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InputDataService {

    DataRepository dataRepository;

    private final AuthService authService;

    public SensorPostDataResponse sendData(InputSensorData inputSensorData) {
        if (!validateData(inputSensorData)) {
            return SensorPostDataResponse.builder().success(false).build();
        }
        if (!authService.authenticate(inputSensorData.getSensorId())) {
            return SensorPostDataResponse.builder().success(false).build();
        }
        dataRepository.saveData(inputSensorData);
        return SensorPostDataResponse.builder().success(true).sensorId(inputSensorData.getSensorId()).build();
    }

    private boolean validateData(InputSensorData inputSensorData) {
        var validSensor = inputSensorData.getSensorId() != null && inputSensorData.getSensorId() > 0;
        var validWaterFlow = inputSensorData.getWaterFlow() > 0;
        var validtimeStamp = inputSensorData.getTimeStamp() != null;
        return validSensor && validWaterFlow && validtimeStamp;
    }
}
