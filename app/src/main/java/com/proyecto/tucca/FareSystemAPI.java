package com.proyecto.tucca;

import com.proyecto.tucca.model.CityList;
import com.proyecto.tucca.model.FareSystemList;
import com.proyecto.tucca.model.LineList;

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

    /*@GET("nucleos/{id}/lineas")
    Call<LineList> getLineList(@Path("id") long idLinea);*/

    @GET("horarios_lineas?dia=&frecuencia=&lang=ES&linea={id}&mes=")
    Call<LineList> getLineList(@Path("id") long idLinea);
}
