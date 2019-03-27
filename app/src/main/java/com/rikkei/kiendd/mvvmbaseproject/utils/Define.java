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

    public class ResponseStatus {
        public static final int LOADING = 1;
        public static final int SUCCESS = 2;
        public static final int ERROR = 0;
    }

    public class Database {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "app_database";

        public class User {
            public static final String TABLE_NAME = "dtb_user";
            public static final String ID = "id";
            public static final String LOG_IN = "login";
            public static final String AVATAR_URL = "avatar_url";
            public static final String NAME = "name";
            public static final String COMPANY = "company";
            public static final String EMAIL = "email";
            public static final String LOCATION = "location";
        }

        public class Repo {
            public static final String TABLE_NAME = "dtb_repo";
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String FULL_NAME = "full_name";
            public static final String DESCRIPTION = "description";
            public static final String CONTRIBUTORS_URL = "contributors_url";
        }
    }
}
