package org.vincent.springmvc.controller.springpure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.vincent.springmvc.controller.springpure.validator.UserFormValidator;
import org.vincent.springmvc.dozer.DozerMapper;
import org.vincent.springmvc.dto.incomming.IncomingStudent;
import org.vincent.springmvc.dto.outgoing.OutGoingStudent;
import org.vincent.springmvc.service.StudentService;
import org.vincent.springmvc.system.SystemConstant;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@Profile(SystemConstant.ProfileSpringPure)
@RequestMapping(SystemConstant.FE_SPRING_PURE + "student")
public class SpringPureStudentController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private UserFormValidator userFormValidator;

    private StudentService studentService;

    private DozerMapper dozerMapper;

    @Autowired
    public SpringPureStudentController(StudentService studentService, UserFormValidator userFormValidator, DozerMapper dozerMapper) {
        this.studentService = studentService;
        this.userFormValidator = userFormValidator;
        this.dozerMapper = dozerMapper;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if(binder.getTarget() instanceof IncomingStudent) {
            binder.setValidator(userFormValidator);
        }
    }

    @GetMapping("list")
    public String showAllUsers(Model model) {
        logger.debug("showAllUsers()");
        model.addAttribute("users", studentService.getAllStudents());
        return SystemConstant.FE_SPRING_PURE + "student/list";
    }

    @GetMapping("add")
    public String displayAddStudentPage(Model model) {
        model.addAttribute("userForm", new IncomingStudent());
        return SystemConstant.FE_SPRING_PURE + "student/userform";
    }

    @PostMapping("add")
    public String saveStudent(@ModelAttribute("userForm") @Validated IncomingStudent student, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
        IncomingStudent outStudent = null;
        if(result.hasErrors()) {
            return SystemConstant.FE_SPRING_PURE + "student/userform";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if(student.isNew()){
                redirectAttributes.addFlashAttribute("msg", "User added successfully!");
                outStudent = studentService.addStudent(student);
            }else{
                redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
                outStudent = studentService.updateStudent(student);
            }
            return "redirect:" + outStudent.getId();
        }
    }

    @GetMapping(value = "/{id}/update")
    public String showUpdateUserForm(@PathVariable("id") int id, Model model) {
        logger.debug("showUpdateUserForm() : {}", id);

        OutGoingStudent student = studentService.getStudent(id);
        model.addAttribute("userForm", dozerMapper.map(student, IncomingStudent.class));
        model.addAttribute("testUpdate", "test update value");

        return SystemConstant.FE_SPRING_PURE + "student/userform";
    }

    @GetMapping(value = "/{id}/delete")
    public String deleteUser(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        logger.debug("deleteUser() : {}", id);
        studentService.deleteStudent(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "User is deleted!");
        return "redirect:/" + SystemConstant.FE_SPRING_PURE + "student/list";
    }

    @GetMapping(value = "{id}")
    public String showStudent(@PathVariable("id") int id, Model model) {
        logger.debug("showUser() id: {}", id);
        OutGoingStudent student = studentService.getStudent(id);
        if (student == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "User not found");
        }
        model.addAttribute("student", student);
        return SystemConstant.FE_SPRING_PURE + "student/show";
    }

    @ModelAttribute("frameworkList")
    public List<String> frameworksList() {
        List<String> frameworksList = new ArrayList<>();
        frameworksList.add("Spring MVC");
        frameworksList.add("Struts 2");
        frameworksList.add("JSF 2");
        frameworksList.add("GWT");
        frameworksList.add("Play");
        frameworksList.add("Apache Wicket");
        return frameworksList;
    }

    @ModelAttribute("javaSkillList")
    public Map<String, String> skill() {
        Map<String, String> skill = new LinkedHashMap<>();
        skill.put("Hibernate", "Hibernate");
        skill.put("Spring", "Spring");
        skill.put("Struts", "Struts");
        skill.put("Groovy", "Groovy");
        skill.put("Grails", "Grails");
        return skill;
    }

    @ModelAttribute("numberList")
    public List<Integer> numbers() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        return numbers;
    }

    @ModelAttribute("countryList")
    public Map<String, String> country() {
        Map<String, String> country = new LinkedHashMap<>();
        country.put("US", "United Stated");
        country.put("CN", "China");
        country.put("SG", "Singapore");
        country.put("MY", "Malaysia");
        return country;
    }
}
