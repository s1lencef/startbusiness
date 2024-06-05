package ru.studentproject.startbusiness.repos;

import ru.studentproject.startbusiness.models.Form;
import ru.studentproject.startbusiness.models.User;

import java.util.Comparator;


public class UserComparator implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return o1.getFormsCount()-o2.getFormsCount();
    }
}