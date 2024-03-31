package ru.studentproject.startbusiness.ProxyClasses;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.studentproject.startbusiness.dto.FnsInfoDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FnsByRegion extends DadataProxyParent{
    public ArrayList<FnsInfoDto> getFnsArray(String region) throws IOException, JSONException {
        final String urlString = "http://suggestions.dadata.ru/suggestions/api/4_1/rs/suggest/fns_unit";
        createConnection(urlString);
        setJSONParameters(region);
        writeParameters();
        return readFnsFromBuffer();

    }
    private ArrayList<FnsInfoDto> readFnsFromBuffer(){
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(
                connection.getInputStream())))
        {
            String line = bf.readLine();
            JSONObject json = new JSONObject(line);

            ArrayList<FnsInfoDto> answers = new ArrayList<FnsInfoDto>();
            JSONArray suggestions = json.getJSONArray("suggestions");
            int length = suggestions.length();
            for (int i = 0; i<length;i++){
                JSONObject fns = suggestions.getJSONObject(i);
                JSONObject data = fns.getJSONObject("data");
                FnsInfoDto fnsInfoDto  = new FnsInfoDto(data);
                answers.add(fnsInfoDto);
            }
            System.out.println(answers);
            return answers;

        }catch (IOException exception){
            System.out.println(exception);
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        try{
            FnsByRegion fnsByRegion = new FnsByRegion();
            fnsByRegion.getFnsArray("Санкт-Петербург");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
