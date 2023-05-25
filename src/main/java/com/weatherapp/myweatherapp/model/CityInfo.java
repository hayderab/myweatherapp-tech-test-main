package com.weatherapp.myweatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CityInfo {

  @JsonProperty("address")
  private String address;

  @JsonProperty("description")
  private String description;

  @JsonProperty("currentConditions")
  private CurrentConditions currentConditions;

  @JsonProperty("days")
  private List<DayInfo> days;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CurrentConditions getCurrentConditions() {
    return currentConditions;
  }

  public void setCurrentConditions(CurrentConditions currentConditions) {
    this.currentConditions = currentConditions;
  }

  public List<DayInfo> getDays() {
    return days;
  }

  public void setDays(List<DayInfo> days) {
    this.days = days;
  }

  public static class CurrentConditions {
    @JsonProperty("temp")
    private String temperature;

    @JsonProperty("sunrise")
    private String sunrise;

    @JsonProperty("sunset")
    private String sunset;

    @JsonProperty("feelslike")
    private String feelsLike;

    @JsonProperty("humidity")
    private String humidity;

    @JsonProperty("conditions")
    private String conditions;

    public CurrentConditions(String string) {
    }

    public CurrentConditions() {
    }

    public String getTemperature() {
      return temperature;
    }

    public void setTemperature(String temperature) {
      this.temperature = temperature;
    }

    public String getSunrise() {
      return sunrise;
    }

    public void setSunrise(String sunrise) {
      this.sunrise = sunrise;
    }

    public String getSunset() {
      return sunset;
    }

    public void setSunset(String sunset) {
      this.sunset = sunset;
    }

    public String getFeelsLike() {
      return feelsLike;
    }

    public void setFeelsLike(String feelsLike) {
      this.feelsLike = feelsLike;
    }

    public String getHumidity() {
      return humidity;
    }

    public void setHumidity(String humidity) {
      this.humidity = humidity;
    }

    public String getConditions() {
      return conditions;
    }

    public void setConditions(String conditions) {
      this.conditions = conditions;
    }
  }

  public static class DayInfo {
    @JsonProperty("datetime")
    private String date;

    @JsonProperty("temp")
    private String temperature;

    @JsonProperty("tempmax")
    private String maxTemperature;

    @JsonProperty("tempmin")
    private String minTemperature;

    @JsonProperty("conditions")
    private String conditions;

    @JsonProperty("description")
    private String description;

    public String getDate() {
      return date;
    }

    public void setDate(String date) {
      this.date = date;
    }

    public String getTemperature() {
      return temperature;
    }

    public void setTemperature(String temperature) {
      this.temperature = temperature;
    }

    public String getMaxTemperature() {
      return maxTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
      this.maxTemperature = maxTemperature;
    }

    public String getMinTemperature() {
      return minTemperature;
    }

    public void setMinTemperature(String minTemperature) {
      this.minTemperature = minTemperature;
    }

    public String getConditions() {
      return conditions;
    }

    public void setConditions(String conditions) {
      this.conditions = conditions;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }
  }
}