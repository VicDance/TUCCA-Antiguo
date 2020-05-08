package com.proyecto.tucca.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityList {
    @SerializedName("municipios")
    private List<City> cities;

    @SerializedName("nucleos")
    private List<Centre> centreList;

    public List<City> getCities() {
        return cities;
    }

    public List<Centre> getCentreList() {
        return centreList;
    }
}
