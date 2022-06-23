package com.marea_binario.rpg_lallavedelhorizonte.Data;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.marea_binario.rpg_lallavedelhorizonte.R;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.ArmaBlanca;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.ArmaNegra;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Bestia;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Magia;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Objeto;
import com.marea_binario.rpg_lallavedelhorizonte.objeto.Regiones;

import java.util.ArrayList;

public class Data {

    public static final String JUGADOR = "Jugador";
    public static final String MASTER = "Master";
    public static final String LIDER = "Lider";
    public static String URL = "http://192.168.4.1/rpg/";
    private static String lider = "";
    private static Integer lider_id;
    private static String rol = Data.JUGADOR;
    private static ArrayList<Regiones> regiones;
    private static ArrayList<Objeto> objetos;
    private static ArrayList<ArmaNegra> armasNegras;
    private static ArrayList<ArmaBlanca> armasBlancas;
    private static ArrayList<Bestia> bestiario;
    private static ArrayList<Magia> magia;
    public static final String BESTIARIO = "bestiario";
    public static final String OBJETO = "objeto";
    public static final String ARMA_BLANCA = "armas blancas";
    public static final String ARMA_NEGRA = "armas negras";
    public static final String REGIONES = "regiones";
    public static final String MAGIA = "magia";
    public static int[] imgArray = {
            R.drawable.g1,
            R.drawable.g2,
            R.drawable.g3,
            R.drawable.g4,
            R.drawable.g5,
            R.drawable.g6};

    public static int[] imgObjetosArray = new int[122];

    private static LinearLayout lider_layout, master_layout;

    private static ArrayList<Drawable> listaImagenes;


    public static void setRol(String rol){
        Data.rol = rol;
    }

    public static String getRol() {
        return rol;
    }

    public static LinearLayout getLider_layout() {
        return lider_layout;
    }

    public static void setLider_layout(LinearLayout lider_layout) {
        Data.lider_layout = lider_layout;
    }

    public static String getLider() {
        return lider;
    }

    public static Integer getLider_id() {
        return lider_id;
    }

    public static void setLider(String lider, Integer lider_id) {
        Data.lider = lider;
        Data.lider_id = lider_id;
    }

    public static void setData(Context c) {
        listaImagenes = new ArrayList<>();
        listaImagenes.add(ContextCompat.getDrawable(c, R.drawable.newplayericon));
    }

    public static Drawable getImageJugador(int idImage) {
        try {
            return listaImagenes.get(idImage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getRandomWallPaper(){
        int min = 0;
        int max = imgArray.length-1;
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        return imgArray[random_int];
    }

    public static void setImg() {

        imgObjetosArray[0] = R.drawable.espada_de_afrodis;
        imgObjetosArray[1] = R.drawable.espada_de__xi;
        imgObjetosArray[2] = R.drawable.espada_de_hielo;
        imgObjetosArray[3] = R.drawable.espada_de_fuego;
        imgObjetosArray[4] = R.drawable.espada_del_alma;
        imgObjetosArray[5] = 0;
        imgObjetosArray[6] = R.drawable.sable;
        imgObjetosArray[7] = 0;
        imgObjetosArray[8] = 0;
        imgObjetosArray[9] = 0;
        imgObjetosArray[10] = R.drawable.daga_de_paradas;
        imgObjetosArray[11] = R.drawable.navaja_de__xi;
        imgObjetosArray[12] = 0;
        imgObjetosArray[13] = 0;
        imgObjetosArray[14] = R.drawable.cinquedea;
        imgObjetosArray[15] = R.drawable.daga_del_viento;
        imgObjetosArray[16] = 0;
        imgObjetosArray[17] = R.drawable.pica;
        imgObjetosArray[18] = 0;
        imgObjetosArray[19] = R.drawable.alabarda_incandescente;
        imgObjetosArray[20] = R.drawable.destral;
        imgObjetosArray[21] = R.drawable.hacha_de_guerra;
        imgObjetosArray[22] = R.drawable.hacha_del_rayo;
        imgObjetosArray[23] = R.drawable.baculo_de_p_nte;
        imgObjetosArray[24] = R.drawable.baculo_elemental;
        imgObjetosArray[25] = R.drawable.varita_de_pino_de_los_bosques_helados;
        imgObjetosArray[26] = R.drawable.varita_quemada;
        imgObjetosArray[27] = 0;
        imgObjetosArray[28] = R.drawable.anillo_de_kov_lito;
        imgObjetosArray[29] = R.drawable.ballesta_de_fos;
        imgObjetosArray[30] = 0;
        imgObjetosArray[31] = R.drawable.arco_corto;
        imgObjetosArray[32] = R.drawable.arco_largo;
        imgObjetosArray[33] = R.drawable.arco_recurvo;
        imgObjetosArray[34] = R.drawable.arco_de_hielo;
        imgObjetosArray[35] = R.drawable.pistola_de_chispa;
        imgObjetosArray[36] = 0;
        imgObjetosArray[37] = 0;
        imgObjetosArray[38] = R.drawable.arcabuz;
        imgObjetosArray[39] = R.drawable.escudo_de_madera;
        imgObjetosArray[40] = R.drawable.escudo_de_hierro;
        imgObjetosArray[41] = 0;
        imgObjetosArray[42] = 0;
        imgObjetosArray[43] = R.drawable.martillo_de__nas;
        imgObjetosArray[44] = 0;
        imgObjetosArray[45] = R.drawable.herramienta;
        imgObjetosArray[46] = R.drawable.bosque_de_pefko;
        imgObjetosArray[47] = R.drawable.bosque_de_tria;
        imgObjetosArray[48] = R.drawable.mavros;
        imgObjetosArray[49] = R.drawable.dyo;
        imgObjetosArray[50] = 0;
        imgObjetosArray[51] = R.drawable.exi;
        imgObjetosArray[52] = 0;
        imgObjetosArray[53] = R.drawable.isla_stagnos;
        imgObjetosArray[54] = R.drawable.isla_vrechos;
        imgObjetosArray[55] = R.drawable.kovalito;
        imgObjetosArray[56] = R.drawable.llanura_de_alas;
        imgObjetosArray[57] = R.drawable.llanura_de_tessera_norte;
        imgObjetosArray[58] = R.drawable.llanura_de_tessera_sur;
        imgObjetosArray[59] = R.drawable.llanura_prasinos;
        imgObjetosArray[60] = R.drawable.koralli;
        imgObjetosArray[61] = R.drawable.smaragdi;
        imgObjetosArray[62] = R.drawable.lefko;
        imgObjetosArray[63] = R.drawable.zafeiri;
        imgObjetosArray[64] = R.drawable.lefko;
        imgObjetosArray[65] = 0;
        imgObjetosArray[66] = R.drawable.rio_tessera;
        imgObjetosArray[67] = R.drawable.rio_tessera_monta_a;
        imgObjetosArray[68] = R.drawable.sierra_de_fotia;
        imgObjetosArray[69] = R.drawable.sierra_de_pente;
        imgObjetosArray[70] = 0;
        imgObjetosArray[71] = R.drawable.tria;
        imgObjetosArray[72] = R.drawable.tren_tr_a_tessera;
        imgObjetosArray[73] = 0;
        imgObjetosArray[74] = 0;
        imgObjetosArray[75] = 0;
        imgObjetosArray[76] = 0;
        imgObjetosArray[77] = 0;
        imgObjetosArray[78] = 0;
        imgObjetosArray[79] = 0;
        imgObjetosArray[80] = 0;
        imgObjetosArray[81] = 0;
        imgObjetosArray[82] = 0;
        imgObjetosArray[83] = 0;
        imgObjetosArray[84] = 0;
        imgObjetosArray[85] = 0;
        imgObjetosArray[86] = 0;
        imgObjetosArray[87] = 0;
        imgObjetosArray[88] = 0;
        imgObjetosArray[89] = 0;
        imgObjetosArray[90] = 0;
        imgObjetosArray[91] = 0;
        imgObjetosArray[92] = 0;
        imgObjetosArray[93] = 0;
        imgObjetosArray[94] = 0;
        imgObjetosArray[95] = 0;
        imgObjetosArray[96] = 0;
        imgObjetosArray[97] = 0;
        imgObjetosArray[98] = 0;
        imgObjetosArray[99] = 0;
        imgObjetosArray[100] = 0;
        imgObjetosArray[101] = 0;
        imgObjetosArray[102] = R.drawable.cubo;
        imgObjetosArray[103] = R.drawable.carne;
        imgObjetosArray[104] = R.drawable.pez;
        imgObjetosArray[105] = R.drawable.pocion_de_vida;
        imgObjetosArray[106] = R.drawable.pocion_de_fuerza;
        imgObjetosArray[107] = R.drawable.pocion_de_velocidad;
        imgObjetosArray[108] = R.drawable.pocion_de_resistencia;
        imgObjetosArray[109] = R.drawable.hierro;
        imgObjetosArray[110] = 0;
        imgObjetosArray[111] = 0;
        imgObjetosArray[112] = R.drawable.pluma_de_aguila_del_fuego;
        imgObjetosArray[113] = R.drawable.hierba_roja;
        imgObjetosArray[114] = R.drawable.hierba_amarilla;
        imgObjetosArray[115] = R.drawable.hierba_azul;
        imgObjetosArray[116] = R.drawable.hierba_naranja;
        imgObjetosArray[117] = 0;
        imgObjetosArray[118] = 0;
        imgObjetosArray[119] = R.drawable.papel;
        imgObjetosArray[120] = 0;
        imgObjetosArray[121] = 0;
    }

    public static int getImg(Integer img_id) {
        if (img_id == null) {
            return R.drawable.todoynada;
        } else if(imgObjetosArray[img_id]==0){
            return Data.getRandomWallPaper();
        }
        return imgObjetosArray[img_id];
    }

    public static void setRegiones(ArrayList<Regiones> regiones) {
        Data.regiones = regiones;
    }

    public static void setObjetos(ArrayList<Objeto> objetos) {
        Data.objetos = objetos;
    }

    public static void setArmasBlancas(ArrayList<ArmaBlanca> armasBlancas) {
        Data.armasBlancas = armasBlancas;
    }

    public static void setArmasNegras(ArrayList<ArmaNegra> armasNegras) {
        Data.armasNegras = armasNegras;
    }

    public static void setBestiario(ArrayList<Bestia> bestiario) {
        Data.bestiario = bestiario;
    }

    public static void setMagia(ArrayList<Magia> magia) {
        Data.magia = magia;
    }

    public static ArrayList<Regiones> getRegiones() {
        return regiones;
    }

    public static ArrayList<Objeto> getObjetos() {
        return objetos;
    }

    public static ArrayList<ArmaNegra> getArmasNegras() {
        return armasNegras;
    }

    public static ArrayList<ArmaBlanca> getArmasBlancas() {
        return armasBlancas;
    }

    public static ArrayList<Bestia> getBestiario() {
        return bestiario;
    }

    public static ArrayList<Magia> getMagia() {
        return magia;
    }
}
