package com.marea_binario.rpg_lallavedelhorizonte.Data;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marea_binario.rpg_lallavedelhorizonte.ConnTask;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.ArmaBlanca;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.ArmaNegra;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Bestia;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Magia;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.NuevoJugador;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Objeto;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Regiones;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Clase de utilidades random
 */
public class Utils {

    public static String fixEncode(String dencode) {
        byte[] pp = new byte[0];
        try {
            pp = dencode.getBytes("ISO-8859-15");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new String(pp, StandardCharsets.UTF_8);
    }

    public static void cambiarLider(String nombre){
        LinearLayout lider_layout = Data.getLider_layout();
        for (int i = 0 ; i < lider_layout.getChildCount() ; i++){
            if (lider_layout.getChildAt(i) instanceof NuevoJugador){
                if(((NuevoJugador) lider_layout.getChildAt(i)).getNombre().equals(nombre)){
                    ((NuevoJugador) lider_layout.getChildAt(i)).setLider(true);
                }else{
                    ((NuevoJugador) lider_layout.getChildAt(i)).setLider(false);
                }
            }
        }
    }

    public static void getDineros(TextView dineros) {
        ConnTask connTask2 = new ConnTask("get/dineros");
        connTask2.execute();
        try {
            String din = connTask2.get().toString().trim();
            //Log.e("dineros", din);
            dineros.setText(din);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addDineros(TextView dineros, int d) {
        ConnTask connTask2 = new ConnTask("put/dineros?add="+d);
        connTask2.execute();
        try {
            String din = connTask2.get().toString().trim();
            //Log.e("dineros", din);
            dineros.setText(din);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void subDineros(TextView dineros, int d) {
        ConnTask connTask2 = new ConnTask("put/dineros?sup="+d);
        connTask2.execute();
        try {
            String din = connTask2.get().toString().trim();
            //Log.e("dineros", din);
            dineros.setText(din);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getData(String url){
        ConnTask connTask2 = new ConnTask(url);
        connTask2.execute();
        try {
            String ret = connTask2.get().toString().trim();
            if(ret == null){
                return getData(url);
            }else{
                return ret;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }

    public static Integer stringToInteger(String s) {
        return s.equalsIgnoreCase("NULL")?null:Integer.valueOf(s);
    }

    public static String getNombreCosa(int id, String tipo) {
        switch (tipo) {
            case Data.REGIONES:
                for (Regiones region : Data.getRegiones())
                    if (region.getId() == id)
                        return region.getNombre();
                break;
            case Data.OBJETO:
                for (Objeto objeto : Data.getObjetos())
                    if (objeto.getId() == id)
                        return objeto.getNombre();
                break;
            case Data.MAGIA:
                for (Magia magia : Data.getMagia())
                    if (magia.getId() == id)
                        return magia.getNombre();
                break;
            case Data.ARMA_BLANCA:
                for (ArmaBlanca armaBlanca : Data.getArmasBlancas())
                    if (armaBlanca.getId() == id)
                        return armaBlanca.getNombre();
                break;
            case Data.ARMA_NEGRA:
                for (ArmaNegra armaNegra : Data.getArmasNegras())
                    if (armaNegra.getId() == id)
                        return armaNegra.getNombre();
                break;
            case Data.BESTIARIO:
                for (Bestia bestia : Data.getBestiario())
                    if (bestia.getId() == id)
                        return bestia.getNombre();
                break;
        }
        return "";
    }

    public static Integer getImgIdCosa(int id, String tipo) {
        switch (tipo) {
            case Data.REGIONES:
                for (Regiones region : Data.getRegiones())
                    if (region.getId() == id)
                        return region.getImg_id();
                break;
            case Data.OBJETO:
                for (Objeto objeto : Data.getObjetos())
                    if (objeto.getId() == id)
                        return objeto.getImg_id();
                break;
            case Data.MAGIA:
                for (Magia magia : Data.getMagia())
                    if (magia.getId() == id)
                        return magia.getImg_id();
                break;
            case Data.ARMA_BLANCA:
                for (ArmaBlanca armaBlanca : Data.getArmasBlancas())
                    if (armaBlanca.getId() == id)
                        return armaBlanca.getImg_id();
                break;
            case Data.ARMA_NEGRA:
                for (ArmaNegra armaNegra : Data.getArmasNegras())
                    if (armaNegra.getId() == id)
                        return armaNegra.getImg_id();
                break;
            case Data.BESTIARIO:
                for (Bestia bestia : Data.getBestiario())
                    if (bestia.getId() == id)
                        return bestia.getImg_id();
                break;
        }
        return null;
    }
}
