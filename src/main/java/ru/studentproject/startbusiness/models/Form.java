package ru.studentproject.startbusiness.models;

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Date;

@Entity
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "staff_id")
    private User staff;
    private boolean type;
    private boolean favorites;
    private Date date;
    @ManyToOne(targetEntity = Status.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "status")
    private Status status;
    @ManyToOne(targetEntity = Types.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "tax")
    private Types tax;
    public Form(Long id, User user, User staff, boolean type, Date date, Status status, Types tax) {
        this.id = id;
        this.user = user;
        this.staff = staff;
        this.type = type;
        this.date = date;
        this.status = status;
        this.tax = tax;
    }

    public Form() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getStaff() {
        return staff;
    }

    public void setStaff(User staff) {
        this.staff = staff;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate() {
        Calendar now = Calendar.getInstance();
        this.date = now.getTime();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Types getTax() {
        return tax;
    }

    public void setTax(Types tax) {
        this.tax = tax;
    }

    public boolean isFavorites() {
        return favorites;
    }

    public void setFavorites(boolean favorites) {
        this.favorites = favorites;
    }
}
