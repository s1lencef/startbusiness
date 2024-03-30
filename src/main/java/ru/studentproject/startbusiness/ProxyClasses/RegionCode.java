package ru.studentproject.startbusiness.ProxyClasses;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import ru.studentproject.startbusiness.models.Subject;
import ru.studentproject.startbusiness.service.SubjectService;
import ru.studentproject.startbusiness.repos.SubjectRepository;

public class RegionCode extends DadataProxyParent{
    @Autowired
    private  SubjectService subjectService;
    @Autowired
    private SubjectRepository subjectRepository;



    public Subject getSubject(String subjectName) throws IOException, JSONException {
        final String urlString = "http://suggestions.dadata.ru/suggestions/api/4_1/rs/suggest/address";
        createConnection(urlString);
        setJSONParameters(subjectName);
        writeParameters();
        return readSubjectFromBuffer();

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

    public static void main(String[] args) {
        RegionCode countryFounder = new RegionCode();
        countryFounder.readSubjectFromBuffer();
    }

}
