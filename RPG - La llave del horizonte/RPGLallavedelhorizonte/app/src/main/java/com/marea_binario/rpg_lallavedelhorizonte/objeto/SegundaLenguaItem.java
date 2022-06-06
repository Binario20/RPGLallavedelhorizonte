package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.marea_binario.rpg_lallavedelhorizonte.R;
import com.marea_binario.rpg_lallavedelhorizonte.SuperText;

public class SegundaLenguaItem extends androidx.appcompat.widget.AppCompatRadioButton {
    private String name;
    private SuperText txt;

    public SegundaLenguaItem(Context context, String name) {
        super(context);
        this.name = name;

        initComponents();
    }

    private void initComponents() {
        //inflate(getContext(), R.layout.segunda_lengua_item, this);
        txt = this.findViewById(R.id.lenguaChose);
        txt.setEncodedText(name);
    }

    public String getName() { return name; }
}
