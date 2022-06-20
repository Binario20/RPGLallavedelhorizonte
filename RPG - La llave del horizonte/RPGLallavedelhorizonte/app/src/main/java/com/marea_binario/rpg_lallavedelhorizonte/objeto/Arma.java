package com.marea_binario.rpg_lallavedelhorizonte.objeto;

public class Arma extends MrWorldwide {
    private String subtipo, campo, ataque, rango, descripcion;
    private Integer requisito, normal, objeto_principal, objeto_secundario;

    public Arma(String nombre, String subtipo, String campo, String ataque, String rango, String descripcion, Integer id_arma, Integer requisito, Integer normal, Integer imagen_id, Integer objeto_principal, Integer objeto_secundario) {
        super(id_arma, imagen_id, nombre);
        this.subtipo = subtipo;
        this.campo = campo;
        this.ataque = ataque;
        this.rango = rango;
        this.descripcion = descripcion;
        this.requisito = requisito;
        this.normal = normal;
        this.objeto_principal = objeto_principal;
        this.objeto_secundario = objeto_secundario;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(String subtipo) {
        this.subtipo = subtipo;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getAtaque() {
        return ataque;
    }

    public void setAtaque(String ataque) {
        this.ataque = ataque;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getRequisito() {
        return requisito;
    }

    public void setRequisito(Integer requisito) {
        this.requisito = requisito;
    }

    public Integer getNormal() {
        return normal;
    }

    public void setNormal(Integer normal) {
        this.normal = normal;
    }

    public Integer getObjeto_principal() {
        return objeto_principal;
    }

    public void setObjeto_principal(Integer objeto_principal) {
        this.objeto_principal = objeto_principal;
    }

    public Integer getObjeto_secundario() {
        return objeto_secundario;
    }

    public void setObjeto_secundario(Integer objeto_secundario) {
        this.objeto_secundario = objeto_secundario;
    }
}
