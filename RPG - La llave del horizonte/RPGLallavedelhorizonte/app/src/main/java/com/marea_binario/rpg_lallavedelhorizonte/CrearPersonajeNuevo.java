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

import com.marea_binario.rpg_lallavedelhorizonte.objeto.Personajes;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class CrearPersonajeNuevo extends AppCompatActivity {

    private EditText newPerNombreIn, newPerEdadIn, newPerAlturaIn, newPerPesoIn, newPerVitalidadIn,
            newPerResistenciaIn, newPerFuerzaIn, newPerVelocidadIn, newPerInteligenciaIn,
            newPerPunteriaIn, newPerMagiaIn, newPerPersonalidadIn, newPerFisicoIn;
    private Spinner newPerProcedenciaIn, newPerEspecieIn, newPerSexsoIn, newPerClaseIn;
    private LinearLayout oculto;
    private HashMap<String, String> listClases = new HashMap<>();
    private HashMap<String, String> listProcedencias = new HashMap<>();
    private HashMap<String, String> listEspecies = new HashMap<>();
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
        ConnTask connTask = new ConnTask("get/personaje/nuevo");
        connTask.execute();
        JSONObject listJ = null;
        try {
            String kk = connTask.get().toString().trim();
            //Log.e("Spinners", kk);
            listJ = new JSONObject(kk);
        } catch (Exception e) {
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
                listEspecies.put(key, value);
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
            Integer proc = Integer.valueOf(listProcedencias.get(newPerProcedenciaIn.getSelectedItem()));
            Integer especie = Integer.valueOf(listEspecies.get(newPerEspecieIn.getSelectedItem()));
            Integer edad = Integer.valueOf(newPerEdadIn.getText().toString());
            Float altura = Float.valueOf(newPerAlturaIn.getText().toString());
            Float peso = Float.valueOf(newPerPesoIn.getText().toString());
            String sexo = newPerSexsoIn.getSelectedItem().toString();
            Integer clase = Integer.valueOf(listClases.get(newPerClaseIn.getSelectedItem()));

            Integer vital = Integer.valueOf(newPerVitalidadIn.getText().toString());
            Integer resis = Integer.valueOf(newPerResistenciaIn.getText().toString());
            Integer fuerza = Integer.valueOf(newPerFuerzaIn.getText().toString());
            Integer velo = Integer.valueOf(newPerVelocidadIn.getText().toString());
            Integer intel = Integer.valueOf(newPerInteligenciaIn.getText().toString());
            Integer punt = Integer.valueOf(newPerPunteriaIn.getText().toString());
            Integer magia = Integer.valueOf(newPerMagiaIn.getText().toString());

            String perso = newPerPersonalidadIn.getText().toString();
            String fisico = newPerFisicoIn.getText().toString();

            newPersonaje = new Personajes(name, proc, especie, edad, altura, peso, sexo, clase,
                    vital, resis, fuerza, velo, intel, punt, magia, perso, fisico);
            newPersonaje.setProcedencia(listProcedencias.get(newPerProcedenciaIn.getSelectedItem()));
            newPersonaje.setEspecie(listEspecies.get(newPerEspecieIn.getSelectedItem()));
            newPersonaje.setClase(listClases.get(newPerClaseIn.getSelectedItem()));
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
        ConnTask connTask = new ConnTask("post/personaje?new="+String.valueOf(perso_json));
        connTask.execute();
        try{
            String kk = connTask.get().toString().trim();
            Log.e("fonko?", kk);
            //Get ID Personaje
            JSONObject persoID = new JSONObject(kk).getJSONObject("0");
            idPer = persoID.getInt("id");
        }catch (Exception e){
            e.printStackTrace();
        }

        //Set personaje
        ConnTask connTask2 = new ConnTask("put/set_personaje?id="+idPer);
        connTask.execute();
        try{
            String kk = connTask.get().toString().trim();
            Log.e("fonko?", kk);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}