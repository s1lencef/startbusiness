package ru.studentproject.startbusiness.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.studentproject.startbusiness.exceptions.FileStorageException;
import ru.studentproject.startbusiness.models.User;
import ru.studentproject.startbusiness.models.Document;
import ru.studentproject.startbusiness.repos.DocumentRepository;

@Service
public class FileService {
    @Autowired
    private UserService userService;
    @Autowired
    private DocumentRepository documentRepository;
    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    public void uploadFile(MultipartFile file, String email) {

        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("copyLocation = " + copyLocation + ", file = " + file);
            Document newFile = new Document();
            User user = userService.findByEmail(email);
            newFile.setFilePath(copyLocation.toString());
            newFile.setDate();
            newFile.setFormId(0L);
            newFile.setType(false);
            newFile.setUser(user);
            newFile.setName(file.getOriginalFilename());
            documentRepository.save(newFile);

        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }
    }
    public List<Document> getAll() {
        return documentRepository.findAll();
    }
    public Collection<Document> getSamples() {
        return documentRepository.findSamples();
    }
    public Collection<Document> getDocuments() {
        return documentRepository.findDocuments();
    }
    public Document get(Long id){
        return documentRepository.getReferenceById(id);
    }
}
