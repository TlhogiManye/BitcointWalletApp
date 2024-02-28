package com.example.bitcoinwalletapp.network;

import com.example.bitcoinwalletapp.models.ConvertDataResponse;
import com.example.bitcoinwalletapp.models.FluctuationDataResponse;
import com.example.bitcoinwalletapp.models.LatestDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author tlhogi-manye
 *
 * Interfact that will carry all the endpoints for this particular application
 */

public interface NetworkService {

    // GET /convert
    @GET("/convert")
    Call<ConvertDataResponse> getConvertData(@Query("to") String toCurrency, @Query("from") String fromCurrency, @Query("amount") String amount );

    // GET /fluctuation
    @GET("/fluctuation")
    Call<FluctuationDataResponse> getFluctuationData();

    // GET /latest
    @GET("/latest")
    Call<LatestDataResponse> getLatestData();
}

