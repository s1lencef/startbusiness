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
import ru.studentproject.startbusiness.models.User;
import ru.studentproject.startbusiness.repos.DocumentRepository;
import ru.studentproject.startbusiness.service.FileService;
import ru.studentproject.startbusiness.service.FormService;
import ru.studentproject.startbusiness.service.UserService;

import javax.print.Doc;
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

    @Autowired
    UserService userService;
    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";
    }

    private String getContentType(Path file)  {
        try {
            String contentType = Files.probeContentType(file);
            if (contentType == null) {
                throw new IOException ("Пустой контент");
            }
            return contentType;
        }
        catch (IOException e){
            return MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

    }
    private void setResponseHeader(HttpServletResponse response, Path file){
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                .filename(file.getFileName().toString(), StandardCharsets.UTF_8)
                .build()
                .toString());
    }
    private Path getFileToDownload(Long id){
        Document doc = fileService.get(id);

        System.out.println("id = " + id + ", doc = "+doc.toString()) ;

         return Paths.get(doc.getFilePath());
    }
    private void setResponseContent(HttpServletResponse response, Path file){
        String contentType = getContentType(file);

        try {
            response.setContentType(contentType);

            response.setContentLengthLong(Files.size(file));
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
    private ArrayList<Path> getFilesToDownload(List<Document> documents){
        ArrayList<Path> files =  new ArrayList<>();

        for(Document doc:documents){
            files.add(Paths.get(doc.getFilePath()));
        }
        return files;
    }
    private String createFilaNameForZip(Form form){
        return form.getId()+" - "+form.getTax().getName()+".zip";
    }
    private void setResponseParamsForZip(HttpServletResponse response, String filename){
        response.setContentType("application/zip");

        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                .filename(filename, StandardCharsets.UTF_8)
                .build()
                .toString());
    }
    private void downloadAsZip(HttpServletResponse response,ArrayList<Path> filesToDownload){
        try(ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())){
            for (Path file : filesToDownload) {
                try (InputStream inputStream = Files.newInputStream(file)) {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getFileName().toString()));
                    StreamUtils.copy(inputStream, zipOutputStream);
                    zipOutputStream.flush();
                }
            }
        } catch (IOException e) {
            System.out.println("Не удалось скачать");
        }
    }
    private void printFileInfo(MultipartFile file){
        System.out.println("Загружен файл: " + file.getOriginalFilename());
        System.out.println("Размер файла: " + file.getSize() + " байт");
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
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true) Long id) throws IOException {

        Path file = getFileToDownload(id);

        setResponseContent(response,file);

        setResponseHeader(response, file);

        Files.copy(file, response.getOutputStream());

        response.flushBuffer();

    }
    @GetMapping("/download/all")
    public void downloadAllFiles(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true) Long id) {
        Form form = formService.get(id);

        List<Document> documents =  documentRepository.findByForm(form.getId());

        ArrayList<Path> filesToDownload =  getFilesToDownload(documents);

        String filename = createFilaNameForZip(form);

        setResponseParamsForZip(response, filename);

        downloadAsZip(response,filesToDownload);
    }

    @PostMapping("/upload")
    public String uploadFiles(@RequestParam("files") MultipartFile[] files, RedirectAttributes redirectAttributes) {
        User currUser = userService.getAuthenticatedUser();

        System.out.println("files = " + Arrays.toString(files) + ", redirectAttributes = " + redirectAttributes);

        for (MultipartFile file : files) {

            if (!file.isEmpty()) {
                fileService.uploadFile(file,currUser.getEmail(),null);
                printFileInfo(file);

            }
        }
        redirectAttributes.addFlashAttribute("filename",
                "You successfully uploaded all files!");

        return "redirect:/upload?success";


    }

}