package com.distribuidos.sensordataprocess.repository;

import com.distribuidos.sensordataprocess.model.InputSensorData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class DataRepository {

    private List<InputSensorData> data;

    public boolean saveData(InputSensorData inputSensorData) {
        return data.add(inputSensorData);
    }
}
