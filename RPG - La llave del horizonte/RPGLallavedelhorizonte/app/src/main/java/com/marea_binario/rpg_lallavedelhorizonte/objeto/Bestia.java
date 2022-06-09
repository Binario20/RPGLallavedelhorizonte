package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import android.os.Bundle;

public class Bestia {

    private int id, img_id, daño, vida, valocidad, experiencia;
    private String nobre, descripcion, tipo, clasificacion, tamaño, clasificacion_adicional, resitencia, vulnerabilidad, extras;
    private boolean montura;

    public Bestia(int id, int img_id, int daño, int vida, int valocidad, int experiencia, String nobre, String descripcion,
                   String tipo, String clasificacion, String tamaño, String clasificacion_adicional, String resitencia,
                   String vulnerabilidad, String extras, boolean montura) {
        this.id = id;
        this.img_id = img_id;
        this.daño = daño;
        this.vida = vida;
        this.valocidad = valocidad;
        this.experiencia = experiencia;
        this.nobre = nobre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.clasificacion = clasificacion;
        this.tamaño = tamaño;
        this.clasificacion_adicional = clasificacion_adicional;
        this.resitencia = resitencia;
        this.vulnerabilidad = vulnerabilidad;
        this.extras = extras;
        this.montura = montura;
    }
}
