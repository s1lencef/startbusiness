package ru.studentproject.startbusiness.models;
import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Date;


@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;



    private String filePath;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    private Long formId;
    @ManyToOne(targetEntity = DocumentTypes.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "type")
    private DocumentTypes type;
    @Column(nullable = false)
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public DocumentTypes isType() {
        return type;
    }

    public void setType(DocumentTypes type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate() {
        Calendar now = Calendar.getInstance();
        this.date = now.getTime();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
