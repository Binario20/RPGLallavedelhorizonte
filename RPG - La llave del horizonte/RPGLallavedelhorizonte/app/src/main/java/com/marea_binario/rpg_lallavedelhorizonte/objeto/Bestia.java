package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;

public class Bestia extends MrWorldwide{

    private int daño, vida, velocidad;
    private Integer experiencia;
    private String descripcion, tipo, clasificacion, tamaño, clasificacion_adicional, resitencia, vulnerabilidad, extras;
    private boolean montura;
    private String tipoInterno = Data.BESTIARIO;

    public Bestia(int id, int img_id, int daño, int vida, int velocidad, Integer experiencia, String nombre, String descripcion,
                   String tipo, String clasificacion, String tamaño, String clasificacion_adicional, String resitencia,
                   String vulnerabilidad, String extras, boolean montura) {
        super(id, img_id, nombre);
        this.daño = daño;
        this.vida = vida;
        this.velocidad = velocidad;
        this.experiencia = experiencia;
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

    public int getDaño() {
        return daño;
    }

    public int getVida() {
        return vida;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public Integer getExperiencia() {
        return experiencia;
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

