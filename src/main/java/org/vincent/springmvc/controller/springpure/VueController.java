package org.vincent.springmvc.controller.springpure;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vincent.springmvc.system.SystemConstant;

@Controller
@Profile(SystemConstant.ProfileSpringPure)
@RequestMapping("vue")
public class VueController {

    /**
     * http://localhost:22900/sample/vue/index
     */
    @GetMapping("index")
    public String buildIndex() {
        return SystemConstant.FE_VUE + "index";
    }

}
