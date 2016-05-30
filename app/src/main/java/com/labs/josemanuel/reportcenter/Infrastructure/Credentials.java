package com.labs.josemanuel.reportcenter.Infrastructure;

/**
 * Created by Miguel on 5/26/2016.
 */
public class Credentials {
    public static String Authorization;
    public static String X_CRSF_Token;

    public static String getAuthorization() {
        return Authorization;
    }

    public static void setAuthorization(String authorization) {
        Authorization = authorization;
    }

    public static String getX_CRSF_Token() {
        return X_CRSF_Token;
    }

    public static void setX_CRSF_Token(String x_CRSF_Token) {
        X_CRSF_Token = x_CRSF_Token;
    }
}

