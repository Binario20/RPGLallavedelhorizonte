package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Utils;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Personajes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
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
    private Integer idPer;
    private boolean totOK = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaje_nuevo_by_ivan);
        initComponents();
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
        newPerResistenciaIn = this.findViewById(R.id.newPerResistenciaIn);
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
                i.putExtra("Id", idPer);
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
        JSONObject listJ = null;
        try {
            listJ = new JSONObject(Utils.getData("get/personaje/nuevo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
        try {
            // Get data
            String name = newPerNombreIn.getText().toString();
            int proc = Integer.parseInt(listProcedencias.get(newPerProcedenciaIn.getSelectedItem()));
            int especie = Integer.parseInt(listEspecies.get(newPerEspecieIn.getSelectedItem()));
            int edad = Integer.parseInt(newPerEdadIn.getText().toString());
            float altura = Float.parseFloat(newPerAlturaIn.getText().toString());
            float peso = Float.parseFloat(newPerPesoIn.getText().toString());
            String sexo = newPerSexsoIn.getSelectedItem().toString();
            int clase = Integer.parseInt(listClases.get(newPerClaseIn.getSelectedItem()));
            String l = listLenguasEspecies.get(String.valueOf(especie));
            Integer lengua;
            if (l.trim().equals("NULL")) {
                Log.e("funko??",l);
                lengua = null;
            } else {
                Log.e("fonko?",l);
                lengua = Integer.valueOf(l);
            }

            int vital = Integer.parseInt(newPerVitalidadIn.getText().toString());
            int resis = Integer.parseInt(newPerResistenciaIn.getText().toString());
            int fuerza = Integer.parseInt(newPerFuerzaIn.getText().toString());
            int velo = Integer.parseInt(newPerVelocidadIn.getText().toString());
            int intel = Integer.parseInt(newPerInteligenciaIn.getText().toString());
            int punt = Integer.parseInt(newPerPunteriaIn.getText().toString());
            int magia = Integer.parseInt(newPerMagiaIn.getText().toString());

            String perso = newPerPersonalidadIn.getText().toString();
            String fisico = newPerFisicoIn.getText().toString();

            newPersonaje = new Personajes(name, proc, especie, edad, altura, peso, sexo, clase,lengua,
                    vital, resis, fuerza, velo, intel, punt, magia, perso, fisico);
            newPersonaje.setProcedencia(listProcedencias.get(newPerProcedenciaIn.getSelectedItem()));
            newPersonaje.setEspecie(listEspecies.get(newPerEspecieIn.getSelectedItem()));
            newPersonaje.setClase(listClases.get(newPerClaseIn.getSelectedItem()));
            newPersonaje.setLengua1(listLenguas.get(String.valueOf(lengua)));
            totOK = true;
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
            JSONObject persoID = new JSONObject(Utils.getData("post/personaje?new="+perso_json)).getJSONObject("0");
            idPer = persoID.getInt("id");
            Utils.getData("put/set_personaje?id="+idPer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}