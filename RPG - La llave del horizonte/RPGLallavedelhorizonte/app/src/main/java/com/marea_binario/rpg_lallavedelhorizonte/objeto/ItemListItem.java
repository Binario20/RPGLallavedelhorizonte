package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.PaginaControlMaster;
import com.marea_binario.rpg_lallavedelhorizonte.R;
import com.marea_binario.rpg_lallavedelhorizonte.SuperText;

import org.json.JSONException;
import org.json.JSONObject;

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
        if(type.equals(Data.BESTIARIO)){
            initBestiario((Bestia)data);
        }
    }

    private void initBestiario(Bestia bestia) {
        cosaName.setEncodedText(bestia.getNobre());
        miniDescriptionCosa.setEncodedText(bestia.getDescripcion());
        leFoto.setImageResource(Data.getImg(bestia.getImg_id()));
        megaDescriptionCosa.setEncodedText(bestia.getClasificacion());
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
