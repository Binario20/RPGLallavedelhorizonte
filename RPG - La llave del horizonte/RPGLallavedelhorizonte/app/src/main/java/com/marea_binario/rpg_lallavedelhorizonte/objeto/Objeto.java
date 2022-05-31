package com.marea_binario.rpg_lallavedelhorizonte.objeto;

public class Objeto {
    private String nombre, descripcion, tipo;
    private int id, img_id;
    private Integer obj1_id, obj2_id;

    public Objeto(String nombre, String descripcion, String tipo, int id, int img_id, Integer obj1_id, Integer obj2_id) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.id = id;
        this.img_id = img_id;
        this.obj1_id = obj1_id;
        this.obj2_id = obj2_id;
    }

    @Override
    public String toString() {
        return "Objeto{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipo='" + tipo + '\'' +
                ", id=" + id +
                ", img_id=" + img_id +
                ", obj1_id=" + obj1_id +
                ", obj2_id=" + obj2_id +
                '}';
    }
}
