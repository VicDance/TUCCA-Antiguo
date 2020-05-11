package com.proyecto.tucca.model;

public class City {
    private int idMunicipio;
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
