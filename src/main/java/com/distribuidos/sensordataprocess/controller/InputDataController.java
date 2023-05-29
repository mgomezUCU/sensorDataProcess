package com.distribuidos.sensordataprocess.controller;

import com.distribuidos.sensordataprocess.model.InputSensorData;
import com.distribuidos.sensordataprocess.service.InputDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class InputDataController {

    private final InputDataService inputDataService;

    @PostMapping("/sendData")
    ResponseEntity<String> sendData(@RequestBody InputSensorData inputSensorData) {
        var response = inputDataService.sendData(inputSensorData);
        if (!response.isSuccess()) {
            log.error(String.format("Error getting data from sensor %s", inputSensorData.getSensorId()));
            return ResponseEntity.badRequest().body("Error getting data");
        }
        log.info(String.format("Data from sensor %s received", inputSensorData.getSensorId()));
        return ResponseEntity.ok(response.getSensorId().toString());
    }
}
