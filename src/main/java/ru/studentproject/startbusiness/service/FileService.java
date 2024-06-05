package ru.studentproject.startbusiness.service;

import java.io.File;
import java.io.IOException;
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
import ru.studentproject.startbusiness.models.Form;
import ru.studentproject.startbusiness.models.User;
import ru.studentproject.startbusiness.models.Document;
import ru.studentproject.startbusiness.repos.DocumentRepository;
import ru.studentproject.startbusiness.repos.DocumentTypesRepository;

@Service
public class FileService {
    public static final long SAMPLE = 1L;
    public static final long DOCUMENT = 2L;

    public static final long NO_FORM = 0L;
    @Autowired
    private UserService userService;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentTypesRepository documentTypesRepository;
    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;
    public Path copyLocation;
    public Document newFile;
    private Path getCopyLocation(MultipartFile file){
        String fullPath = uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename());
        return Paths.get(fullPath);
    }
    private void uploadInputStreamToServer(MultipartFile file) throws IOException {
        Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
    }
    private Document createFile(String email){
        Document document = new Document();
        document.setDate();
        document.setUser(userService.findByEmail(email));
        document.setFilePath(copyLocation.toString());
        return document;
    }
    private void printFileData(MultipartFile file){
        System.out.println("copyLocation = " + copyLocation + ", file = " + file);
    }
    private void setFileParams(MultipartFile file, Form form){
        if (form == null){
            newFile.setFormId(NO_FORM);
            newFile.setType(documentTypesRepository.getReferenceById(SAMPLE));
            newFile.setName(file.getOriginalFilename());

        }
        else {
            newFile.setFormId(form.getId());
            newFile.setType(documentTypesRepository.getReferenceById(DOCUMENT));
            newFile.setName(file.getOriginalFilename());
        }
    }

    public void uploadFile(MultipartFile file, String email, Form form) {

        try {

            copyLocation = getCopyLocation(file);

            uploadInputStreamToServer(file);

            printFileData(file);

            newFile = createFile(email);

            setFileParams(file,form);

            save(newFile);

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
        return documentRepository.findByType(documentTypesRepository.getReferenceById(SAMPLE));
    }
    public Collection<Document> getDocuments() {
        return documentRepository.findByType(documentTypesRepository.getReferenceById(DOCUMENT));
    }
    public Document get(Long id){
        return documentRepository.getReferenceById(id);
    }
    public Document save (Document document){
        return documentRepository.save(document);

    }
}
