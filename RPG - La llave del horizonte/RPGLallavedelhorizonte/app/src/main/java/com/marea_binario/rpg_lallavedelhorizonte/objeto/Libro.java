package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import java.util.ArrayList;

public class Libro extends MrWorldwide{
    private int id_lengua, requisitos;
    private String tipo, descripcion, lengua;
    private ArrayList<Hechizo> hechizos = new ArrayList<>();

    public Libro(int id, int id_lengua, int requisitos, Integer imagen_id, String nombre, String tipo, String descripcion, String lengua) {
        super(id, imagen_id, nombre);
        this.id_lengua = id_lengua;
        this.requisitos = requisitos;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.lengua = lengua;
    }

    public void addHechizo(Hechizo hechizo){
        hechizos.add(hechizo);
    }

}
