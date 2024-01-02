package ru.studentproject.startbusiness.service;

import ru.studentproject.startbusiness.models.Form;

import java.util.Comparator;

public class FormComparator implements Comparator<Form> {
    @Override
    public int compare(Form o1, Form o2) {
        if (o1.isFavorites() && !o2.isFavorites()) {
            return -1; // user1 активен, а user2 нет
        } else if (!o1.isFavorites() && o2.isFavorites()) {
            return 1; // user2 активен, а user1 нет
        } else {
            return 0; // Оба пользователя активны или неактивны
        }
    }
}
