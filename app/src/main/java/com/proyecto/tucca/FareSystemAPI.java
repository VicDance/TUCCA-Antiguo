package com.proyecto.tucca;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FareSystemAPI {
    @GET("zonas")
    Call<FareSystemList> getFareSystemList();

    @GET("municipios")
    Call<CityList> getCityList();

    @GET("municipios/{id}/nucleos")
    Call<CityList> getCentreList(@Path("id") long idNucleo);
}
