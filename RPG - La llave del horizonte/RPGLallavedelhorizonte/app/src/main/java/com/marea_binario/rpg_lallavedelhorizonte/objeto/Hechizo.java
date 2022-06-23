package com.marea_binario.rpg_lallavedelhorizonte.objeto;

public class Hechizo {
    private int id, id_libro;
    private String hechizo, descripcion, libro;

    public Hechizo(int id, int id_libro, String hechizo, String descripcion, String libro) {
        this.id = id;
        this.id_libro = id_libro;
        this.hechizo = hechizo;
        this.descripcion = descripcion;
        this.libro = libro;
    }

    public String getHechizo() {
        return hechizo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
