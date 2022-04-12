package com.marea_binario.rpg_lallavedelhorizonte.objeto;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;
import com.marea_binario.rpg_lallavedelhorizonte.R;

public class Item extends LinearLayout {

    private OnLongClickListener item;
    private ImageView img;
    private TextView text;
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
        item = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getContext(), "item clicado " + customId, Toast.LENGTH_SHORT).show();
                return false;
            }
        };
        initComponents();
    }

    private void initComponents(){
        img = this.findViewById(R.id.img);
        img.setBackground(AppCompatResources.getDrawable(getContext(), Data.getRandomWallPaper()));
        this.setOnLongClickListener(item);
    }

    public int getCustomId() {
        return customId;
    }

    public void setCustomId(int customId) {
        this.customId = customId;
    }
}
