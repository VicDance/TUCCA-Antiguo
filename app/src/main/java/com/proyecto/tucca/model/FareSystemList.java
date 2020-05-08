package com.proyecto.tucca.model;

import com.google.gson.annotations.SerializedName;
import com.proyecto.tucca.model.FareSystem;

import java.util.List;

public class FareSystemList {
    @SerializedName("zonas")
    private List<FareSystem> fareSystems;

    public List<FareSystem> getFareSystems() {
        return fareSystems;
    }
}
