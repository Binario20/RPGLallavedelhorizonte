package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.R;
import com.marea_binario.rpg_lallavedelhorizonte.SuperText;

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
        if(type.equals(Data.BESTIARIO))
            initBestiario((Bestia)data);

        if (type.equals(Data.OBJETO))
            initObjeto((Objeto) data);

        if (type.equals(Data.ARMA_NEGRA))
            initArmaNegra((ArmaNegra) data);

        if (type.equals(Data.ARMA_BLANCA))
            initArmaBlanca((ArmaBlanca) data);

        if (type.equals(Data.MAGIA))
            initMagia((Magia) data);

        if (type.equals(Data.REGIONES))
            initRegiones((Regiones) data);
    }

    private void initArmaBlanca(ArmaBlanca arma) {
        cosaName.setEncodedText(arma.getNombre());
        miniDescriptionCosa.setEncodedText(arma.getDescripcion());
        leFoto.setImageResource(Data.getImg(arma.getImg_id()));

        //descripcion
        String desc = "- Tipo de arma: ";
        desc += arma.getSubtipo();
        desc += "\n- Para utilizar este arma se requiere un "+arma.getRequisito()+" de "+arma.getCampo();
        desc += "\n- Este arma aporta "+arma.getOperacion()+arma.getSuma1()+" a la estadistica de "+ arma.getSuma1_campo()+".";
        if (arma.getSuma2() != null)
            desc += "\n- Este arma puede aportar "+arma.getOperacion()+arma.getSuma2()+" a la estadistica de "+arma.getSuma2_campo()+" en casos especiales.";
        if (!arma.getAtaque().trim().equals("NULL"))
            desc += "\n-Tipo de ataque: "+arma.getAtaque();
        if (!arma.getRango().trim().equals("NULL"))
            desc += "\n-Rango de ataque: "+arma.getRango();
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
        megaDescriptionCosa.setEncodedText(desc);
    }

    private void initArmaNegra(ArmaNegra arma) {
        cosaName.setEncodedText(arma.getNombre());
        miniDescriptionCosa.setEncodedText(arma.getDescripcion());
        leFoto.setImageResource(Data.getImg(arma.getImg_id()));

        //descripcion
        String desc = "- Tipo: ";
        desc += arma.getSubtipo();
        desc += "\n- Para utilizar este arma se requiere un "+arma.getRequisito()+" de "+arma.getCampo();
        if (arma.getRequisito2() != null)
            desc += " y un "+arma.getRequisito2()+" de "+arma.getCampo2();
        desc += "\n- DaÃ±o del ataque: "+arma.getDaño();
        desc += "\n-Tipo de ataque: "+arma.getAtaque();
        desc += "\n-Rango de ataque: "+arma.getRango();
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
        megaDescriptionCosa.setEncodedText(desc);
    }

    private void initObjeto(Objeto objeto) {
        cosaName.setEncodedText(objeto.getNombre());
        miniDescriptionCosa.setEncodedText(objeto.getDescripcion());
        leFoto.setImageResource(Data.getImg(objeto.getImg_id()));

        //descripcion
        String desc = "- Tipo: ";
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
        megaDescriptionCosa.setEncodedText(desc);
    }

    private void initBestiario(Bestia bestia) {
        cosaName.setEncodedText(bestia.getNombre());
        miniDescriptionCosa.setEncodedText(bestia.getDescripcion());
        leFoto.setImageResource(Data.getImg(bestia.getImg_id()));

        //descripcion
        String desc = "- Tipo: ";
        desc += bestia.getTipo() + "\n";
        desc += "- ClasificaciÃ³n: " + bestia.getClasificacion() + "\n";
        if (!bestia.getClasificacion_adicional().trim().equals("NULL"))
            desc += "- ClasificaciÃ³n(2): " + bestia.getClasificacion_adicional() + "\n";
        desc += "- Montura: ";
        desc +=  bestia.isMontura()? "si\n": "no\n";
        desc += "- TamaÃ±o: " + bestia.getTamaño() + "\n";
        desc += "- DaÃ±o: " + bestia.getDaño() + "\n";
        desc += "- Vida: " + bestia.getVida() + "\n";
        desc += "-Velocidad: " + bestia.getVelocidad() + "\n";
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
        megaDescriptionCosa.setEncodedText(desc);
    }

    private void initMagia(Magia magia) {
        cosaName.setEncodedText(magia.getNombre());
        miniDescriptionCosa.setEncodedText(magia.getDescripcion());
        leFoto.setImageResource(Data.getImg(magia.getImg_id()));
        leFoto.getLayoutParams().width = 1;

        //descripcion
        StringBuilder desc = new StringBuilder("- Tipo: ");
        desc.append(magia.getTipo()).append("\n");
        desc.append("- Requiere saber la lengua ").append(magia.getLengua());
        desc.append(" y tener un nivel de inteligencia igual o superior a ").append(magia.getRequisitos());
        desc.append(" para poder utilizar los hechizos y conjuros de este libro.");
        if (magia.numHechizos() > 0)
            desc.append("- Este artefacto contiene los siguientes hechizos:");
        for (Hechizo hechizo : magia.getHechizos())
            desc.append("\n  * ").append(hechizo.getHechizo()).append(": ").append(hechizo.getDescripcion());
        megaDescriptionCosa.setEncodedText(desc.toString());
    }

    private void initRegiones(Regiones regiones) {
        cosaName.setEncodedText(regiones.getNombre());
        miniDescriptionCosa.setEncodedText(regiones.getDescripcion());
        leFoto.setImageResource(Data.getImg(regiones.getImg_id()));

        //descripcion
        StringBuilder desc = new StringBuilder();
        if (regiones.getTipo().equals("ciudad")) {
            desc.append("- Esta region ofrece los siguientes servicios:");
            for (Servicio servicio : regiones.getServicios()) {
                desc.append("\n  * ").append(servicio.getNombre());
                desc.append(servicio.getDescripcion().equalsIgnoreCase("NULL") ? "" : ": " + servicio.getDescripcion());
            }
        } else {
            desc.append("\n- Este acontecimiento geologico pertenece a la region de ");
            desc.append(regiones.getNombre_region());
        }
        megaDescriptionCosa.setEncodedText(desc.toString());
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
