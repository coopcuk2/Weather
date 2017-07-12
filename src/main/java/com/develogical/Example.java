package com.develogical;

public class Example {
    public static void main(String[] args) {
        ForecasterAdaptor appLondonForecast = new ForecasterAdaptor();

        AppForecast data = appLondonForecast.getForecast(ForecasterAdaptor.appRegion.LONDON, ForecasterAdaptor.appDay.MONDAY);

        System.out.println("London outlook: " + data.summary());
        System.out.println("London temperature: " + data.temperature());
    }
}
