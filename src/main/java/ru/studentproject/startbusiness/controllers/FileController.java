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
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.studentproject.startbusiness.models.Document;
import ru.studentproject.startbusiness.models.DocumentTypes;
import ru.studentproject.startbusiness.models.Form;
import ru.studentproject.startbusiness.repos.DocumentRepository;
import ru.studentproject.startbusiness.service.FileService;
import ru.studentproject.startbusiness.service.FormService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller

public class FileController {

    @Autowired
    FileService fileService;
    @Autowired
    FormService formService;
    @Autowired
    DocumentRepository documentRepository;
    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";
    }

//    @PostMapping("/upload")
//    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email =  authentication.getName();
//        fileService.uploadFile(file,email);
//
//
//        System.out.println("name = "+email);
//        redirectAttributes.addFlashAttribute("filename","файл "+file.getOriginalFilename()+" успешно загружен!");
//
//        return "redirect:/upload?success";
//    }
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
        try {
            response.setContentType(contentType);

            response.setContentLengthLong(Files.size(file));
        }
        catch (NoSuchFileException e){
            System.out.println(e);

        }
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                .filename(file.getFileName().toString(), StandardCharsets.UTF_8)
                .build()
                .toString());

        Files.copy(file, response.getOutputStream());
        response.flushBuffer();

    }
    @GetMapping("/download/all")
    public void dowloadAllFiles(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true) Long id) throws IOException {
        Form form = formService.get(id);
        List<Document> documents = documentRepository.findByForm(form.getId());
        ArrayList<Path> filesToDownload =  new ArrayList<>();
        for(Document doc:documents){
            filesToDownload.add(Paths.get(doc.getFilePath()));
        }
        String filename = form.getId()+" - "+form.getTax().getName()+".zip";
        response.setContentType("application/zip"); // zip archive format
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                .filename(filename, StandardCharsets.UTF_8)
                .build()
                .toString());
        try(ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())){
            for (Path file : filesToDownload) {
                try (InputStream inputStream = Files.newInputStream(file)) {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getFileName().toString()));
                    StreamUtils.copy(inputStream, zipOutputStream);
                    zipOutputStream.flush();
                }
            }
        }
    }

    @PostMapping("/upload")
    public String uploadFiles(@RequestParam("files") MultipartFile[] files, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email =  authentication.getName();
        System.out.println("files = " + Arrays.toString(files) + ", redirectAttributes = " + redirectAttributes);
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                long fileSize = file.getSize();
                fileService.uploadFile(file,email);
                System.out.println("Загружен файл: " + fileName);
                System.out.println("Размер файла: " + fileSize + " байт");
            }
        }
        redirectAttributes.addFlashAttribute("filename",
                "You successfully uploaded all files!");

        return "redirect:/upload?success";


    }

}