package com.marea_binario.rpg_lallavedelhorizonte.Data;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marea_binario.rpg_lallavedelhorizonte.ConnTask;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.ArmaBlanca;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.ArmaNegra;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Bestia;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Hechizo;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Magia;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.NuevoJugador;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Objeto;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Regiones;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Servicio;

import org.json.JSONException;
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


    public static JSONObject getDataJSON(String url){
        JSONObject data = new JSONObject();
        boolean notDone = true;
        while (notDone) {
            try {
                String x = getData(url);
                if (x.equals("204 OK"))
                    data = new JSONObject();
                else
                    data = new JSONObject(x);
                notDone = false;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return data;
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

    public static String getDescripcion(Regiones region, boolean full) {
        StringBuilder desc = new StringBuilder();
        if (full) {
            desc.append("- Nombre: ").append(region.getNombre()).append("\n");
            desc.append("- DescripciÃ³n: ").append(region.getDescripcion()).append("\n\n");
        }
        if (region.getTipo().equals("ciudad")) {
            desc.append("- Esta region ofrece los siguientes servicios:");
            for (Servicio servicio : region.getServicios()) {
                desc.append("\n  * ").append(servicio.getNombre());
                desc.append(servicio.getDescripcion().equalsIgnoreCase("NULL") ? "" : ": " + servicio.getDescripcion());
            }
        } else {
            desc.append("\n- Este acontecimiento geologico pertenece a la region de ");
            desc.append(region.getNombre_region());
        }
        return desc.toString();
    }

    public static String getDescripcion(Objeto objeto, boolean full) {
        String desc = "";
        if (full) {
            desc += "- Nombre: " + objeto.getNombre() + "\n";
            desc += "- DescripciÃ³n: " + objeto.getDescripcion() + "\n\n";
        }
        desc += "- Tipo: ";
        desc += objeto.getTipo() + "\n";
        if (objeto.getObj1().trim().equals("NULL")) {
            desc += "- Este objeto no se puede crear. Ha de ser encontrado.";
        } else {
            desc += "- Para crear este objeto se necesita:\n";
            if (objeto.getObj1().trim().equals(objeto.getObj2().trim()))
                desc += "  * 2 de "+objeto.getObj1();
            else {
                desc += "  * 1 de "+objeto.getObj1();
                if (!objeto.getObj2().trim().equals("NULL"))
                    desc += "\n  * 1 de "+objeto.getObj2();
            }
        }
        return desc;
    }

    public static String getDescripcion(ArmaBlanca arma, boolean full) {
        String desc = "";
        if (full) {
            desc += "- Nombre: " + arma.getNombre() + "\n";
            desc += "- DescripciÃ³n: " + arma.getDescripcion() + "\n\n";
        }
        desc += "- Tipo de arma: ";
        desc += arma.getSubtipo();
        desc += "\n- Para utilizar este arma se requiere un "+arma.getRequisito()+" de "+arma.getCampo();
        desc += "\n- Este arma aporta "+arma.getOperacion()+arma.getSuma1()+" a la estadistica de "+ arma.getSuma1_campo()+".";
        if (arma.getSuma2() != null)
            desc += "\n- Este arma puede aportar "+arma.getOperacion()+arma.getSuma2()+" a la estadistica de "+arma.getSuma2_campo()+" en casos especiales.";
        if (!arma.getAtaque().trim().equals("NULL"))
            desc += "\n- Tipo de ataque: "+arma.getAtaque();
        if (!arma.getRango().trim().equals("NULL"))
            desc += "\n- Rango de ataque: "+arma.getRango();
        if (arma.getNormal() == 0)
            desc += "\n- Esta es una arma especial.";
        else
            desc += "\n- Esta es una arma normal.";
        if (arma.getObj1().trim().equals("NULL")) {
            desc += "\n- Este objeto no se puede crear. Ha de ser encontrado.";
        } else {
            desc += "\n- Para crear este objeto se necesita:";
            if (arma.getObj1().trim().equals(arma.getObj2().trim()))
                desc += "\n  * 2 de "+arma.getObj1();
            else {
                desc += "\n  * 1 de "+arma.getObj1();
                if (!arma.getObj2().trim().equals("NULL"))
                    desc += "\n  * 1 de "+arma.getObj2();
            }
        }
        return desc;
    }

    public static String getDescripcion(ArmaNegra arma, boolean full) {
        String desc = "";
        if (full) {
            desc += "- Nombre: " + arma.getNombre() + "\n";
            desc += "- DescripciÃ³n: " + arma.getDescripcion() + "\n\n";
        }
        desc += "- Tipo: ";
        desc += arma.getSubtipo();
        desc += "\n- Para utilizar este arma se requiere un "+arma.getRequisito()+" de "+arma.getCampo();
        if (arma.getRequisito2() != null)
            desc += " y un "+arma.getRequisito2()+" de "+arma.getCampo2();
        desc += "\n- DaÃ±o del ataque: "+arma.getDaño();
        desc += "\n- Tipo de ataque: "+arma.getAtaque();
        desc += "\n- Rango de ataque: "+arma.getRango();
        if (arma.getNormal() == 0)
            desc += "\n- Esta es una arma especial.";
        else
            desc += "\n- Esta es una arma normal.";
        if (arma.getObj1().trim().equals("NULL")) {
            desc += "\n- Este objeto no se puede crear. Ha de ser encontrado.";
        } else {
            desc += "\n- Para crear este objeto se necesita:";
            if (arma.getObj1().trim().equals(arma.getObj2().trim()))
                desc += "\n  * 2 de "+arma.getObj1();
            else {
                desc += "\n  * 1 de "+arma.getObj1();
                if (!arma.getObj2().trim().equals("NULL"))
                    desc += "\n  * 1 de "+arma.getObj2();
            }
        }
        return desc;
    }

    public static String getDescripcion(Bestia bestia, boolean full) {
        String desc = "";
        if (full) {
            desc += "- Nombre: " + bestia.getNombre() + "\n";
            desc += "- DescripciÃ³n: " + bestia.getDescripcion() + "\n\n";
        }
        desc += "- Tipo: ";
        desc += bestia.getTipo() + "\n";
        desc += "- ClasificaciÃ³n: " + bestia.getClasificacion() + "\n";
        if (!bestia.getClasificacion_adicional().trim().equals("NULL"))
            desc += "- ClasificaciÃ³n(2): " + bestia.getClasificacion_adicional() + "\n";
        desc += "- Montura: ";
        desc +=  bestia.isMontura()? "si\n": "no\n";
        desc += "- TamaÃ±o: " + bestia.getTamaño() + "\n";
        desc += "- DaÃ±o: " + bestia.getDaño() + "\n";
        desc += "- Vida: " + bestia.getVida() + "\n";
        desc += "- Velocidad: " + bestia.getVelocidad() + "\n";
        if (bestia.getExperiencia() != null)
            desc += "- Experiencia al derrotar: " + bestia.getExperiencia() + "\n";
        if (bestia.getExtras().trim().equals("NULL"))
            desc += "- Resistente a: Nada\n";
        else
            desc += "- Resistente a: " + bestia.getResitencia() + "\n";
        if (bestia.getExtras().trim().equals("NULL"))
            desc += "- Vulnerable a: Nada";
        else
            desc += "- Vulnerable a: " + bestia.getVulnerabilidad();
        if (!bestia.getExtras().trim().equals("NULL"))
            desc += "\n- Extras: " + bestia.getExtras();
        return desc;
    }

    public static String getDescripcion(Magia magia, boolean full) {
        StringBuilder desc = new StringBuilder();
        if (full) {
            desc.append("- Nombre: ").append(magia.getNombre()).append("\n");
            desc.append("- DescripciÃ³n: ").append(magia.getDescripcion()).append("\n\n");
        }
        desc.append("- Tipo: ").append(magia.getTipo()).append("\n");
        desc.append("- Requiere saber la lengua ").append(magia.getLengua());
        desc.append(" y tener un nivel de inteligencia igual o superior a ").append(magia.getRequisitos());
        desc.append(" para poder utilizar los hechizos y conjuros de este libro.");
        if (magia.numHechizos() > 0)
            desc.append("\n- Este artefacto contiene los siguientes hechizos:");
        for (Hechizo hechizo : magia.getHechizos())
            desc.append("\n  * ").append(hechizo.getHechizo()).append(": ").append(hechizo.getDescripcion());
        return desc.toString();
    }

    public static Objeto getObjetoById(int id) {
        for (Objeto objeto : Data.getObjetos())
            if (objeto.getId() == id)
                return objeto;
        return null;
    }

    public static Regiones getRegionById(int id, String tipo) {
        for (Regiones region : Data.getRegiones())
            if (region.getId() == id && tipo.equals(region.getTipo()))
                return region;
        return null;
    }

    public static ArmaBlanca getArmaBlancaById(int id) {
        for (ArmaBlanca arma : Data.getArmasBlancas())
            if (arma.getId() == id)
                return arma;
        return null;
    }

    public static ArmaNegra getArmaNegraById(int id) {
        for (ArmaNegra arma : Data.getArmasNegras())
            if (arma.getId() == id)
                return arma;
        return null;
    }

    public static Bestia getBestiarioById(int id) {
        for (Bestia bestia : Data.getBestiario())
            if (bestia.getId() == id)
                return bestia;
        return null;
    }

    public static Magia getMagiaById(int id) {
        for (Magia magia : Data.getMagia())
            if (magia.getId() == id)
                return magia;
        return null;
    }
}
