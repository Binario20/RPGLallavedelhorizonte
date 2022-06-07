package com.marea_binario.rpg_lallavedelhorizonte;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Utils;

import java.io.UnsupportedEncodingException;

public class SuperText extends androidx.appcompat.widget.AppCompatTextView {
    public SuperText(Context context) {
        super(context);
    }

    public SuperText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SuperText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setEncodedText(String text){
        this.setText(Utils.fixEncode(text));
    }
}
