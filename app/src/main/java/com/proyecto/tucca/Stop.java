package com.proyecto.tucca;

import com.google.gson.annotations.SerializedName;

public class Stop {
    @SerializedName("idParada")
    private int idParada;

    @SerializedName("idNucleo")
    private int idNucleo;

    @SerializedName("nombre")
    private String nombre;
}
