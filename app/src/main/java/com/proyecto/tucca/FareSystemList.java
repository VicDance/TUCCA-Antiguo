package com.proyecto.tucca;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.http.GET;

public class FareSystemList {
    @SerializedName("zonas")
    private List<FareSystem> fareSystems;

    public List<FareSystem> getFareSystems() {
        return fareSystems;
    }
}
