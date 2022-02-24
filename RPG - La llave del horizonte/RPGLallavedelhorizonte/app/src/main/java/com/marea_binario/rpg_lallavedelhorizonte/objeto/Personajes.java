package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import androidx.annotation.NonNull;

public class Personajes {

    private final int maxPuntos = 25;

    private String nombre;
    private String procedencia;
    private String especie;
    private int edad;
    private float altura;
    private float peso;
    private String sexo;
    private String preferencia1;
    private String preferencia2 = "";
    private String preferencia3 = "";
    private int vitalidad;
    private int resistencia;
    private int fuerza;
    private int velocidad;
    private int inteligencia;
    private int punteria;
    private int magia;
    private int destreza;
    private String objetoInicial = "";
    private String personalidad = "";
    private String habilidades = "";
    private String fisico = "";

    public Personajes(@NonNull String nombre, @NonNull String procedencia, @NonNull String especie, int edad,
                      float altura, float peso, @NonNull String sexo, @NonNull String preferencia1,
                      String preferencia2, String preferencia3, int vitalidad, int resistencia, int fuerza,
                      int velocidad, int inteligencia, int punteria, int magia, String objetoInicial,
                      String personalidad, String habilidades, String fisico) throws Exception {
        if(nombre.trim().equals("")){
            new Exception();
        }else{
            this.nombre = nombre;
        }
        this.procedencia = procedencia;
        this.especie = especie;
        if (edad < 2) {
            new Exception();
        } else {
            this.edad = edad;
        }
        if (altura < 0.15) {
            new Exception();
        } else {
            this.altura = altura;
        }
        if (peso < 0.3) {
            new Exception();
        } else {
            this.peso = peso;
        }
        this.sexo = sexo;
        this.preferencia1 = preferencia1;
        this.preferencia2 = preferencia2;
        this.preferencia3 = preferencia3;
        if (vitalidad < 1) {
            new Exception();
        } else {
            this.vitalidad = vitalidad;
        }
        if (resistencia < 1) {
            new Exception();
        } else {
            this.resistencia = resistencia;
        }
        if (fuerza < 1) {
            new Exception();
        } else {
            this.fuerza = fuerza;
        }
        if (velocidad < 1) {
            new Exception();
        } else {
            this.velocidad = velocidad;
        }
        if (inteligencia < 1) {
            new Exception();
        } else {
            this.inteligencia = inteligencia;
        }
        if (punteria < 1) {
            new Exception();
        } else {
            this.punteria = punteria;
        }
        if (magia < 1) {
            new Exception();
        } else {
            this.magia = magia;
        }
        int sumaPuntos = vitalidad + resistencia + fuerza + velocidad + inteligencia + punteria + magia;
        if (sumaPuntos > maxPuntos) {
            new Exception();
        }
        this.destreza = (velocidad + punteria)/2;
        this.objetoInicial = objetoInicial;
        this.personalidad = personalidad;
        this.habilidades = habilidades;
        this.fisico = fisico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPreferencia1() {
        return preferencia1;
    }

    public void setPreferencia1(String preferencia1) {
        this.preferencia1 = preferencia1;
    }

    public String getPreferencia2() {
        return preferencia2;
    }

    public void setPreferencia2(String preferencia2) {
        this.preferencia2 = preferencia2;
    }

    public String getPreferencia3() {
        return preferencia3;
    }

    public void setPreferencia3(String preferencia3) {
        this.preferencia3 = preferencia3;
    }

    public int getVitalidad() {
        return vitalidad;
    }

    public void setVitalidad(int vitalidad) {
        this.vitalidad = vitalidad;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public int getPunteria() {
        return punteria;
    }

    public void setPunteria(int punteria) {
        this.punteria = punteria;
    }

    public int getMagia() {
        return magia;
    }

    public void setMagia(int magia) {
        this.magia = magia;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public String getObjetoInicial() {
        return objetoInicial;
    }

    public void setObjetoInicial(String objetoInicial) {
        this.objetoInicial = objetoInicial;
    }

    public String getPersonalidad() {
        return personalidad;
    }

    public void setPersonalidad(String personalidad) {
        this.personalidad = personalidad;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public String getFisico() {
        return fisico;
    }

    public void setFisico(String fisico) {
        this.fisico = fisico;
    }
}
