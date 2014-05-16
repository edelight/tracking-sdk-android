package com.shoplove.tracking;

import java.util.Map;

/**
 * A basic implementation of the ITrackEvent interface.
 */
public class Event implements ITrackEvent {

    private Map<String, String> mParams;
    private Map<String, String> mHeaders;
    private String mPath;

    /**
     * Creates a new Event object with all required information to conform to the ITrackEvent interface.
     *
     * @param path The path to append to the baseUrl.
     * @param headers The header information of the request.
     * @param params The body information of the request.
     */
    Event(String path, Map<String, String> headers, Map<String, String> params) {
        mPath = path;
        mHeaders = headers;
        mParams = params;
    }

    @Override
    public String getTrackEventPath() {
        return mPath;
    }

    @Override
    public Map<String, String> getTrackEventHeaders() {
        return mHeaders;
    }

    @Override
    public Map<String, String> getTrackEventParams() {
        return mParams;
    }

    @Override
    public String toString() {
        return "[Event: Path " + getTrackEventPath() + ", Headers " + getTrackEventHeaders() + ", Params " + getTrackEventParams() + "]";
    }
}
