package com.distribuidos.sensordataprocess.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Location {

    private Long latitude;

    private Long longitude;
}
