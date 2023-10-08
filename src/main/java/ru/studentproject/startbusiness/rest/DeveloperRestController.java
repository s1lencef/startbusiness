package ru.studentproject.startbusiness.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/1")
public class DeveloperRestController {
    @GetMapping()
    public int blogWindow(){
        return 1;
    }
}
