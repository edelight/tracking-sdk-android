package com.shoplove.tracking;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.Volley;

import org.apache.http.impl.client.AbstractHttpClient;

/**
 * The RequestHandler used in the sdk.
 * It is built upon the volley framework and thus uses a RequestQueue to manage all requests.
 */
public class RequestHandler implements IRequestHandler {

    /** The volley queue wherein all requests are handled. */
    private RequestQueue mRequestQueue;

    /** The Http configuration for the requests. */
    private IHttpConfig mHTTPConfig;

    /**
     * Creates a new RequestHandler object with the given configuration.
     *
     * @param context The Application Context.
     * @param httpClient The used HttpClient to send the requests.
     * @param httpConfig The Http configuration for the requests.
     */
    RequestHandler(Context context, AbstractHttpClient httpClient, IHttpConfig httpConfig) {
        mHTTPConfig = httpConfig;
        HttpStack httpStack = new HttpClientStack( httpClient );
        mRequestQueue = Volley.newRequestQueue(context, httpStack);
    }


    /**
     * Creates a new request with the passed trackevent.
     * The trackevent will be ignored if you pass a malformed trackevent data.
     *
     * @param event The event to send.
     * @return True if it's a valid trackevent data and the request was enqueued.
     */
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

    /**
     * Validates the trackevent.
     *
     * @param event The trackevent to validate.
     * @return True if it's a valid trackevent.
     */
    private boolean validateTrackEvent(ITrackEvent event) {
        if(event.getTrackEventParams() == null || event.getTrackEventParams().size()<=0) {
            return false;
        }
        else {
            return true;
        }
    }
}
