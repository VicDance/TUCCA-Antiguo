package com.proyecto.tucca.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StopList {

    @SerializedName("paradas")
    private List<Stop> stops;

    public List<Stop> getStops() {
        return stops;
    }
}
