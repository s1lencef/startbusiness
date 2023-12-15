package ru.studentproject.startbusiness.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
import ru.studentproject.startbusiness.models.DocumentTypes;
import ru.studentproject.startbusiness.service.FileService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    @GetMapping("/samples")
    public String downloadPage (Model model, HttpServletRequest request){
        List<Document> documents = fileService.getSamples().stream().toList();
        ArrayList<DocumentTypes> types = new ArrayList<>();
        if (documents.isEmpty()){
            model.addAttribute("documents", "Документов не найдено");

        }
        else{
            for(Document doc:documents){
                types.add(doc.isType());
            }
            model.addAttribute("documents", documents);
            model.addAttribute("types",types);
        }
        return "samples";
    }
    @GetMapping("/download")
    public void dowloadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true) Long id) throws IOException {
        Document doc = fileService.get(id);
        System.out.println("id = " + id + ", doc = "+doc.toString()) ;
        Path file = Paths.get(doc.getFilePath());

        // Get the media type of the file
        String contentType = Files.probeContentType(file);
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        response.setContentType(contentType);

        response.setContentLengthLong(Files.size(file));

        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                .filename(file.getFileName().toString(), StandardCharsets.UTF_8)
                .build()
                .toString());

        Files.copy(file, response.getOutputStream());
        response.flushBuffer();


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
