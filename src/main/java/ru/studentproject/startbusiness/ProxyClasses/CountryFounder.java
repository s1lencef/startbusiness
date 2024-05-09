package ru.studentproject.startbusiness.ProxyClasses;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CountryFounder extends DadataProxyParent {

    public int getCountryCode(String country) throws IOException, JSONException {
        final String urlString = "http://suggestions.dadata.ru/suggestions/api/4_1/rs/suggest/country";
        createConnection(urlString);
        setJSONParameters(country);
        writeParameters();
        return readCodeFromBuffer();

    }
    private int readCodeFromBuffer(){
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(
                connection.getInputStream())))
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

    public static void main(String[] args) {
        try{
            CountryFounder countryFounder = new CountryFounder();
            countryFounder.getCountryCode("Санкт-Петербург");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
