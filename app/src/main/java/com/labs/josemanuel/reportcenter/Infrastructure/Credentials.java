package com.labs.josemanuel.reportcenter.Infrastructure;

/**
 * Created by Miguel on 5/26/2016.
 */
public class Credentials {
    public static String Authorization;
    public static String X_CSRF_Token;

    public static String getAuthorization() {
        return Authorization;
    }

    public static void setAuthorization(String authorization) {
        Authorization = "Basic "+authorization;
    }

    public static String getX_CSRF_Token() {
        return X_CSRF_Token;
    }

    public static void setX_CSRF_Token(String x_CSRF_Token) {
        X_CSRF_Token = x_CSRF_Token;
    }
}

