package com.proyecto.tucca.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.http.GET;

public class City {
    @SerializedName("idMunicipio")
    private int idMunicipio;
    @SerializedName("datos")
    private String nombreMunicipio;

    public City(int idMunicipio, String nombreMunicipio){
        this.idMunicipio = idMunicipio;
        this.nombreMunicipio = nombreMunicipio;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }
}
