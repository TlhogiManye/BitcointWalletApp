package com.example.bitcoinwalletapp.network;

import com.example.bitcoinwalletapp.model.ConvertDataResponse;
import com.example.bitcoinwalletapp.model.FluctuationDataResponse;
import com.example.bitcoinwalletapp.model.LatestDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author tlhogi-manye
 *
 * Interface that will carry all the endpoints for this particular application
 */

public interface NetworkService {

    // GET /convert
    @GET("convert")
    Call<ConvertDataResponse> getConvertData(@Query("to") String toCurrency, @Query("from") String fromCurrency, @Query("amount") Double amount );

    // GET /fluctuation
    @GET("fluctuation")
    Call<FluctuationDataResponse> getFluctuationData();

    // GET /latest
    @GET("latest")
    Call<LatestDataResponse> getLatestData(@Query("symbols") String symbols,@Query("base")String base);
}

