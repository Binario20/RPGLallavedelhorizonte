package com.marea_binario.rpg_lallavedelhorizonte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class CrearPersonajeNuevo extends AppCompatActivity {

    private EditText newPerNombreIn, newPerEdadIn, newPerAlturaIn, newPerPesoIn, newPerVitalidadIn,
            newPerResistenciaIn, newPerFuerzaIn, newPerVelocidadIn, newPerInteligenciaIn,
            newPerPunteriaIn, newPerMagiaIn;
    private Spinner newPerProcedenciaIn, newPerEspecieIn, newPerSexsoIn, newPerClaseIn;
    private LinearLayout oculto;
    private TextView caracteristicasEspeciales;

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

        setSpinners();

        oculto = this.findViewById(R.id.oculto);
        caracteristicasEspeciales = this.findViewById(R.id.caracteristicasEspeciales);

        this.findViewById(R.id.saveNewPer).setOnClickListener(view -> {
            // Guardar personaje
            savePersonaje();
            // Empezar a jugar
            startActivity(new Intent(getApplicationContext(), PrimeraPantallaJuego.class));
            finish();
        });

        caracteristicasEspeciales.setOnClickListener(view -> {
            if(oculto.getVisibility() == View.GONE){
                oculto.setVisibility(View.VISIBLE);
            }else{
                oculto.setVisibility(View.GONE);
            }
        });
    }

    private void setSpinners() {
        ArrayAdapter<CharSequence> newPerSexsoAdapt = ArrayAdapter.createFromResource(this,R.array.newPerSexoSpin, android.R.layout.simple_spinner_item);
        newPerSexsoAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newPerSexsoIn.setAdapter(newPerSexsoAdapt);
    }

    private void savePersonaje() {
        //try {
            //Personajes newPer = new Personajes();
            String nombre = String.valueOf(newPerNombreIn.getText());
            if (true){
                saveInBD();
                Intent i = new Intent(this, PaginaPrincipal.class);
                i.putExtra("Id", 0);
                startActivity(i);
            }
        //} except {};
    }

    private void saveInBD() {

    }

}