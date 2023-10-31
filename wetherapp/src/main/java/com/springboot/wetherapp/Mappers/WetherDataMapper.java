package com.springboot.wetherapp.Mappers;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WetherDataMapper {
    // {"cloud_pct": 40, "temp": 6, "feels_like": 2, "humidity": 83, "min_temp": 3, "max_temp": 8, "wind_speed": 5.66, "wind_degrees": 30, "sunrise": 1698677401, "sunset": 1698713747}
    @JsonProperty("cloud_pct")
    private int cloudPct;
    private int temp;

    @JsonProperty("feels_like")
    private int feelsLike;
    private int humidity;
    
    @JsonProperty("min_temp")
    private int minTemp;

    @JsonProperty("max_temp")
    private int maxTemp;

    @JsonProperty("wind_speed")
    private double windSpeed;

    @JsonProperty("wind_degrees")
    private double windDegrees;
    private String sunrise;
    private String sunset;
    private String error;
    private String location;
}
