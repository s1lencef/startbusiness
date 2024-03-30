package ru.studentproject.startbusiness.ProxyClasses;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FnsByRegion extends DadataProxyParent{
    public int getFnsArray(String region) throws IOException, JSONException {
        final String urlString = "http://suggestions.dadata.ru/suggestions/api/4_1/rs/suggest/fns_unit";
        createConnection(urlString);
        setJSONParameters(region);
        writeParameters();
        return readFnsFromBuffer();

    }
    private int readFnsFromBuffer(){
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(
                connection.getInputStream())))
        {
            String line = bf.readLine();
            JSONObject json = new JSONObject(line);
            //Тут дописать логику обработки массива (Делаем свой из номера и адреса)
            int code = Integer.parseInt(json.getJSONArray("suggestions").getJSONObject(0).getJSONObject("data").get("code").toString());
            System.out.println(code);
            return code;

        }catch (IOException exception){
            System.out.println(exception);
            return -1;
        }
    }
}
