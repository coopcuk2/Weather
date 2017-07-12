package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

public class ForecasterAdaptor implements ForecasterInterface {

    public AppForecast getForecast(appRegion region, appDay day) {
        Forecaster forecaster = new Forecaster();

        Forecast f = forecaster.forecastFor(convertAppRegionToRegion(region), convertAppDayToDay(day));

        return new AppForecast(f.summary(), f.temperature());
    }

    private static Region convertAppRegionToRegion(appRegion region) {

        switch (region) {
            case LONDON:
                return Region.LONDON;
            case EDINBURGH:
                return Region.EDINBURGH;
            default:
                return null;
        }
    }

    private static Day convertAppDayToDay(appDay day) {

        switch (day) {
            case MONDAY:
                return Day.MONDAY;
            case TUESDAY:
                return Day.TUESDAY;
            default:
                return null;

        }
    }

    public enum appRegion {
        LONDON,
        EDINBURGH,
        BIRMINGHAM,
        GLASGOW,
        MANCHESTER,
        NORTH_ENGLAND,
        SOUTH_WEST_ENGLAND,
        SOUTH_EAST_ENGLAND,
        WALES;
    }

    public enum appDay {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY;

    }
}