package com.example.ss7.model;

public class Weather {
    private String Status;
    private String DateTime;
    private int WeatherIcon;
    private Temperature Temperature;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public int getWeatherIcon() {
        return WeatherIcon;
    }

    public void setWeatherIcon(int weatherIcon) {
        WeatherIcon = weatherIcon;
    }

    public com.example.ss7.model.Temperature getTemperature() {
        return Temperature;
    }

    public void setTemperature(com.example.ss7.model.Temperature temperature) {
        Temperature = temperature;
    }
}
