package com.marea_binario.rpg_lallavedelhorizonte.objeto;

public class Objetos {
    private String nombre, descripcion;
    private int id, img_id, obj1_id, obj2_id;

    public Objetos(String nombre, String descripcion, int id, int img_id, int obj1_id, int obj2_id) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id = id;
        this.img_id = img_id;
        this.obj1_id = obj1_id;
        this.obj2_id = obj2_id;
    }

}
