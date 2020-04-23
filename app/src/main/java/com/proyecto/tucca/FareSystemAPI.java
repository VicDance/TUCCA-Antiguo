package com.proyecto.tucca;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FareSystemAPI {
    @GET("zonas")
    Call<FareSystemList> getFareSystemList();
}
