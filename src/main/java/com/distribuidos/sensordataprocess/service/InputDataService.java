package com.distribuidos.sensordataprocess.service;

import com.distribuidos.sensordataprocess.model.SensorPostDataResponse;
import com.distribuidos.sensordataprocess.model.InputSensorData;
import com.distribuidos.sensordataprocess.repository.DataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
@AllArgsConstructor
public class InputDataService {

    DataRepository dataRepository;

    private final AuthService authService;

    private final MessageService messageService;



    public SensorPostDataResponse sendData(InputSensorData inputSensorData) {
        if (!validateData(inputSensorData)) {
            return SensorPostDataResponse.builder().success(false).build();
        }
        if (!authService.authenticate(inputSensorData.getSensorId())) {
            return SensorPostDataResponse.builder().success(false).build();
        }
        dataRepository.saveData(inputSensorData);
        messageService.sendMessage(inputSensorData.getWaterFlow().toString());
        return SensorPostDataResponse.builder().success(true).sensorId(inputSensorData.getSensorId()).build();
    }

    private boolean validateData(InputSensorData inputSensorData) {
        var validSensor = inputSensorData.getSensorId() != null && inputSensorData.getSensorId() > 0;
        var validWaterFlow = inputSensorData.getWaterFlow() > 0;
        var validtimeStamp = inputSensorData.getTimeStamp() != null;
        return validSensor && validWaterFlow && validtimeStamp;
    }




    public void saveData(InputSensorData data) {
        // abro coenxion
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sensorData", "distribuidosAdmin", "K3DsTQ3RxuC4b4");

            PreparedStatement statement = connection.prepareStatement("INSERT INTO input_sensor_data (sensor_id, latitude, longitude, area, timestamp_value, water_flow) VALUES (?, ?, ?, ?, ?, ?)");

            // seteo parametros
            statement.setLong(1, data.getSensorId());
            statement.setDouble(2, data.getLocation().getLatitude());
            statement.setDouble(3, data.getLocation().getLongitude());
            statement.setString(4, data.getArea());
            statement.setLong(5, data.getTimeStamp());
            statement.setDouble(6, data.getWaterFlow());

            statement.executeUpdate();

            // cierro con
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
