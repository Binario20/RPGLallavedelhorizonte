package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import java.util.ArrayList;

public class Libro {
    private int id, id_lengua, requisitos;
    private Integer imagen_id;
    private String nombre, tipo, descripcion, lengua;
    private ArrayList<Hechizo> hechizos = new ArrayList<>();

    public Libro(int id, int id_lengua, int requisitos, Integer imagen_id, String nombre, String tipo, String descripcion, String lengua) {
        this.id = id;
        this.id_lengua = id_lengua;
        this.requisitos = requisitos;
        this.imagen_id = imagen_id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.lengua = lengua;
    }

    public void addHechizo(Hechizo hechizo){
        hechizos.add(hechizo);
    }

    public int getId() {
        return id;
    }
}
