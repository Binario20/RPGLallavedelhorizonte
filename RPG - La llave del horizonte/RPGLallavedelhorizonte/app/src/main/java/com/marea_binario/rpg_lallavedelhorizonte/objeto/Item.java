package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.Data.Utils;
import com.marea_binario.rpg_lallavedelhorizonte.R;
import com.marea_binario.rpg_lallavedelhorizonte.SuperText;

import java.util.ArrayList;

public class Item extends LinearLayout {

    private OnLongClickListener item;
    private ImageView img;
    private SuperText text;
    private String nombreItem = "";
    private String tipo;
    private MrWorldwide mySelf;
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
        text = this.findViewById(R.id.text);
    }

    public int getCustomId() {
        return customId;
    }

    public void setCustomId(int customId, String tipo) {
        this.customId = customId;

        if (customId != -1) {
            this.tipo = tipo;

            switch (tipo) {
                case Data.OBJETO:
                    mySelf = Utils.getObjetoById(customId);
                    break;
                case Data.ARMA_BLANCA:
                    mySelf = Utils.getArmaBlancaById(customId);
                    break;
                case Data.ARMA_NEGRA:
                    mySelf = Utils.getArmaNegraById(customId);
                    break;
                case Data.MAGIA:
                    mySelf = Utils.getMagiaById(customId);
                    break;
                case Data.BESTIARIO:
                    mySelf = Utils.getBestiarioById(customId);
                    break;
            }

            img.setImageResource(Data.getImg(mySelf.getImg_id()));
            text.setEncodedText(mySelf.getNombre());
        }else{
            img.setImageResource(Data.getImg(null));
            text.setEncodedText("(Vacio)");
        }
    }

    private String getInfo(){
        String info ="";/*
        switch (tipo){
            case Data.OBJETO:
                info = Utils.getDescripcion((Objeto)mySelf, true);
                break;
            case Data.ARMA_BLANCA:
                info = Utils.getDescripcion((ArmaBlanca)mySelf, true);
                break;
            case Data.ARMA_NEGRA:
                info = Utils.getDescripcion((ArmaNegra)mySelf, true);
                break;
            case Data.MAGIA:
                info = Utils.getDescripcion((Magia)mySelf, true);
                break;
            case Data.BESTIARIO:
                info = Utils.getDescripcion((Bestia)mySelf, true);
                break;
        }*/
        return info;
    }
}
