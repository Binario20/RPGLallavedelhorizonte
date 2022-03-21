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
    private final ArrayList<String> keys;
    private final String php;
    private String ret;
    private final ArrayList<String> values;

    public ConnTask(ArrayList<String> var2, ArrayList<String> var3, String var4) {
        this.keys = var2;
        this.values = var3;
        this.php = var4;
    }

    protected Object doInBackground(Object[] objects) {
        String toFinalUrl = Data.URL + this.php;
        Uri.Builder uri = new Uri.Builder();

        int i;
        for(i = 0; i < this.keys.size(); i++) {
            uri.appendQueryParameter(keys.get(i), values.get(i));
        }

        toFinalUrl += uri.toString();
        StringBuilder var9 = new StringBuilder();

        label35: {
            label34: {
                BufferedInputStream buffer;
                try {
                    Log.e("TAG", "doInBackground: " + toFinalUrl);
                    URL url = new URL(toFinalUrl);
                    HttpURLConnection UrlConnection = (HttpURLConnection)url.openConnection();
                    buffer = new BufferedInputStream(UrlConnection.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                    break label34;
                }

                while(true) {
                    try {
                        i = buffer.read();
                    } catch (IOException var6) {
                        break;
                    }
                    if (i == -1) {
                        break label35;
                    }
                    var9.append((char)i);
                }
            }
        }

        ret = var9.toString();
        return ret;
    }

    public void onPostExecute(String var1) {
    }

}
