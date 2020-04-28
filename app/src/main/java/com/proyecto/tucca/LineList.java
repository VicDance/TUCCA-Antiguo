package com.proyecto.tucca;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LineList {
    @SerializedName("lineas")
    private List<Line> lines;

    private List<Stop> stops;

    public List<Line> getLines() {
        return lines;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }
}
