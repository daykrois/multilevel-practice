package com.example.demo01parentchild.controller;

import com.example.demo01parentchild.entity.ParentChild;
import com.example.demo01parentchild.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("get/all")
    public List<ParentChild> getAllPerson(){
//        return personService.getAllPerson();
        return personService.getAllPerson().stream().filter(person->person.getParentId()==null).toList();
    }

    @GetMapping("get/person")
    public List<ParentChild> getPerson(){
        return personService.getPerson();
    }
}
