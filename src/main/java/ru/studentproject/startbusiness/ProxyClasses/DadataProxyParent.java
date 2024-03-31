package ru.studentproject.startbusiness.ProxyClasses;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class DadataProxyParent {
    protected HttpURLConnection connection;
    protected JSONObject params;
    protected void createConnection(String urlString) throws IOException {
        final URL url = new URL(urlString);

        connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Authorization","Token "+"30988fd2e27097c5d01458e767448eb886aabf54");
        connection.setConnectTimeout(1000);
        connection.setReadTimeout(1000);
        connection.setDoOutput(true);

    }
    protected void setJSONParameters(String country){
        params = new JSONObject();
        try {
            params.put("query", country);
        }
        catch (JSONException e){
            System.out.println(e);
        }
        System.out.println(params.toString());

    }
    protected  void writeParameters() throws IOException {
        OutputStream os = connection.getOutputStream();
        os.write(params.toString().getBytes("UTF-8"));
        os.close();
        os.flush();
    }


}
