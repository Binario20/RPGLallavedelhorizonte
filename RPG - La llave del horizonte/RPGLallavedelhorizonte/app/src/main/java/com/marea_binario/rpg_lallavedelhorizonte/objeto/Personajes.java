package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import androidx.annotation.NonNull;

import org.json.JSONObject;

public class Personajes {

    private final int maxPuntos = 25;

    private String nombre;
    private String procedencia;
    private int id_procedencia;
    private String especie;
    private int id_especie;
    private int edad;
    private float altura;
    private float peso;
    private String sexo;
    private String clase;
    private int id_clase;
    private Integer id_lengua1;
    private int id_lengua2;
    private int vitalidad;
    private int resistencia;
    private int fuerza;
    private int velocidad;
    private int inteligencia;
    private int punteria;
    private int magia;
    private int destreza;
    private String personalidad;
    private String fisico;
    private String habilidades = null;
    private String lengua1, lengua2 = null;

    public Personajes(@NonNull String nombre, int id_procedencia, int id_especie, int edad,
                      float altura, float peso, @NonNull String sexo, int id_clase, Integer id_lengua,
                      int vitalidad, int resistencia, int fuerza, int velocidad, int inteligencia,
                      int punteria, int magia, String personalidad, String fisico) throws Exception {
        if(nombre.trim().equals("")){
            new Exception();
        }else{
            this.nombre = nombre;
        }
        this.id_procedencia = id_procedencia;
        this.id_especie = id_especie;
        if (edad < 14) {
            new Exception();
        } else {
            this.edad = edad;
        }
        if (altura < 0.5) {
            new Exception();
        } else {
            this.altura = altura;
        }
        if (peso < 25) {
            new Exception();
        } else {
            this.peso = peso;
        }
        this.sexo = sexo;
        this.id_clase = id_clase;
        this.id_lengua1 = id_lengua;
        if (vitalidad < 1 || vitalidad > 10) {
            new Exception();
        } else {
            this.vitalidad = vitalidad;
        }
        if (resistencia < 1 || resistencia > 10) {
            new Exception();
        } else {
            this.resistencia = resistencia;
        }
        if (fuerza < 1 || fuerza > 10) {
            new Exception();
        } else {
            this.fuerza = fuerza;
        }
        if (velocidad < 1 || velocidad > 10) {
            new Exception();
        } else {
            this.velocidad = velocidad;
        }
        if (inteligencia < 1 || inteligencia > 10) {
            new Exception();
        } else {
            this.inteligencia = inteligencia;
        }
        if (punteria < 0 || punteria > 10) {
            new Exception();
        } else {
            this.punteria = punteria;
        }
        if (magia < 0 || magia > 10) {
            new Exception();
        } else {
            this.magia = magia;
        }
        int sumaPuntos = vitalidad + resistencia + fuerza + velocidad + inteligencia + punteria + magia;
        if (sumaPuntos > maxPuntos) {
            new Exception();
        }
        this.destreza = (velocidad + punteria)/2;
        this.personalidad = personalidad;
        this.fisico = fisico;
    }

    public JSONObject getInputJSON() {
        JSONObject personaje = new JSONObject();
        try {
            personaje.put("nombre", nombre);
            personaje.put("id_procedencia", id_procedencia);
            personaje.put("id_especie", id_especie);
            personaje.put("edad", edad);
            personaje.put("altura", String.valueOf(altura));
            personaje.put("peso", String.valueOf(peso));
            personaje.put("sexo", sexo);
            personaje.put("id_clase", id_clase);
            personaje.put("id_lengua", id_lengua1);
            personaje.put("vitalidad", vitalidad);
            personaje.put("resistencia", resistencia);
            personaje.put("fuerza", fuerza);
            personaje.put("velocidad", velocidad);
            personaje.put("inteligencia", inteligencia);
            personaje.put("punteria", punteria);
            personaje.put("magia", magia);
            personaje.put("destreza", destreza);
            personaje.put("personalidad", personalidad);
            personaje.put("fisico", fisico);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //public int getIdProcedencia() { return id_procedencia; }

    public void setProcedencia(String procedencia) { this.procedencia = procedencia; }

    public String getProcedencia() { return procedencia; }

    //public int getIdEspecie() { return id_especie; }

    public void setEspecie(String especie) { this.especie = especie; }

    public String getEspecie() { return especie; }

    public int getEdad() { return edad; }

    public float getAltura() { return altura; }

    public float getPeso() { return peso; }

    public String getSexo() { return sexo; }

    //public int getIdClase() { return id_clase; }

    public void setClase(String clase) { this.clase = clase; }

    public String getClase() { return clase; }

    //public int getIdLengua1() { return id_lengua1; }

    public void setLengua1(String lengua1) { this.lengua1 = lengua1; }

    public String getLengua1() { return lengua1; }

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

    public String getPersonalidad() {
        return personalidad;
    }

    public void setPersonalidad(String personalidad) {
        this.personalidad = personalidad;
    }

    public String getFisico() {
        return fisico;
    }

    public void setFisico(String fisico) {
        this.fisico = fisico;
    }

    public String getHabilidades() { return habilidades; }

    public void setHabilidades(String habilidades) { this.habilidades = habilidades; }

    public void setLengua2(int id_lengua2, String lengua2) {
        this.id_lengua2 = id_lengua2;
        this.lengua2 = lengua2;
    }

    public int getIdLengua2() { return id_lengua2; }

    public String getLengua2() { return lengua2; }

    public void setEstadisticas(int vitalidad, int resistencia, int fuerza, int velocidad,
                                int inteligencia, int punteria, int magia) {
        int sumaPuntos = vitalidad + resistencia + fuerza + velocidad + inteligencia + punteria + magia;
        if (sumaPuntos > maxPuntos) {
            new Exception();
        }
        this.destreza = (velocidad + punteria)/2;
        setVitalidad(vitalidad);
        setResistencia(resistencia);
        setFuerza(fuerza);
        setVelocidad(velocidad);
        setInteligencia(inteligencia);
        setPunteria(punteria);
        setMagia(magia);
    }
}
