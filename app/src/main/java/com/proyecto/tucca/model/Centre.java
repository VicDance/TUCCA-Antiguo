package com.proyecto.tucca.model;

import com.google.gson.annotations.SerializedName;

public class Centre {
    @SerializedName("idNucleo")
    private int idNucleo;
    @SerializedName("idMunicipio")
    private int idMunicipio;
    private char idZona;
    @SerializedName("nombre")
    private String nombreNucleo;

    public Centre(){

    }

    public Centre(int idNucleo, int idMunicipio, char idZona, String nombreNucleo){
        this.idNucleo = idNucleo;
        this.idMunicipio = idMunicipio;
        this.idZona = idZona;
        this.nombreNucleo = nombreNucleo;
    }

    public Centre(String nombreNucleo){
        this.nombreNucleo = nombreNucleo;
    }

    public int getIdNucleo() {
        return idNucleo;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public String getNombreNucleo() {
        return nombreNucleo;
    }
}
