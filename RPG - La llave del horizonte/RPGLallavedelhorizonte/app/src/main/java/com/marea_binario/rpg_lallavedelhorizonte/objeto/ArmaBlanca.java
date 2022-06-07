package com.marea_binario.rpg_lallavedelhorizonte.objeto;

public class ArmaBlanca extends Arma{

    private String operacion, suma1_campo, suma2_campo;
    private Integer suma1, suma2;

    public ArmaBlanca(String nombre, String subtipo, String campo, String ataque, String operacion, String suma1_campo, String suma2_campo, Integer suma1, Integer suma2, String rango, String descripcion, Integer id_arma, Integer requisito, Integer normal, Integer imagen_id, Integer objeto_principal, Integer objeto_secundario) {
        super(nombre, subtipo, campo, ataque, rango, descripcion, id_arma, requisito, normal, imagen_id, objeto_principal, objeto_secundario);
        this.operacion = operacion;
        this.suma1_campo = suma1_campo;
        this.suma2_campo = suma2_campo;
        this.suma1 = suma1;
        this.suma2 = suma2;
    }
}
