package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.Data.Utils;
import com.marea_binario.rpg_lallavedelhorizonte.PaginaPrincipal;
import com.marea_binario.rpg_lallavedelhorizonte.R;
import com.marea_binario.rpg_lallavedelhorizonte.SuperText;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint("ViewConstructor")
public class NuevoPersonaje extends LinearLayout {
    private Integer idPerso;
    private String nombre;
    private int idImagen;
    private SuperText tvNombrePersonaje;
    private ImageView ivImagenPersonaje;

    public NuevoPersonaje(Context context, String nombre, Integer idPerso, int idImagen) {
        super(context);
        inflate(getContext(), R.layout.display_personaje, this);
        this.nombre = nombre;
        this.idPerso = idPerso;
        this.idImagen = idImagen;
        initComponents();
    }

    private void initComponents() {
        tvNombrePersonaje = findViewById(R.id.tvNombrePersonaje);
        ivImagenPersonaje = findViewById(R.id.ivImagenPersonaje);
        tvNombrePersonaje.setEncodedText(nombre);
        if (idImagen == 0) {
            ivImagenPersonaje.setImageDrawable(Data.getImageJugador(idImagen));
        }

        this.setOnClickListener(view -> {
            Log.e("np-cl??", String.valueOf(idPerso));
            JSONObject data = Utils.getDataJSON("put/set_personaje?id="+idPerso);
            Log.e("fonko?", String.valueOf(data));
            int id_jugador = -1;
            try {
                id_jugador = data.getJSONObject("0").getInt("id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Intent i = new Intent(getContext(), PaginaPrincipal.class);
            i.putExtra("idPerso", idPerso);
            i.putExtra("id_jugador", id_jugador);
            getContext().startActivity(i);
//                getContext().finish();
        });
    }
}
