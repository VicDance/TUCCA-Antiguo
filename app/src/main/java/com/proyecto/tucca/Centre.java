package com.proyecto.tucca;

import com.google.gson.annotations.SerializedName;

public class Centre {
    @SerializedName("idNucleo")
    private int idNucleo;
    @SerializedName("idMunicipio")
    private int idMunicipio;
    @SerializedName("nombre")
    private String nombreNucleo;

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
