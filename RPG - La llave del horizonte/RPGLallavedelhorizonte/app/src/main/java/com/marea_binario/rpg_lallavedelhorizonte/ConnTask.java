package com.marea_binario.rpg_lallavedelhorizonte;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ConnTask extends AsyncTask {
    private final Context context;
    private final ArrayList keys;
    private final String php;
    private String ret;
    private final ArrayList values;

    public ConnTask(Context var1, ArrayList var2, ArrayList var3, String var4) {
        this.keys = var2;
        this.values = var3;
        this.context = var1;
        this.php = var4;
    }

    protected Object doInBackground(Object[] objects) {
        String toFinalUrl = Data.URL + this.php;
        Uri.Builder uri = new Uri.Builder();

        int var2;
        for(var2 = 0; var2 < this.keys.size(); ++var2) {
            uri.appendQueryParameter((String)this.keys.get(var2), (String)this.values.get(var2));
        }

        toFinalUrl += uri.toString();
        StringBuilder var9 = new StringBuilder();

        label35: {
            label34: {
                BufferedInputStream var11;
                try {
                    Log.e("TAG", "doInBackground: " + toFinalUrl);
                    URL url = new URL(toFinalUrl);
                    HttpURLConnection var14 = (HttpURLConnection)url.openConnection();
                    var11 = new BufferedInputStream(var14.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                    break label34;
                }

                while(true) {
                    try {
                        var2 = var11.read();
                    } catch (IOException var6) {
                        break;
                    }
                    if (var2 == -1) {
                        break label35;
                    }
                    var9.append((char)var2);
                }
            }
        }

        String var10 = var9.toString();
        this.ret = var10;
        return var10;
    }

    public void onPostExecute(String var1) {
    }

}
