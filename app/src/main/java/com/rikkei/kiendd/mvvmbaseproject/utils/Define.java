package com.rikkei.kiendd.mvvmbaseproject.utils;

public class Define {

    public static final String PREF_FILE_NAME = "oab_pref";
    public static final String REALM_NAME = "oab_database";

    public static final long DEFAULT_TIMEOUT = 60L;

    public static class Api {
        public static class Http {
            public static final int RESPONSE_CODE_ACCESS_TOKEN_EXPIRED = 403;
        }
    }

    public static class Bus {
        public static final String ACCESS_TOKEN_EXPIRED = "ACCESS_TOKEN_EXPIRED";
    }
}
