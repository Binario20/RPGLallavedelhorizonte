package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.marea_binario.rpg_lallavedelhorizonte.R;
import com.marea_binario.rpg_lallavedelhorizonte.SuperText;

public class ModCosaDeGenteItemList extends LinearLayout {

    private String nombre;
    private SuperText nomPersoList;
    private Button showListCosas, reloadCosas;
    private LinearLayout listaDeCosas;

    public ModCosaDeGenteItemList(Context context, String nombre) {
        super(context);
        this.nombre = nombre;

        initComponents();
        initData();
        initListeners();
    }

    private void initComponents() {
        inflate(getContext(), R.layout.mod_cosas_de_gente_itelm, this);
        nomPersoList = this.findViewById(R.id.nomPersoList);
        showListCosas = this.findViewById(R.id.showListCosas);
        reloadCosas = this.findViewById(R.id.reloadCosas);
        listaDeCosas = this.findViewById(R.id.showListCosas);
    }

    private void initData() {
        nomPersoList.setEncodedText(nombre);
    }

    private void initListeners() {
        showListCosas.setOnClickListener(view -> {
            if (listaDeCosas.getVisibility() == View.VISIBLE)
                listaDeCosas.setVisibility(View.GONE);
            else if (listaDeCosas.getVisibility() == View.GONE)
                listaDeCosas.setVisibility(View.VISIBLE);
        });
    }

    public Button getReloadCosas() {
        return reloadCosas;
    }

    public LinearLayout getListaDeCosas() {
        return listaDeCosas;
    }
}
