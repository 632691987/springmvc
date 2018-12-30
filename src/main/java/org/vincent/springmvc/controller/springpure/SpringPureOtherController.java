package org.vincent.springmvc.controller.springpure;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vincent.springmvc.controller.springpure.editor.DateEditor;
import org.vincent.springmvc.dto.incomming.OtherDemoDTO;
import org.vincent.springmvc.system.SystemConstant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Profile(SystemConstant.ProfileSpringPure)
@RequestMapping(SystemConstant.FE_SPRING_PURE + "other")
public class SpringPureOtherController {

    /**
     * registerCustomEditor 是说，parameter 进去得时候解释不了得，例如 YYYY-MM-dd 要解释到 OtherDemoDTO 得一个 date 类型中，
     * 那么就用这些 propertyEditor
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    @PostMapping("testdate")
    public String knowTheTestDate(@ModelAttribute("otherDemottt") @Validated OtherDemoDTO dto, Errors errors, Model model) {
        model.addAttribute("date", dto.getDate());
        model.addAttribute("name", dto.getCustomerName());

        return SystemConstant.FE_SPRING_PURE + "other/result";
    }

    @GetMapping("testdate")
    public String knowTheTestDate(Model model) {
        model.addAttribute("otherDemottt", new OtherDemoDTO());
        return SystemConstant.FE_SPRING_PURE + "other/input";
    }

}
