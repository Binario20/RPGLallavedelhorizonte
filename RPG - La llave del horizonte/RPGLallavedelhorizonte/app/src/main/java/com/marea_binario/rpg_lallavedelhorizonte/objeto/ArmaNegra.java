package com.marea_binario.rpg_lallavedelhorizonte.objeto;

public class ArmaNegra extends Arma {

    private String campo2;
    private Integer requisito2, daño;

    public ArmaNegra(String nombre, String subtipo, String campo1, String campo2, String ataque, Integer daño, String rango, String descripcion, Integer id_arma, Integer requisito, Integer requisito2, Integer normal, Integer imagen_id, Integer objeto_principal, Integer objeto_secundario) {
        super(nombre, subtipo, campo1, ataque, rango, descripcion, id_arma, requisito, normal, imagen_id, objeto_principal, objeto_secundario);
        this.daño = daño;
        this.requisito2 = requisito2;
        this.campo2 = campo2;
    }

    public String getCampo2() {
        return campo2;
    }

    public Integer getRequisito2() {
        return requisito2;
    }

    public Integer getDaño() {
        return daño;
    }
}
