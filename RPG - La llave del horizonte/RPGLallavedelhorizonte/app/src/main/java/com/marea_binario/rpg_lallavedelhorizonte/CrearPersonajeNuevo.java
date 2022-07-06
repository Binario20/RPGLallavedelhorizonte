package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.Data.Utils;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Personajes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class CrearPersonajeNuevo extends AppCompatActivity {

    private EditText newPerNombreIn, newPerEdadIn, newPerAlturaIn, newPerPesoIn, newPerVitalidadIn,
            newPerResistenciaIn, newPerFuerzaIn, newPerVelocidadIn, newPerInteligenciaIn,
            newPerPunteriaIn, newPerMagiaIn, newPerPersonalidadIn, newPerFisicoIn;
    private Spinner newPerProcedenciaIn, newPerEspecieIn, newPerSexsoIn, newPerClaseIn;
    private LinearLayout oculto;
    private HashMap<String, String> listClases = new HashMap<>();
    private HashMap<String, String> listProcedencias = new HashMap<>();
    private HashMap<String, String> listEspecies = new HashMap<>();
    private HashMap<String, String> listLenguasEspecies = new HashMap<>();
    private HashMap<String, String> listLenguas = new HashMap<>();
    private Integer idPer, id_jugador;
    private boolean totOK = true;
    private final int NumeroAbsurdameteGrande = 999999999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaje_nuevo_by_ivan);
        initComponents();
    }

    private boolean verificarCampo(SuperText st, EditText et){
        int color = Color.TRANSPARENT;
        if (et.getText().toString().trim().equalsIgnoreCase("")){
            color = Color.RED;
            totOK = false;
        }
        st.setBackgroundColor(color);
        return color == Color.TRANSPARENT;
    }

    private boolean verificarCampoSpinner(SuperText st, Spinner sp, HashMap<String, String> lista){
        int color = Color.TRANSPARENT;
        if (lista.get(sp.getSelectedItem()).equalsIgnoreCase("")){
            color = Color.RED;
            totOK = false;
        }
        st.setBackgroundColor(color);
        return color == Color.TRANSPARENT;
    }

    private boolean verificarCampoConRango(SuperText st, EditText et, float min, float max){
        boolean beffore = totOK;
        int color = Color.RED;
        if(verificarCampo(st, et)){
            try{
                totOK = false;
                float num = Float.parseFloat(et.getText().toString().trim());
                if(min <= num && num <= max){
                    color = Color.TRANSPARENT;
                    totOK = beffore;
                }
                st.setBackgroundColor(color);
            }catch (Exception e){
                st.setBackgroundColor(color);
                totOK = false;
            }
        }
        return color == Color.TRANSPARENT;
    }

    private void initComponents() {
        newPerNombreIn = this.findViewById(R.id.newPerNombreIn);
        newPerProcedenciaIn = this.findViewById(R.id.newPerProcedenciaIn);
        newPerEspecieIn = this.findViewById(R.id.newPerEspecieIn);
        newPerEdadIn = this.findViewById(R.id.newPerEdadIn);
        newPerAlturaIn = this.findViewById(R.id.newPerAlturaIn);
        newPerPesoIn = this.findViewById(R.id.newPerPesoIn);
        newPerSexsoIn = this.findViewById(R.id.newPerSexsoIn);
        newPerClaseIn = this.findViewById(R.id.newPerClaseIn);
        newPerVitalidadIn = this.findViewById(R.id.newPerVitalidadIn);
        newPerFuerzaIn = this.findViewById(R.id.newPerFuerzaIn);
        newPerResistenciaIn = this.findViewById(R.id.newPerResistencia);
        newPerVelocidadIn = this.findViewById(R.id.newPerVelocidadIn);
        newPerInteligenciaIn = this.findViewById(R.id.newPerInteligenciaIn);
        newPerPunteriaIn = this.findViewById(R.id.newPerPunteriaIn);
        newPerMagiaIn = this.findViewById(R.id.newPerMagiaIn);
        newPerPersonalidadIn = this.findViewById(R.id.personalidadTxt);
        newPerFisicoIn = this.findViewById(R.id.fisicoTxt);


        getLists();
        setSpinners();

        oculto = this.findViewById(R.id.oculto);
        TextView caracteristicasEspeciales = this.findViewById(R.id.caracteristicasEspeciales);

        this.findViewById(R.id.saveNewPer).setOnClickListener(view -> {
            // Guardar personaje
            Personajes newPersonaje = savePersonaje();
            // Empezar a jugar
            if (totOK) {
                saveInServer(newPersonaje);
                Intent i = new Intent(this, PaginaPrincipal.class);
                i.putExtra("idPerso", idPer);
                i.putExtra("id_jugador", id_jugador);
                this.startActivity(i);
                finish();
            }
        });

        caracteristicasEspeciales.setOnClickListener(view -> {
            if (oculto.getVisibility() == View.GONE) {
                oculto.setVisibility(View.VISIBLE);
            } else {
                oculto.setVisibility(View.GONE);
            }
        });
    }



    private void getLists() {
        JSONObject listJ = Utils.getDataJSON("get/personaje/nuevo");
        try {
            JSONObject proce = listJ.getJSONObject("Procedencia");
            //Log.e("Spinners-P", String.valueOf(proce));
            Iterator<String> iterP = proce.keys();
            while (iterP.hasNext()) {
                JSONObject line = proce.getJSONObject(iterP.next());
                //Log.e("Spinners-P", String.valueOf(line));
                String key = line.getString("nombre");
                String value = line.getString("id");
                listProcedencias.put(key, value);
            }

            JSONObject espec = listJ.getJSONObject("Especie");
            //Log.e("Spinners-E", String.valueOf(espec));
            Iterator<String> iterE = proce.keys();
            while (iterE.hasNext()) {
                JSONObject line = espec.getJSONObject(iterE.next());
                String key = line.getString("nombre");
                String value = line.getString("id");
                String id_leng = line.getString("id_lengua");
                String leng = line.getString("id_lengua");
                listEspecies.put(key, value);
                listLenguasEspecies.put(value,id_leng);
                listLenguas.put(id_leng,leng);
            }

            JSONObject clase = listJ.getJSONObject("Clase");
            //Log.e("Spinners-C", String.valueOf(clase));
            Iterator<String> iterC = proce.keys();
            while (iterC.hasNext()) {
                JSONObject line = clase.getJSONObject(iterC.next());
                String key = line.getString("clase");
                String value = line.getString("id");
                listClases.put(key, value);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setSpinners() {
        // Spinner sexo
        ArrayAdapter<CharSequence> newPerSexsoAdapt = ArrayAdapter.createFromResource(this,
                R.array.newPerSexoSpin, android.R.layout.simple_spinner_item);
        newPerSexsoAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newPerSexsoIn.setAdapter(newPerSexsoAdapt);

        // Spinner procedencia
        String[] arrayP = listProcedencias.keySet().toArray(new String[0]);
        ArrayAdapter<String> newPerProceAdapt = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arrayP);
        newPerProceAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newPerProcedenciaIn.setAdapter(newPerProceAdapt);

        // Spinner especie
        String[] arrayE = listEspecies.keySet().toArray(new String[0]);
        ArrayAdapter<String> newPerEspAdapt = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arrayE);
        newPerEspAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newPerEspecieIn.setAdapter(newPerEspAdapt);

        // Spinner clase
        String[] arrayC = listClases.keySet().toArray(new String[0]);
        ArrayAdapter<String> newPerClasAdapt = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arrayC);
        newPerClasAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newPerClaseIn.setAdapter(newPerClasAdapt);
    }

    private Personajes savePersonaje() {
        Personajes newPersonaje = null;
        String name = "", sexo = "", error = "";
        Integer lengua = -1;
        int proc = -1, especie = -1, edad = -1, vital = -1, resis = -1, fuerza = -1, velo = -1, intel = -1, punt = -1, magia = -1;
        float altura = -1, peso = -1;

        try {
            // Get data
            if(verificarCampo(this.findViewById(R.id.newPerNombre), newPerNombreIn)){
                name = newPerNombreIn.getText().toString();
            }

            if(verificarCampoSpinner(this.findViewById(R.id.newPerProcedencia), newPerProcedenciaIn, listProcedencias)){
                proc = Integer.parseInt(Objects.requireNonNull(listProcedencias.get(newPerProcedenciaIn.getSelectedItem())));
            }

            if(verificarCampoSpinner(this.findViewById(R.id.newPerEspecie), newPerEspecieIn, listProcedencias)) {
                especie = Integer.parseInt(Objects.requireNonNull(listProcedencias.get(newPerEspecieIn.getSelectedItem())));
                String l = listLenguasEspecies.get(String.valueOf(especie));
                if (l.trim().equals("NULL")) {
                    lengua = null;
                } else {
                    lengua = Integer.valueOf(l);
                }
            }

            if(verificarCampoConRango(this.findViewById(R.id.newPerEdad), newPerEdadIn, 14, NumeroAbsurdameteGrande)){
                edad = Integer.parseInt(newPerEdadIn.getText().toString());
            }

            if(verificarCampoConRango(this.findViewById(R.id.newPerAltura), newPerAlturaIn, 0.5F, NumeroAbsurdameteGrande)){
                altura = Float.parseFloat(newPerAlturaIn.getText().toString());
            }

            if(verificarCampoConRango(this.findViewById(R.id.newPerPeso), newPerPesoIn, 25, NumeroAbsurdameteGrande)){
                peso = Float.parseFloat(newPerPesoIn.getText().toString());
            }

            HashMap<String , String> lolipop = new HashMap<>();
            String[] myResArray = getResources().getStringArray(R.array.newPerSexoSpin);
            List<String> myResArrayList = Arrays.asList(myResArray);
            int k = 0;
            for (String seso: myResArrayList) {
                lolipop.put(String.valueOf(k), seso);
                k++;
            }

            if(verificarCampoSpinner(this.findViewById(R.id.newPerSexso), newPerSexsoIn, lolipop)) {
                sexo = newPerSexsoIn.getSelectedItem().toString();
            }

            int clase = Integer.parseInt(listClases.get(newPerClaseIn.getSelectedItem()));

            if(verificarCampoConRango(this.findViewById(R.id.newPerVitalidad), newPerVitalidadIn, 1, 10)){
                vital = Integer.parseInt(newPerVitalidadIn.getText().toString());
            }
            if(verificarCampoConRango(this.findViewById(R.id.newPerResistencia), newPerResistenciaIn, 1, 10)){
                resis = Integer.parseInt(newPerResistenciaIn.getText().toString());
            }
            if(verificarCampoConRango(this.findViewById(R.id.newPerFuerza), newPerFuerzaIn, 1, 10)){
                fuerza = Integer.parseInt(newPerFuerzaIn.getText().toString());
            }
            if(verificarCampoConRango(this.findViewById(R.id.newPerVelocidad), newPerVelocidadIn, 1, 10)){
                velo = Integer.parseInt(newPerVelocidadIn.getText().toString());
            }
            if(verificarCampoConRango(this.findViewById(R.id.newPerInteligencia), newPerInteligenciaIn, 1, 10)){
                intel = Integer.parseInt(newPerInteligenciaIn.getText().toString());
            }
            if(verificarCampoConRango(this.findViewById(R.id.newPerPunteria), newPerPunteriaIn, 1, 10)){
                punt = Integer.parseInt(newPerPunteriaIn.getText().toString());
            }
            if(verificarCampoConRango(this.findViewById(R.id.newPerMagia), newPerMagiaIn, 1, 10)){
                magia = Integer.parseInt(newPerMagiaIn.getText().toString());
            }

            int color = Color.TRANSPARENT;

            if (vital + resis + fuerza + velo + intel + punt + magia > 25){
                color = Color.RED;
                totOK = false;
            }
            newPerVitalidadIn.setBackgroundColor(color);
            newPerResistenciaIn.setBackgroundColor(color);
            newPerFuerzaIn.setBackgroundColor(color);
            newPerVelocidadIn.setBackgroundColor(color);
            newPerInteligenciaIn.setBackgroundColor(color);
            newPerPunteriaIn.setBackgroundColor(color);
            newPerMagiaIn.setBackgroundColor(color);

            String perso = newPerPersonalidadIn.getText().toString();
            String fisico = newPerFisicoIn.getText().toString();

            if (!totOK){
                error = "Los datos no son adecuados o inexistentes";
                if(Color.RED == color){
                    error += "\nLa suma de los campos de estadisticas supera el limite";
                }
                advertenciaErrores(error);
            }else{
                newPersonaje = new Personajes(name, proc, especie, edad, altura, peso, sexo, clase,lengua,
                        vital, resis, fuerza, velo, intel, punt, magia, perso, fisico);
                newPersonaje.setProcedencia(listProcedencias.get(newPerProcedenciaIn.getSelectedItem()));
                newPersonaje.setEspecie(listEspecies.get(newPerEspecieIn.getSelectedItem()));
                newPersonaje.setClase(listClases.get(newPerClaseIn.getSelectedItem()));
                newPersonaje.setLengua1(listLenguas.get(String.valueOf(lengua)));
            }
//            totOK = true;
        } catch (Exception e) {
            e.printStackTrace();
            totOK = false;
        }
        return newPersonaje;
    }

    private void saveInServer(Personajes personaje) {
        // Put data in JSON
        JSONObject perso_json = personaje.getInputJSON();
        Log.e("Perso-JSON", String.valueOf(perso_json));

        // Guardar personaje
        try {
            JSONObject persoID = Utils.getDataJSON("post/personaje?new="+perso_json).getJSONObject("0");
            idPer = persoID.getInt("id");
            id_jugador = Utils.getDataJSON("put/set_personaje?id="+idPer).getJSONObject("0").getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void advertenciaErrores(String mensaje) {
        //Toast.makeText(this, Data.getLider(), Toast.LENGTH_SHORT).show();
        Log.e("lider", Data.getLider());
        AlertDialog.Builder builder = new AlertDialog.Builder(CrearPersonajeNuevo.this);
        builder.setCancelable(true);
        View alertEraseView = getLayoutInflater().inflate(R.layout.advertencia, null);

        TextView textViewAlert = alertEraseView.findViewById(R.id.textViewAlert);
        Button okBtn = alertEraseView.findViewById(R.id.okBtn);

        okBtn.setText("Ok");

        textViewAlert.setText(mensaje);

        builder.setView(alertEraseView);

        AlertDialog alertEraseAlert = builder.create();
        alertEraseAlert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertEraseAlert.show();

        okBtn.setOnClickListener(view2 -> alertEraseAlert.cancel());
    }
}