package com.proyecto.tucca;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.http.GET;

public class City {
    @SerializedName("idMunicipio")
    private long idMunicipio;
    @SerializedName("datos")
    private String nombreMunicipio;

    public City(long idMunicipio, String nombreMunicipio){
        this.idMunicipio = idMunicipio;
        this.nombreMunicipio = nombreMunicipio;
    }

    public long getIdMunicipio() {
        return idMunicipio;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }
}
