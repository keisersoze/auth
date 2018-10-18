package bootwildfly.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWildFlyController {


    @RequestMapping("hello")
    public String sayHello(){
        return ("Hi Kennedy");
    }

    @RequestMapping("prova")
    public String prova(){
        return ("...");
    }
}