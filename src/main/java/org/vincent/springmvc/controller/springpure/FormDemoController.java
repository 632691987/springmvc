package org.vincent.springmvc.controller.springpure;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vincent.springmvc.system.SystemConstant;

@Controller
@Profile(SystemConstant.ProfileSpringPure)
@RequestMapping("formdemo")
public class FormDemoController {

    @GetMapping
    public String showDemo() {
        return SystemConstant.FE_SPRING_PURE + "form/demo";
    }

    @PostMapping
    public String submitForm() {
        System.out.println("form submit detected");
        return SystemConstant.FE_SPRING_PURE + "form/demo";
    }

}
