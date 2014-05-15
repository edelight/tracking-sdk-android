package com.shoplove.tracking;

import java.util.Map;

/**
 * Created by michaelbanholzer on 15/05/14.
 */
public class Event implements ITrackEvent {

    private Map<String, String> mParams;
    private Map<String, String> mHeaders;
    private String mPath;

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
