package com.wilson.insurance.constant;

public final class ControllerConstants {
    private static class Api {
        private static final String VERSION = "v1";
        public static final String BASE = "/api/" + VERSION + "/";
    }

    public static class Quotation {
        public static final String BASE = Api.BASE + "quotation";
        public static final String CAR_QUOTE = "car";
    }
}
