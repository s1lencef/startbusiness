package ru.studentproject.startbusiness.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import ru.studentproject.startbusiness.models.Subject;
import ru.studentproject.startbusiness.service.SubjectService;
import ru.studentproject.startbusiness.repos.SubjectRepository;

public class RegionCode {
    @Autowired
    private  SubjectService subjectService;
    @Autowired
    private SubjectRepository subjectRepository;
    HttpURLConnection connection;
    JSONObject params;
    private void createConnection() throws IOException {

        final String urlString = "http://suggestions.dadata.ru/suggestions/api/4_1/rs/suggest/address";
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
    private Subject readSubjectFromBuffer(){
        Subject subject;
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(
                connection.getInputStream())))
        {
            String jsonString = bf.readLine();
            JSONObject json = new JSONObject(jsonString);
            JSONArray suggestions = json.getJSONArray("suggestions");
            JSONObject regionDataWrapper = suggestions.getJSONObject(0);
            JSONObject regionData = regionDataWrapper.getJSONObject("data");
            String regionKLADRId = regionData.getString("region_kladr_id");
            regionKLADRId = regionKLADRId.substring(0,2);

            Long subjectId = Long.parseLong(regionKLADRId);
            String subjectName = regionData.getString("region");

            subject = new Subject(subjectId,subjectName);


            return subject;

        }catch (IOException exception){
            System.out.println(exception);
            return null;
        }
    }

    public Subject getSubject(String subjectName) throws IOException, JSONException {
        createConnection();
        setJSONParameters(subjectName);
        writeParameters();
        return readSubjectFromBuffer();

    }

    public static void main(String[] args) {
        RegionCode countryFounder = new RegionCode();
        countryFounder.readSubjectFromBuffer();
    }

}
