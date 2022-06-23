package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import java.util.ArrayList;

public class Magia extends MrWorldwide{
    private int id_lengua, requisitos;
    private String tipo, descripcion, lengua;
    private ArrayList<Hechizo> hechizos = new ArrayList<>();

    public Magia(int id, int id_lengua, int requisitos, Integer imagen_id, String nombre, String tipo, String descripcion, String lengua) {
        super(id, imagen_id, nombre);
        this.id_lengua = id_lengua;
        this.requisitos = requisitos;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.lengua = lengua;
    }

    public int getRequisitos() {
        return requisitos;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLengua() {
        return lengua;
    }

    public ArrayList<Hechizo> getHechizos() {
        return hechizos;
    }

    public void addHechizo(Hechizo hechizo){
        hechizos.add(hechizo);
    }

}
