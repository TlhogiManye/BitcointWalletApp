package com.example.bitcoinwalletapp.network;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author tlhogi-manye
 * @date 28/02/2024
 *
 * Network retrofit client to assist in consuming the API
 */
public class NetworkClient {
    private static final String BASE_URL = "https://api.apilayer.com/fixer/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(String authToken) {

        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            if (authToken != null && !authToken.isEmpty()) {
                // Add interceptor to include the token in the header
                httpClient.addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request.Builder requestBuilder = original.newBuilder()
                                .header("apikey", authToken)
                                .method(original.method(), original.body());
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                });
            }

            retrofit = new Retrofit.Builder()
                    .client(getOkHttp(httpClient))  // Pass the httpClient here
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * Method used for logging API calls for easy debugging
     * @return
     */
    private static OkHttpClient getOkHttp(OkHttpClient.Builder httpClient) {
        return httpClient
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }



    public static NetworkService getNetworkService(String authToken) {
        return getRetrofitInstance(authToken).create(NetworkService.class);
    }
}
