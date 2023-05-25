package com.weatherapp.myweatherapp.service;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.model.CityInfo.CurrentConditions;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherServiceTest {

  @Mock
  private VisualcrossingRepository weatherRepo;

  private WeatherService weatherService;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    weatherService = new WeatherService();
    weatherService.weatherRepo = weatherRepo;
  }

  @Test
  void compareDaylight_ShouldReturnLongestDayCity() {
    // Arrange
    String city1 = "City1";
    String city2 = "City2";

    CityInfo cityInfo1 = new CityInfo();
    CurrentConditions currentConditions1 = new CurrentConditions();
    currentConditions1.setSunrise("06:00:00");
    currentConditions1.setSunset("18:00:00");
    cityInfo1.setCurrentConditions(currentConditions1);

    CityInfo cityInfo2 = new CityInfo();
    CurrentConditions currentConditions2 = new CurrentConditions();
    currentConditions2.setSunrise("05:30:00");
    currentConditions2.setSunset("18:30:00");
    cityInfo2.setCurrentConditions(currentConditions2);

    when(weatherRepo.getByCity(city1)).thenReturn(cityInfo1);
    when(weatherRepo.getByCity(city2)).thenReturn(cityInfo2);

    // Act
    String result = weatherService.compareDaylight(city1, city2);

    // Assert
    assertEquals("The longest day is in City2 with 13 hours of daylight.", result);
    verify(weatherRepo, times(2)).getByCity(anyString());
  }


  @Test
  void checkRain_ShouldReturnRainStatus() {
    // Arrange
    String city1 = "City1";
    String city2 = "City2";

    CityInfo cityInfo1 = new CityInfo();
    CurrentConditions currentConditions1 = new CurrentConditions();
    currentConditions1.setConditions("Rain showers");
    cityInfo1.setCurrentConditions(currentConditions1);

    CityInfo cityInfo2 = new CityInfo();
    CurrentConditions currentConditions2 = new CurrentConditions();
    currentConditions2.setConditions("Cloudy");
    cityInfo2.setCurrentConditions(currentConditions2);

    when(weatherRepo.getByCity(city1)).thenReturn(cityInfo1);
    when(weatherRepo.getByCity(city2)).thenReturn(cityInfo2);

    // Act
    String result = weatherService.checkRain(city1, city2);

    // Assert
    assertEquals("It is currently raining in City1", result);
    verify(weatherRepo, times(2)).getByCity(anyString());
  }

  @Test
void compareDaylight_ShouldReturnSameDaylightHours() {
  // Arrange
  String city1 = "City1";
  String city2 = "City2";

  CityInfo cityInfo1 = new CityInfo();
  CurrentConditions currentConditions1 = new CurrentConditions();
  currentConditions1.setSunrise("06:00:00");
  currentConditions1.setSunset("18:00:00");
  cityInfo1.setCurrentConditions(currentConditions1);

  CityInfo cityInfo2 = new CityInfo();
  CurrentConditions currentConditions2 = new CurrentConditions();
  currentConditions2.setSunrise("06:00:00");
  currentConditions2.setSunset("18:00:00");
  cityInfo2.setCurrentConditions(currentConditions2);

  when(weatherRepo.getByCity(city1)).thenReturn(cityInfo1);
  when(weatherRepo.getByCity(city2)).thenReturn(cityInfo2);

  // Act
  String result = weatherService.compareDaylight(city1, city2);

  // Assert
  assertEquals("Both cities have the same length of daylight hours.", result);
  verify(weatherRepo, times(2)).getByCity(anyString());
}



@Test
void checkRain_ShouldReturnNoRainStatus() {
  // Arrange
  String city1 = "City1";
  String city2 = "City2";

  CityInfo cityInfo1 = new CityInfo();
  CurrentConditions currentConditions1 = new CurrentConditions();
  currentConditions1.setConditions("Sunny");
  cityInfo1.setCurrentConditions(currentConditions1);

  CityInfo cityInfo2 = new CityInfo();
  CurrentConditions currentConditions2 = new CurrentConditions();
  currentConditions2.setConditions("Cloudy");
  cityInfo2.setCurrentConditions(currentConditions2);

  when(weatherRepo.getByCity(city1)).thenReturn(cityInfo1);
  when(weatherRepo.getByCity(city2)).thenReturn(cityInfo2);

  // Act
  String result = weatherService.checkRain(city1, city2);

  // Assert
  assertEquals("It is not currently raining in either City1 or City2", result);
  verify(weatherRepo, times(2)).getByCity(anyString());
}


}