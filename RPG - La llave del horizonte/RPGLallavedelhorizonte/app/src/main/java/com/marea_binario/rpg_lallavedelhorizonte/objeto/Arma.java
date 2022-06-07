package com.marea_binario.rpg_lallavedelhorizonte.objeto;

public class Arma {
    private String nombre, subtipo, campo, ataque, rango, descripcion;
    private Integer id_arma, requisito, normal, imagen_id, objeto_principal, objeto_secundario;

    public Arma(String nombre, String subtipo, String campo, String ataque, String rango, String descripcion, Integer id_arma, Integer requisito, Integer normal, Integer imagen_id, Integer objeto_principal, Integer objeto_secundario) {
        this.nombre = nombre;
        this.subtipo = subtipo;
        this.campo = campo;
        this.ataque = ataque;
        this.rango = rango;
        this.descripcion = descripcion;
        this.id_arma = id_arma;
        this.requisito = requisito;
        this.normal = normal;
        this.imagen_id = imagen_id;
        this.objeto_principal = objeto_principal;
        this.objeto_secundario = objeto_secundario;
    }
}
