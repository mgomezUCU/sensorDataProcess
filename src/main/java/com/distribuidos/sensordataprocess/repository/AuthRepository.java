package com.distribuidos.sensordataprocess.repository;

import org.springframework.stereotype.Repository;

import java.util.List;


public class AuthRepository {
    private final List<Long> validSensors = List.of(1L, 2L, 3L);

    public boolean authenticate(Long sensorId) {
        return validSensors.contains(sensorId);
    }
}
