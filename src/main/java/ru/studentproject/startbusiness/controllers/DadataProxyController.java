package ru.studentproject.startbusiness.controllers;

import jakarta.validation.Valid;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.studentproject.startbusiness.dto.SubjectRequestDto;

@Controller
@RequestMapping("/proxy/dadata")
public class DadataProxyController {
    JSONObject params;

    @GetMapping("/get-fns/region")
    public ResponseEntity<String> getFnsByRegion(@Valid @RequestBody SubjectRequestDto subjectRequestDto, Model model){
        System.out.println("subjectRequestDto = " + subjectRequestDto.getSubjectName());
        String subjectName = subjectRequestDto.getSubjectName();


        return ResponseEntity.ok("Регион получен");

    };
}
