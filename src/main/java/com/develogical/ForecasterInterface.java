package com.develogical;

/**
 * Created by ape04 on 12/07/2017.
 */
public interface ForecasterInterface {

        public AppForecast getForecast(ForecasterAdaptor.appRegion region, ForecasterAdaptor.appDay day);
}
