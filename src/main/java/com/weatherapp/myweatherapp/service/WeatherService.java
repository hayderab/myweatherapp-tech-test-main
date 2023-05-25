package com.weatherapp.myweatherapp.service;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class WeatherService {

  @Autowired
  VisualcrossingRepository weatherRepo;

  public CityInfo forecastByCity(String city) {

    return weatherRepo.getByCity(city);
  }

  public String compareDaylight(String city1, String city2) {
    CityInfo cityInfo1 = forecastByCity(city1);
    CityInfo cityInfo2 = forecastByCity(city2);

    String sunriseCity1 = cityInfo1.getCurrentConditions().getSunrise();
    String sunsetCity1 = cityInfo1.getCurrentConditions().getSunset();
    long daylightHoursCity1 = calculateDaylightHours(sunriseCity1, sunsetCity1);

    String sunriseCity2 = cityInfo2.getCurrentConditions().getSunrise();
    String sunsetCity2 = cityInfo2.getCurrentConditions().getSunset();
    long daylightHoursCity2 = calculateDaylightHours(sunriseCity2, sunsetCity2);

    String response;
    if (daylightHoursCity1 > daylightHoursCity2) {
        response = "The longest day is in " + city1 + " with " + daylightHoursCity1 + " hours of daylight.";
    } else if (daylightHoursCity2 > daylightHoursCity1) {
        response = "The longest day is in " + city2 + " with " + daylightHoursCity2 + " hours of daylight.";
    } else {
        response = "Both cities have the same length of daylight hours.";
    }

    return response;
  }

  public String checkRain(String city1, String city2) {
    CityInfo cityInfo1 = forecastByCity(city1);
    CityInfo cityInfo2 = forecastByCity(city2);

    boolean isRainingCity1 = isRaining(cityInfo1);
    boolean isRainingCity2 = isRaining(cityInfo2);

    String response;
    if (isRainingCity1 && isRainingCity2) {
        response = "It is currently raining in both " + city1 + " and " + city2;
    } else if (isRainingCity1) {
        response = "It is currently raining in " + city1;
    } else if (isRainingCity2) {
        response = "It is currently raining in " + city2;
    } else {
        response = "It is not currently raining in either " + city1 + " or " + city2;
    }

    return response;
  }

  private long calculateDaylightHours(String sunrise, String sunset) {
    try {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date sunriseTime = format.parse(sunrise);
        Date sunsetTime = format.parse(sunset);
        long daylightHours = (sunsetTime.getTime() - sunriseTime.getTime()) / (60 * 60 * 1000);
        return daylightHours;
    } catch (ParseException e) {
        e.printStackTrace();
        return 0;
    }
  }

  private boolean isRaining(CityInfo cityInfo) {
    String conditions = cityInfo.getCurrentConditions().getConditions();
    return conditions.toLowerCase().contains("rain");
  }
}

