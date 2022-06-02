package com.marea_binario.rpg_lallavedelhorizonte.Data;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marea_binario.rpg_lallavedelhorizonte.ConnTask;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.NuevoJugador;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * Clase de utilidades random
 */
public class Utils {

    public static String fixEncode(String dencode) throws UnsupportedEncodingException {
        byte[] pp = dencode.getBytes("ISO-8859-15");
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

    public static JSONObject getDepositoObjetos() {
        ConnTask connTask2 = new ConnTask("get/obj_grupo");
        connTask2.execute();
        JSONObject objList = null;
        try {
            String objString = connTask2.get().toString().trim();
            Log.e("depositoObjetos", objString);
            objList = new JSONObject(objString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objList;
    }
}
