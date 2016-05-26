package com.labs.josemanuel.reportcenter.Utils;

/**
 * Created by JMC on 26/05/2016.
 */
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


/**
 * Created by Usuario on 25/05/2016.
 */
public class NukeSSLCerts {
    /**
     * Esta clase no es válida para producción, porque no está validando ningún certificado del servidor
     *
     * https://newfivefour.com/android-trust-all-ssl-certificates.html
     * */
    protected static final String TAG = "NukeSSLCerts";
    /**
     * permite lanzar las peticiones a través de SSL, sin verificar de ninguna manera el certificado proveniente del servidor.
     */
    public static void nuke() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            X509Certificate[] myTrustedAnchors = new X509Certificate[0];
                            return myTrustedAnchors;
                        }

                        @Override
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}

                        @Override
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception e) {
        }
    }
}
