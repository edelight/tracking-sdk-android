package com.shoplove.tracking;

import java.util.Map;

/**
 * The basic information for the POST request.
 */
public interface ITrackEvent {

    /**
     * @return A path which will be appended to the base url.
     */
    public String getTrackEventPath();

    /**
     * @return The Header fields for the POST request.
     */
    public Map<String, String> getTrackEventHeaders();

    /**
     * @return The Body parameters for the POST request.
     */
    public Map<String, String> getTrackEventParams();

}
