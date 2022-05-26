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

import com.marea_binario.rpg_lallavedelhorizonte.ConnTask;
import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.PaginaPrincipal;
import com.marea_binario.rpg_lallavedelhorizonte.R;

@SuppressLint("ViewConstructor")
public class NuevoPersonaje extends LinearLayout {
    private Integer idPerso;
    private String nombre;
    private int idImagen;
    private TextView tvNombrePersonaje;
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
        tvNombrePersonaje.setText(nombre);
        if (idImagen == 0) {
            ivImagenPersonaje.setImageDrawable(Data.getImageJugador(idImagen));
        }

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("np-cl??", String.valueOf(idPerso));
                ConnTask connTask = new ConnTask("put/set_personaje?id="+idPerso);
                connTask.execute();
                try{
                    String kk = connTask.get().toString().trim();
                    Log.e("fonko?", kk);
                }catch (Exception e){
                    e.printStackTrace();
                }
                Intent i = new Intent(getContext(), PaginaPrincipal.class);
                i.putExtra("Id", idPerso);
                getContext().startActivity(i);
//                getContext().finish();
                //Log.e("id", String.valueOf(idPerso));
            }
        });
    }
}
