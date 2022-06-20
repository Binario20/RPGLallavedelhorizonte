package com.marea_binario.rpg_lallavedelhorizonte.objeto;

public class Objeto extends MrWorldwide{
    private String descripcion, tipo;
    private Integer obj1_id, obj2_id;

    public Objeto(String nombre, String descripcion, String tipo, int id, int img_id, Integer obj1_id, Integer obj2_id) {
        super(id, img_id, nombre);
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.obj1_id = obj1_id;
        this.obj2_id = obj2_id;
    }

    @Override
    public String toString() {
        return "Objeto{" +
                "nombre='" + this.getNombre() + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipo='" + tipo + '\'' +
                ", id=" + this.getId() +
                ", img_id=" + this.getImg_id() +
                ", obj1_id=" + obj1_id +
                ", obj2_id=" + obj2_id +
                '}';
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getObj1_id() {
        return obj1_id;
    }

    public Integer getObj2_id() {
        return obj2_id;
    }
}
