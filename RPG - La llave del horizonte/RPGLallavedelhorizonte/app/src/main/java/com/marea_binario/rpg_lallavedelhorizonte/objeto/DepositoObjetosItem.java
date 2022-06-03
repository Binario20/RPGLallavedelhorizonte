package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marea_binario.rpg_lallavedelhorizonte.R;
import com.marea_binario.rpg_lallavedelhorizonte.SuperText;

public class DepositoObjetosItem extends LinearLayout {

    private int image_id, id;
    private String nombre, descripcion, cantidad;

    private SuperText nom_txt, desc_txt, num_txt;
    private ImageView obj_img;

    public DepositoObjetosItem(Context context, int id, int image_id, String nombre,
                               String descripcion, String cantidad) {
        super(context);
        this.id = id;
        this.image_id = image_id;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.descripcion = descripcion;

        initComponents();
        setData();
    }

    private void  initComponents() {
        inflate(getContext(), R.layout.deposito_objetos_item, this);
        obj_img = this.findViewById(R.id.dipObjImg);
        nom_txt = this.findViewById(R.id.dipObjNom);
        desc_txt = this.findViewById(R.id.dipObjDesc);
        num_txt = this.findViewById(R.id.dipObjNum);
    }

    private void setData() {
        nom_txt.setEncodedText(nombre);
        desc_txt.setEncodedText(descripcion);
        num_txt.setEncodedText(cantidad);

        //Set Img
    }
}
