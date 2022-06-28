package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.Data.Utils;
import com.marea_binario.rpg_lallavedelhorizonte.R;
import com.marea_binario.rpg_lallavedelhorizonte.SuperText;

import java.io.UTFDataFormatException;

public class ItemListItem  extends LinearLayout {

    private ImageView leFoto;
    private SuperText cosaName, miniDescriptionCosa, megaDescriptionCosa;
    private Button add, showMore;
    private final String type;
    private final int id;

    public ItemListItem(Context context, int id, String type, Object data) {
        super(context);
        this.id = id;
        this.type = type;
        initComponents();
        initListeners();
        if(type.equals(Data.BESTIARIO))
            initBestiario((Bestia)data);

        if (type.equals(Data.OBJETO))
            initObjeto((Objeto) data);

        if (type.equals(Data.ARMA_NEGRA))
            initArmaNegra((ArmaNegra) data);

        if (type.equals(Data.ARMA_BLANCA))
            initArmaBlanca((ArmaBlanca) data);

        if (type.equals(Data.MAGIA))
            initMagia((Magia) data);

        if (type.equals(Data.REGIONES))
            initRegiones((Regiones) data);
    }

    private void initArmaBlanca(ArmaBlanca arma) {
        cosaName.setEncodedText(arma.getNombre());
        miniDescriptionCosa.setEncodedText(arma.getDescripcion());
        leFoto.setImageResource(Data.getImg(arma.getImg_id()));

        //descripcion
        megaDescriptionCosa.setEncodedText(Utils.getDescripcion(arma,false));
    }

    private void initArmaNegra(ArmaNegra arma) {
        cosaName.setEncodedText(arma.getNombre());
        miniDescriptionCosa.setEncodedText(arma.getDescripcion());
        leFoto.setImageResource(Data.getImg(arma.getImg_id()));

        //descripcion
        megaDescriptionCosa.setEncodedText(Utils.getDescripcion(arma,false));
    }

    private void initObjeto(Objeto objeto) {
        cosaName.setEncodedText(objeto.getNombre());
        miniDescriptionCosa.setEncodedText(objeto.getDescripcion());
        leFoto.setImageResource(Data.getImg(objeto.getImg_id()));

        //descripcion
        megaDescriptionCosa.setEncodedText(Utils.getDescripcion(objeto,false));
    }

    private void initBestiario(Bestia bestia) {
        cosaName.setEncodedText(bestia.getNombre());
        miniDescriptionCosa.setEncodedText(bestia.getDescripcion());
        leFoto.setImageResource(Data.getImg(bestia.getImg_id()));

        //descripcion
        megaDescriptionCosa.setEncodedText(Utils.getDescripcion(bestia,false));
    }

    private void initMagia(Magia magia) {
        cosaName.setEncodedText(magia.getNombre());
        miniDescriptionCosa.setEncodedText(magia.getDescripcion());
        leFoto.setImageResource(Data.getImg(magia.getImg_id()));
        leFoto.getLayoutParams().width = 1;

        //descripcion
        megaDescriptionCosa.setEncodedText(Utils.getDescripcion(magia,false));
    }

    private void initRegiones(Regiones regiones) {
        cosaName.setEncodedText(regiones.getNombre());
        miniDescriptionCosa.setEncodedText(regiones.getDescripcion());
        leFoto.setImageResource(Data.getImg(regiones.getImg_id()));

        //descripcion
        megaDescriptionCosa.setEncodedText(Utils.getDescripcion(regiones,false));
    }

    private void initComponents(){
        inflate(getContext(), R.layout.item_list_item, this);
        leFoto = this.findViewById(R.id.leFoto);
        cosaName = this.findViewById(R.id.cosaName);
        miniDescriptionCosa = this.findViewById(R.id.miniDescriptionCosa);
        megaDescriptionCosa = this.findViewById(R.id.megaDescriptionCosa);
        add = this.findViewById(R.id.add);
        showMore = this.findViewById(R.id.showMore);

    }

    private void initListeners() {
        showMore.setOnClickListener(view -> {
            if (megaDescriptionCosa.getVisibility() == View.VISIBLE)
                megaDescriptionCosa.setVisibility(View.GONE);
            else if (megaDescriptionCosa.getVisibility() == View.GONE)
                megaDescriptionCosa.setVisibility(View.VISIBLE);
        });
    }

    public Button getAdd () { return add; }
}
