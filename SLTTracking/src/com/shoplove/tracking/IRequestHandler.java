package com.shoplove.tracking;

/**
 * Interface for a RequestHandler.
 */
public interface IRequestHandler {

    /**
     * Creates and enqueues a POST request.
     *
     * @param event The event to send.
     * @return True, if the created request has been enqueued successfully.
     */
    public boolean addTrackEvent(ITrackEvent event);
}
