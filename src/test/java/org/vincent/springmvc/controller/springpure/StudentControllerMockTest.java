package org.vincent.springmvc.controller.springpure;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.vincent.springmvc.ApplicationConfig;
import org.vincent.springmvc.dto.outgoing.OutGoingStudent;
import org.vincent.springmvc.service.StudentService;
import org.vincent.springmvc.system.SystemConstant;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * 这个test 是带有 Mock 功能得，能够另Controller
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@TestPropertySource("classpath:application.springpure.test.properties")
@ActiveProfiles(SystemConstant.ProfileSpringPure)
@Profile(SystemConstant.ProfileSpringPure)
public class StudentControllerMockTest {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private SpringPureStudentController controller;

    private String controller_url_base = "/" + SystemConstant.FE_SPRING_PURE + "student";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void testMockList() throws Exception {
        List<OutGoingStudent> studentList = new ArrayList<>();
        studentList.add(outGoingStudent("abc"));
        studentList.add(outGoingStudent("def"));
        studentList.add(outGoingStudent("def"));
        studentList.add(outGoingStudent("def"));
        when(studentService.getAllStudents()).thenReturn(studentList);

        mockMvc.perform(get(controller_url_base + "/list"))
                .andExpect(status().isOk())
                .andExpect(view().name(SystemConstant.FE_SPRING_PURE + "student/list"))
                .andExpect(model().attribute("users", hasSize(studentList.size())))
                .andReturn();
    }

    private OutGoingStudent outGoingStudent(String name) {
        OutGoingStudent student = new OutGoingStudent();
        student.setName(name);
        return student;
    }
}
