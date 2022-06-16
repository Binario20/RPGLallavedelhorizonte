package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import android.os.Bundle;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;

public class Bestia {

    private int id, img_id, daño, vida, valocidad;
    private Integer experiencia;
    private String nobre, descripcion, tipo, clasificacion, tamaño, clasificacion_adicional, resitencia, vulnerabilidad, extras;
    private boolean montura;
    private String tipoInterno = Data.BESTIARIO;

    public Bestia(int id, int img_id, int daño, int vida, int valocidad, Integer experiencia, String nobre, String descripcion,
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

    public int getId() {
        return id;
    }

    public int getImg_id() {
        return img_id;
    }

    public int getDaño() {
        return daño;
    }

    public int getVida() {
        return vida;
    }

    public int getValocidad() {
        return valocidad;
    }

    public Integer getExperiencia() {
        return experiencia;
    }

    public String getNobre() {
        return nobre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public String getTamaño() {
        return tamaño;
    }

    public String getClasificacion_adicional() {
        return clasificacion_adicional;
    }

    public String getResitencia() {
        return resitencia;
    }

    public String getVulnerabilidad() {
        return vulnerabilidad;
    }

    public String getExtras() {
        return extras;
    }

    public boolean isMontura() {
        return montura;
    }
}

