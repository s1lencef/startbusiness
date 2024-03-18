package ru.studentproject.startbusiness.config;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CountryFounder {
    HttpURLConnection connection;
    JSONObject params;
    private void createConnection() throws IOException {

        final String urlString = "http://suggestions.dadata.ru/suggestions/api/4_1/rs/suggest/country";
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
    private void setJSONParameters(String country){
        params = new JSONObject();
        try {
            params.put("query", country);
        }
        catch (JSONException e){
            System.out.println(e);
        }
        System.out.println(params.toString());

    }
    private void writeParameters() throws IOException {
        OutputStream os = connection.getOutputStream();
        os.write(params.toString().getBytes("UTF-8"));
        os.close();
        os.flush();
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

    public int getCountryCode(String country) throws IOException, JSONException {
        createConnection();
        setJSONParameters(country);
        writeParameters();
        return readCodeFromBuffer();

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
