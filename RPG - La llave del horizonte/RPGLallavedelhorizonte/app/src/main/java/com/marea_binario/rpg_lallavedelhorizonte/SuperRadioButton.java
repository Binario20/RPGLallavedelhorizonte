package com.marea_binario.rpg_lallavedelhorizonte;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Utils;

import java.io.UnsupportedEncodingException;

public class SuperRadioButton extends androidx.appcompat.widget.AppCompatRadioButton {
    public SuperRadioButton(Context context) {
        super(context);
    }

    public SuperRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SuperRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setEncodedText(String text){
        try {
            this.setText(Utils.fixEncode(text));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
