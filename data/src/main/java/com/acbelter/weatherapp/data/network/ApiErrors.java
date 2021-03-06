package com.acbelter.weatherapp.data.network;

import io.reactivex.annotations.NonNull;

public class ApiErrors {

    public enum PlacesApiErrors {
        OK("OK"),
        ZERO_RESULTS("ZERO_RESULTS"),
        OVER_QUERY_LIMIT("OVER_QUERY_LIMIT"),
        REQUEST_DENIED("REQUEST_DENIED"),
        INVALID_REQUEST("INVALID_REQUEST");

        String error;

        PlacesApiErrors(String error) {
            this.error = error;
        }

        public
        @NonNull
        String getError() {
            return error;
        }
    }

    public enum LocationApiErrors {
        OK("OK"),
        UNKNOWN_ERROR("UNKNOWN_ERROR"),
        ZERO_RESULTS("ZERO_RESULTS"),
        OVER_QUERY_LIMIT("OVER_QUERY_LIMIT"),
        REQUEST_DENIED("REQUEST_DENIED"),
        INVALID_REQUEST("INVALID_REQUEST"),
        NOT_FOUND("NOT_FOUND");

        String error;

        LocationApiErrors(String error) {
            this.error = error;
        }

        public
        @NonNull
        String getError() {
            return error;
        }
    }
}
