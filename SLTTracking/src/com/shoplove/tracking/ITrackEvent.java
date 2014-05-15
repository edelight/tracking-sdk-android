package com.shoplove.tracking;

import java.util.Map;

/**
 * Created by michaelbanholzer on 15/05/14.
 */
public interface ITrackEvent {

    public String getTrackEventPath();
    public Map<String, String> getTrackEventHeaders();
    public Map<String, String> getTrackEventParams();

}
