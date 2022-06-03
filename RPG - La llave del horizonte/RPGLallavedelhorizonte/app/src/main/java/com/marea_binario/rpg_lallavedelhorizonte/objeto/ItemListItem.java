package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.marea_binario.rpg_lallavedelhorizonte.R;
import com.marea_binario.rpg_lallavedelhorizonte.SuperText;

public class ItemListItem  extends LinearLayout {

    private ImageView leFoto;
    private SuperText cosaName, miniDescriptionCosa, megaDescriptionCosa;
    private Button add, showMore;
    private final String type;
    private final int id;

    public ItemListItem(Context context, int id, String type) {
        super(context);
        this.id = id;
        this.type = type;
        initComponents();
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

}
