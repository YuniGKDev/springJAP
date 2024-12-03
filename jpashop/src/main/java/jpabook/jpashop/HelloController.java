package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")// http://localhost:8080/hello
    public String hello(Model model){
        model.addAttribute("data", "Hello");
        return "hello";// /src/main/resources/templates/hello.html
    }
}
