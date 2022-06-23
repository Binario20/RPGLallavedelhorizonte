package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
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
    }

    public int getCustomId() {
        return customId;
    }

    public void setCustomId(int customId, String tipo) {
        this.tipo = tipo;
        this.customId = customId;

        switch (tipo){
            case Data.OBJETO:
                ArrayList<Objeto> objs = Data.getObjetos();
                for (Objeto obj : objs ) {
                    if (obj.getId()==customId){
                        mySelf = obj;
                        break;
                    }
                }
                break;
            case Data.ARMA_BLANCA:
                ArrayList<ArmaBlanca> armbs = Data.getArmasBlancas();
                for (ArmaBlanca armb : armbs ) {
                    if (armb.getId()==customId){
                        mySelf = armb;
                        break;
                    }
                }
                break;
            case Data.ARMA_NEGRA:
                ArrayList<ArmaNegra> armns = Data.getArmasNegras();
                for (ArmaNegra armn : armns ) {
                    if (armn.getId()==customId){
                        mySelf = armn;
                        break;
                    }
                }
                break;
            case Data.MAGIA:
                ArrayList<Magia> libros = Data.getMagia();
                for (Magia libro : libros ) {
                    if (libro.getId()==customId){
                        mySelf = libro;
                        break;
                    }
                }
                break;
            case Data.BESTIARIO:
                ArrayList<Bestia> bestias = Data.getBestiario();
                for (Bestia bestia : bestias ) {
                    if (bestia.getId()==customId){
                        mySelf = bestia;
                        break;
                    }
                }
                break;
        }

        this.img.setBackground(AppCompatResources.getDrawable(getContext(), mySelf.getImg_id()));
        this.nombreItem = String.valueOf(mySelf.getNombre());
    }

    private String getInfo(){
        String info ="";
        switch (tipo){
            case Data.OBJETO:
                info = ((Objeto)mySelf).toString();
                break;
            case Data.ARMA_BLANCA:
                info = ((ArmaBlanca)mySelf).toString();
                break;
            case Data.ARMA_NEGRA:
                info = ((ArmaNegra)mySelf).toString();
                break;
            case Data.MAGIA:
                info = ((Magia)mySelf).toString();
                break;
            case Data.BESTIARIO:
                info = ((Bestia)mySelf).toString();
                break;
        }
        return info;
    }
}
