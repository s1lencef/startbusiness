package ru.studentproject.startbusiness.config;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CountryFounder {
    public static int getCountryCode(String country) throws IOException, JSONException {

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

}
