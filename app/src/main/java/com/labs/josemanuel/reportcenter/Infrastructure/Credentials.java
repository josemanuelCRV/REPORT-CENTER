package com.labs.josemanuel.reportcenter.Infrastructure;

/**
 * Created by Miguel on 5/26/2016.
 */
public class Credentials {
    public static String userId;
    public static String csrfToken;

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        Credentials.userId = userId;
    }

    public static String getCsrfToken() {
        return csrfToken;
    }

    public static void setCsrfToken(String csrfToken) {
        Credentials.csrfToken = csrfToken;
    }
}

