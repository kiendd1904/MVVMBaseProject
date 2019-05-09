package com.rikkei.kiendd.mvvmbaseproject.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import androidx.annotation.StringDef;

public class Define {

    public static final String PREF_FILE_NAME = "oab_pref";
    public static final String REALM_NAME = "oab_database";

    public static final long DEFAULT_TIMEOUT = 60L;
    public static final long CLICK_TIME_INTERVAL = 300L;

    public static class Api {

        public static final String CONTENT_TYPE = "Content-Type: application/json";
        public static final String LOGIN_URL = "app_api/v1/auth/user";


        public static class BaseResponse {
            public static final String SUCCESS = "success";
            public static final String DATA = "data";
            public static final String PAGE = "page";
            public static final String ERROR = "error";
            public static final String ERROR_CODE = "error_code";
            public static final String ERROR_MESSAGE = "error_message";
        }

        public static class Error {
            // Declare variable name by function description
            public static final String NO_NETWORK_CONNECTION = "NO_NETWORK_CONNECTION";
            public static final String TIME_OUT = "TIME_OUT";
            public static final String UNKNOWN = "UNKNOWN";
        }

        public static class Key {
            public static final String AUTHORIZATION = "Authorization";
            public static final String LOGIN_NAME = "login_name";
            public static final String PASSWORD = "password";
            public static final String TOKEN = "token";
        }

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

    public static class Intent {
        public static final String REPO_OWNER = "repo_owner";
        public static final String REPO_NAME = "repo_name";
    }

    public static class PreferenceKey {
        public static final String IS_FIRST_LAUNCH = "isHaveMasterData";
        public static final String USER_INFO = "userInfo";
        public static final String MASTER_DATA = "masterData";
    }

    public static class ViewState {
        public static final int GO_DETAIL = 0;
        public static final int GO_HOME = 1;
    }
}
