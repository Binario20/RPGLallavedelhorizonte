package com.marea_binario.rpg_lallavedelhorizonte.objeto;


//padre de items
public class MrWorldwide {
    private int id;
    private Integer img_id;
    private String nombre;

    public MrWorldwide(int id, Integer img_id, String nombre) {
        this.id = id;
        this.img_id = img_id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getImg_id() {
        return img_id;
    }

    public void setImg_id(Integer img_id) {
        this.img_id = img_id;
    }
}
