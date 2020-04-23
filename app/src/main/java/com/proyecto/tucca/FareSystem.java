package com.proyecto.tucca;

import com.google.gson.annotations.SerializedName;

public class FareSystem {
    @SerializedName("idZona")
    private String idZone;
    @SerializedName("nombre")
    private String nameZone;
    @SerializedName("color")
    private String colorZone;

    public String getIdZone() {
        return idZone;
    }

    public String getNameZone() {
        return nameZone;
    }

    public String getColorZone() {
        return colorZone;
    }
}
