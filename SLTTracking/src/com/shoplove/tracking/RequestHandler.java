package com.shoplove.tracking;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.Volley;

import org.apache.http.impl.client.AbstractHttpClient;

/**
 * Created by michaelbanholzer on 15/05/14.
 */
public class RequestHandler implements IRequestHandler {

    private RequestQueue mRequestQueue;

    private IHttpConfig mHTTPConfig;

    RequestHandler(Context context, AbstractHttpClient httpClient, IHttpConfig httpConfig) {
        mHTTPConfig = httpConfig;
        HttpStack httpStack = new HttpClientStack( httpClient );
        mRequestQueue = Volley.newRequestQueue(context, httpStack);
    }


    public boolean addTrackEvent(ITrackEvent event) {
        if(validateTrackEvent(event)) {
            TrackEventRequest request = new TrackEventRequest(mHTTPConfig.getBaseUrl(), event);
            mRequestQueue.add(request);
            return true;
        }
        else {
            return false;
        }
    }

    private boolean validateTrackEvent(ITrackEvent event) {
        if(event.getTrackEventParams() == null || event.getTrackEventParams().size()<=0) {
            return false;
        }
        else {
            return true;
        }
    }
}
