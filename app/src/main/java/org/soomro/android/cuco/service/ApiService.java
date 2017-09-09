package org.soomro.android.cuco.service;

import org.soomro.android.cuco.model.Currency;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Arshay on 8/20/2017.
 */

public interface ApiService {
    @GET("get_currency_rates.php")
    Call<List<Currency>> getRates();
}
