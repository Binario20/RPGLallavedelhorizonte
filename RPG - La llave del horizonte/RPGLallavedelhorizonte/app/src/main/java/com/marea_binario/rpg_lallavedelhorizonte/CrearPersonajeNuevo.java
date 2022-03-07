package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.marea_binario.rpg_lallavedelhorizonte.objeto.Personajes;

public class CrearPersonajeNuevo extends AppCompatActivity {
    EditText newPerNombreIn, newPerEdadIn, newPerAlturaIn, newPerPesoIn, newPerVitalidadIn,
            newPerResistenciaIn, newPerFuerzaIn, newPerVelocidadIn, newPerInteligenciaIn,
            newPerPunteriaIn, newPerMagiaIn;
    Spinner newPerProcedenciaIn, newPerEspecieIn, newPerSexsoIn, newPerPreferencia1In;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_personaje_nuevo);
        initComponents();
    }

    private void initComponents() {
        newPerNombreIn = findViewById(R.id.newPerNombreIn);
        newPerProcedenciaIn = findViewById(R.id.newPerProcedenciaIn);
        newPerEspecieIn = findViewById(R.id.newPerEspecieIn);
        newPerEdadIn = findViewById(R.id.newPerEdadIn);
        newPerAlturaIn = findViewById(R.id.newPerAlturaIn);
        newPerPesoIn = findViewById(R.id.newPerPesoIn);
        newPerSexsoIn = findViewById(R.id.newPerSexsoIn);
        newPerPreferencia1In = findViewById(R.id.newPerPreferencia1In);
        newPerVitalidadIn = findViewById(R.id.newPerVitalidadIn);
        newPerFuerzaIn = findViewById(R.id.newPerFuerzaIn);
        newPerResistenciaIn = findViewById(R.id.newPerResistenciaIn);
        newPerVelocidadIn = findViewById(R.id.newPerVelocidadIn);
        newPerInteligenciaIn = findViewById(R.id.newPerInteligenciaIn);
        newPerPunteriaIn = findViewById(R.id.newPerPunteriaIn);
        newPerMagiaIn = findViewById(R.id.newPerMagiaIn);

        Button newPerAdvance = findViewById(R.id.newPerAdvance);
        Button saveNewPer = findViewById(R.id.saveNewPer);
        newPerAdvance.setOnClickListener(view -> {

        });
        saveNewPer.setOnClickListener(view -> {
            // Guardar personaje
            savePersonaje();
            // Empezar a jugar
            startActivity(new Intent(getApplicationContext(), PrimeraPantallaJuego.class));
            finish();
        });
    }

    private void savePersonaje() {
        //try {
            //Personajes newPer = new Personajes();
            String nombre = String.valueOf(newPerNombreIn.getText());
        //} except {};
    }

}