package com.develogical;

import org.junit.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class CacheTest {

    AppForecast testForcast = new AppForecast("test", 30);

    ForecasterInterface forecaster = new ForecasterInterface() {
        @Override
        public AppForecast getForecast(ForecasterAdaptor.appRegion region, ForecasterAdaptor.appDay day) {
            return testForcast;
        }
    };

    @Test
    public void cacheCanStoreData() throws Exception {
        ForecasterCache forecasterCache = new ForecasterCache(forecaster);

        AppForecast appForecast = new AppForecast("cloudy", 30);

        forecasterCache.addToCache(ForecasterAdaptor.appRegion.LONDON, ForecasterAdaptor.appDay.MONDAY, appForecast);

        assertThat(forecasterCache.itemCount(), equalTo(1));
    }

    @Test
    public void cacheDataCanBeRetrieved() throws Exception{
        ForecasterCache forecasterCache = new ForecasterCache(forecaster);

        AppForecast appForecast = new AppForecast("cloudy", 30);

        forecasterCache.addToCache(ForecasterAdaptor.appRegion.LONDON, ForecasterAdaptor.appDay.MONDAY, appForecast);

        assertThat(forecasterCache.getForecast(ForecasterAdaptor.appRegion.LONDON, ForecasterAdaptor.appDay.MONDAY), equalTo(appForecast));
    }

    @Test
    public void uncachedDataCanBeRetrievedFromForecaster() throws Exception{
        ForecasterCache forecasterCache = new ForecasterCache(forecaster);

        AppForecast appForecast = new AppForecast("cloudy", 30);

        assertThat(forecasterCache.getForecast(ForecasterAdaptor.appRegion.LONDON, ForecasterAdaptor.appDay.MONDAY), equalTo(testForcast));
    }

    @Test
    public void removeFromCacheWhenMaxCacheCountIsReached() throws Exception{
        ForecasterCache forecasterCache = new ForecasterCache(forecaster, 5);

        AppForecast appForecast = new AppForecast("cloudy", 30);

        forecasterCache.addToCache(ForecasterAdaptor.appRegion.LONDON, ForecasterAdaptor.appDay.MONDAY, appForecast);
        forecasterCache.addToCache(ForecasterAdaptor.appRegion.LONDON, ForecasterAdaptor.appDay.TUESDAY, appForecast);
        forecasterCache.addToCache(ForecasterAdaptor.appRegion.LONDON, ForecasterAdaptor.appDay.WEDNESDAY, appForecast);
        forecasterCache.addToCache(ForecasterAdaptor.appRegion.LONDON, ForecasterAdaptor.appDay.THURSDAY, appForecast);
        forecasterCache.addToCache(ForecasterAdaptor.appRegion.LONDON, ForecasterAdaptor.appDay.FRIDAY, appForecast);
        forecasterCache.addToCache(ForecasterAdaptor.appRegion.LONDON, ForecasterAdaptor.appDay.SATURDAY, appForecast);

        assertThat(forecasterCache.itemCount(), equalTo(5));
    }

    @Test
    public void verifyOldestIsRemoveFromCacheWhenMaxCacheCountIsReached() throws Exception{
        ForecasterCache forecasterCache = new ForecasterCache(forecaster, 5);

        AppForecast appForecast = new AppForecast("cloudy", 30);

        forecasterCache.addToCache(ForecasterAdaptor.appRegion.LONDON, ForecasterAdaptor.appDay.MONDAY, appForecast);
        forecasterCache.addToCache(ForecasterAdaptor.appRegion.LONDON, ForecasterAdaptor.appDay.TUESDAY, appForecast);
        forecasterCache.addToCache(ForecasterAdaptor.appRegion.LONDON, ForecasterAdaptor.appDay.WEDNESDAY, appForecast);
        forecasterCache.addToCache(ForecasterAdaptor.appRegion.LONDON, ForecasterAdaptor.appDay.THURSDAY, appForecast);
        forecasterCache.addToCache(ForecasterAdaptor.appRegion.LONDON, ForecasterAdaptor.appDay.FRIDAY, appForecast);
        forecasterCache.addToCache(ForecasterAdaptor.appRegion.LONDON, ForecasterAdaptor.appDay.SATURDAY, appForecast);

        assertThat(forecasterCache.getForecast(ForecasterAdaptor.appRegion.LONDON, ForecasterAdaptor.appDay.MONDAY), equalTo(testForcast));
    }
}
