package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.domain.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class PageController {

    @GetMapping("/thymleaf/example")
    public String thymleafExample(Model model){
        Person person = Person.builder()
                .id(1L)
                .name("정지원")
                .age(20)
                .hobbies(Arrays.asList("운동", "독서", "악기 연주")).build();

        model.addAttribute("person",person);

        return "examplePage";
    }
}
