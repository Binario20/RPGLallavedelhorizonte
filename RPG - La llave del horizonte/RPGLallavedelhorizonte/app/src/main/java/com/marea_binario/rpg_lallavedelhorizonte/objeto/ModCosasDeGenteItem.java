package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.R;
import com.marea_binario.rpg_lallavedelhorizonte.SuperText;

public class ModCosasDeGenteItem extends LinearLayout {

    private String nombreCosa;
    private int cantidad;
    private SuperText nomCosa, cantCosa;
    private Button modCosa, delCosa;
    private ImageView imgCosa;

    public ModCosasDeGenteItem(Context context, String nombreCosa, int cantidad) {
        super(context);
        this.nombreCosa = nombreCosa;
        this.cantidad = cantidad;

        initComponents();
        initData();
    }

    private void initComponents() {
        inflate(getContext(), R.layout.mod_cosas_de_gente_itelm, this);
        nomCosa = this.findViewById(R.id.nomCosa);
        cantCosa = this.findViewById(R.id.cantCosa);

        modCosa = this.findViewById(R.id.modCosa);
        delCosa = this.findViewById(R.id.delCosa);
        imgCosa = this.findViewById(R.id.imgCosa);
    }

    private void initData() {
        nomCosa.setEncodedText(nombreCosa);
        cantCosa.setEncodedText(String.valueOf(cantidad));
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setImgCosa(int img_id) {
        imgCosa.setImageResource(Data.getImg(img_id));
    }

    public Button getModCosa() {
        return modCosa;
    }

    public Button getDelCosa() {
        return delCosa;
    }
}
