package com.develogical;

public class AppForecast {
    private final String summary;
    private final int temperature;

    public AppForecast(String summary, int temperature) {
        this.summary = summary;
        this.temperature = temperature;
    }

    public String summary() {
        return this.summary;
    }

    public int temperature() {
        return this.temperature;
    }
}