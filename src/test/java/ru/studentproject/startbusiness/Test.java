package ru.studentproject.startbusiness;

import org.json.JSONException;
import org.json.JSONObject;
import org.python.antlr.ast.Str;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Test {
    private static int getCountryCode(String country) throws IOException, JSONException {
        final URL url = new URL("http://suggestions.dadata.ru/suggestions/api/4_1/rs/suggest/country");
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Authorization","Token "+"30988fd2e27097c5d01458e767448eb886aabf54");
        con.setConnectTimeout(1000);
        con.setReadTimeout(1000);
        con.setDoOutput(true);

        JSONObject params = new JSONObject();
        try {
            params.put("query", country);
        }
        catch (JSONException e){
            System.out.println(e);
        }
        System.out.println(params.toString());

        OutputStream os = con.getOutputStream();
        os.write(params.toString().getBytes("UTF-8"));
        os.close();
        os.flush();

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(
                con.getInputStream())))
        {
            String line = bf.readLine();
            JSONObject json = new JSONObject(line);
            int code = Integer.parseInt(json.getJSONArray("suggestions").getJSONObject(0).getJSONObject("data").get("code").toString());
            System.out.println(code);
            return code;

        }catch (IOException exception){
            System.out.println(exception);
            return -1;
        }
    }


    public static void main(String[] args) throws IOException, JSONException {
        getCountryCode("Таджикистан");
    }
}

