package ru.studentproject.startbusiness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.studentproject.startbusiness.models.Document;
import ru.studentproject.startbusiness.service.FileService;

import java.util.*;

@Controller

public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email =  authentication.getName();
        fileService.uploadFile(file,email);


        System.out.println("name = "+email);
        redirectAttributes.addFlashAttribute("filename","файл "+file.getOriginalFilename()+" успешно загружен!");

        return "redirect:/upload?success";
    }
    @GetMapping("/download")
    public String downloadPage (Model model){
        List<Document> documents = fileService.getSamples().stream().toList();
        if (documents.isEmpty()){
            model.addAttribute("documents", "Документов не найдено");
        }
        else{
            model.addAttribute("documents", documents);
        }
        return "download";
    }

//    @PostMapping("/upload-files")
//    public String uploadFiles(@RequestParam("files") MultipartFile[] files, RedirectAttributes redirectAttributes) {
//
//        Arrays.asList(files)
//                .stream()
//                .forEach(file -> fileService.uploadFile(file));
//
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded all files!");
//
//        return "redirect:/";
//    }
}
