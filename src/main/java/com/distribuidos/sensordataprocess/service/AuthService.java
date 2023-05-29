package com.distribuidos.sensordataprocess.service;

import com.distribuidos.sensordataprocess.repository.AuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    public AuthService() {
        this.authRepository = new AuthRepository();
    }

    private final AuthRepository authRepository;

    public boolean authenticate(Long sensorId) {
        return authRepository.authenticate(sensorId);
    }
}
