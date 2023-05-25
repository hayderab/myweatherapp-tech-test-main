package com.weatherapp.myweatherapp.controller;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.service.WeatherService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WeatherController {

  @Autowired
  WeatherService weatherService;

  @GetMapping("/forecast/{city}")
  public ResponseEntity<CityInfo> forecastByCity(@PathVariable("city") String city) {
    CityInfo ci = weatherService.forecastByCity(city);
    return ResponseEntity.ok(ci);
  }

  @GetMapping("/compare/daylight/{city1}/{city2}")
  public ResponseEntity<String> compareDaylight(@PathVariable("city1") String city1, @PathVariable("city2") String city2) {
    try {
      String response = weatherService.compareDaylight(city1, city2);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      e.printStackTrace();
      // Handle the exception and return an error response
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while comparing daylight. Double check the city name.");
    }
  }

  @GetMapping("/check/rain/{city1}/{city2}")
  public ResponseEntity<String> checkRain(@PathVariable("city1") String city1, @PathVariable("city2") String city2) {
    try {
      String response = weatherService.checkRain(city1, city2);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      e.printStackTrace();
      // Handle the exception and return an error response
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while checking rain conditions. Double check the city name.");
    }
  }



}
