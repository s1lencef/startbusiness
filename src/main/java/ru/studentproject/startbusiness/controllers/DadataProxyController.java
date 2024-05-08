package ru.studentproject.startbusiness.controllers;

import jakarta.validation.Valid;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.studentproject.startbusiness.ProxyClasses.FnsByRegion;
import ru.studentproject.startbusiness.dto.SubjectRequestDto;

import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/proxy/dadata")
public class DadataProxyController {
    JSONObject params;

    @PostMapping("/get-fns/region")
    public ResponseEntity<?> getFnsByRegion(@Valid @RequestBody SubjectRequestDto subjectRequestDto, Model model) throws IOException {
        System.out.println("subjectRequestDto = " + subjectRequestDto.getSubjectName());
        String subjectName = subjectRequestDto.getSubjectName();
        FnsByRegion fnsByRegion = new FnsByRegion();


        return ResponseEntity.ok(fnsByRegion.getFnsArray(subjectName));

    };
}
