package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;

import com.marea_binario.rpg_lallavedelhorizonte.R;

public class Item extends LinearLayout {

    private OnLongClickListener item;
    private ImageView img;
    private TextView text;
    private String nombreItem = "";
    private int customId = -1;

    public Item(Context context) {
        super(context);
        init();
    }

    public Item(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Item(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Item(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.item, this);
        item = view -> {
            if (nombreItem.equals("")) {
                return false;
            }
            Dialog dialog = new Dialog(getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(getContext().getDrawable(R.drawable.dialog_bg));//Aquí le das el efecto de transparencia
//                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            dialog.setContentView(R.layout.activity_datos_objetos);//Aquí cargas el layout
            ((TextView)dialog.findViewById(R.id.tittulo)).setText(nombreItem);
            ((TextView)dialog.findViewById(R.id.texto)).setText(getInfo());
            dialog.show();
            return false;
        };
        initComponents();
    }

    private void initComponents(){
        img = this.findViewById(R.id.img);
        img.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.todoynada));
        this.setOnLongClickListener(item);
    }

    public int getCustomId() {
        return customId;
    }

    public void setCustomId(int customId) {
        this.customId = customId;
        this.nombreItem = String.valueOf(customId);
    }

    private String getInfo(){

        return "Info aqui";
    }
}
