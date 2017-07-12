package com.develogical;

import java.util.*;

public class ForecasterCache implements ForecasterInterface {

    ForecasterInterface forecaster;
    int MaxCacheCount;

    private LinkedHashMap<String,AppForecast> forecastCache;

    public ForecasterCache(ForecasterInterface forecaster){
        this(forecaster,1000);
    }
    public ForecasterCache(ForecasterInterface forecaster, int maxCacheCount){
        this.MaxCacheCount = maxCacheCount;
        forecastCache = new LinkedHashMap<String,AppForecast>(){
            @Override
            protected boolean removeEldestEntry(final Map.Entry eldest) {
                return size() > MaxCacheCount;
            }
        };
        this.forecaster = forecaster;
    }

    public void addToCache(ForecasterAdaptor.appRegion region, ForecasterAdaptor.appDay day, AppForecast forecast) {

        forecastCache.put(toString().format("%s_%s",region.toString(), day.toString()),  forecast);

    }

    public int itemCount(){
       return forecastCache.size();
    }

    public AppForecast getForecast(ForecasterAdaptor.appRegion region, ForecasterAdaptor.appDay day) {
        String cacheKey = toString().format("%s_%s",region.toString(), day.toString());

        if(forecastCache.containsKey(cacheKey)){
            return forecastCache.get(cacheKey);
        }
        else {
            return forecaster.getForecast(region, day);
        }
    }
}
