package ru.studentproject.startbusiness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.studentproject.startbusiness.service.FileService;

@Controller
@RequestMapping("/upload")
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping
    public String index() {
        return "upload";
    }

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email =  authentication.getName();
        fileService.uploadFile(file,email);


        System.out.println("name = "+email);
        redirectAttributes.addFlashAttribute("filename","файл "+file.getOriginalFilename()+" успешно загружен!");

        return "redirect:/upload?success";
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
