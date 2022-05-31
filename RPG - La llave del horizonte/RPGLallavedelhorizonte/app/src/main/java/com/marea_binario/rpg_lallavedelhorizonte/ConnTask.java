package com.marea_binario.rpg_lallavedelhorizonte;

import android.os.AsyncTask;
import android.util.Log;

import com.marea_binario.rpg_lallavedelhorizonte.Data.Data;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ConnTask extends AsyncTask {
    private final String url;
    private String ret;

    public ConnTask(String url) {
        this.url = url;
    }

    protected Object doInBackground(Object[] objects) {

        int i;

        String var9 = new String();

        label35: {
            label34: {
                BufferedInputStream buffer;
                try {
                    Log.e("TAG", "doInBackground: " + Data.URL + url);
                    URL url = new URL(Data.URL + this.url);
                    HttpURLConnection UrlConnection = (HttpURLConnection)url.openConnection();
                    UrlConnection.setRequestProperty("Accept-Charset", "UTF-16");
                    UrlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-16");
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
                    var9 += (char)i;
                }
            }
        }

        ret = new String(var9.getBytes(), StandardCharsets.UTF_8);
        return ret;
    }

    public void onPostExecute(String var1) {
    }

}
