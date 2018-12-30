package org.vincent.springmvc.controller.springpure;

import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;
import org.vincent.springmvc.ApplicationConfig;
import org.vincent.springmvc.dto.incomming.IncomingStudent;
import org.vincent.springmvc.dto.outgoing.OutGoingStudent;
import org.vincent.springmvc.system.SystemConstant;

import javax.servlet.ServletContext;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@TestPropertySource("classpath:application.springpure.test.properties")
@ActiveProfiles(profiles = {SystemConstant.ProfileSpringPure, SystemConstant.ProfileTest})
@Profile(SystemConstant.ProfileSpringPure)
public class StudentControllerE2ETest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private String controller_url_base = "/" + SystemConstant.FE_SPRING_PURE + "student";

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    /**
     * 必须有得，用与测试 Profile 是否已经对应上，激活上
     */
    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertThat(servletContext, Matchers.notNullValue());
        assertTrue(servletContext instanceof MockServletContext);
        Assert.assertThat(wac.getBean("springPureStudentController"), Matchers.notNullValue());
    }

    /**
     * 测试 CRUD 的
     */
    @Test
    public void givenURL_whenMockMVC_thenReturnsJSPViewName() throws Exception {
        Map<String, String> viewMappingMap = new HashMap<>();
        viewMappingMap.put(controller_url_base + "/list", SystemConstant.FE_SPRING_PURE + "student/list");
        viewMappingMap.put(controller_url_base + "/add", SystemConstant.FE_SPRING_PURE + "student/userform");

        for (Map.Entry<String, String> viewEntry : viewMappingMap.entrySet()) {
            this.mockMvc.perform(get(viewEntry.getKey())).andExpect(view().name(viewEntry.getValue()));
        }
    }

    /**
     * 测试含有整个 Entity 输入的
     * 这个测试是在太太经典了，甘都得！
     */
    @Test
    public void givenCorrectStudent_whenAddStrudent_thenReturnNormalview() throws Exception {
        IncomingStudent student = givenNewCorrectStudent();
        String url = controller_url_base + "/add";

        this.mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", student.getName())
                .param("email", student.getEmail())
                .param("address", student.getAddress())
                .param("password", student.getPassword())
                .param("confirmPassword", student.getConfirmPassword())
                .param("newsletter", Boolean.toString(student.isNewsletter()))
                .param("framework[0]", student.getFramework().get(0))
                .param("framework[1]", student.getFramework().get(1))
                .param("framework[2]", student.getFramework().get(2))
                .param("sex", student.getSex())
                .param("number", student.getNumber().toString())
                .param("country", student.getCountry())
                .param("skill[0]", student.getSkill().get(0))
                .param("skill[1]", student.getSkill().get(1))
                .param("skill[2]", student.getSkill().get(2))
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("msg", is("User added successfully!")))
                .andExpect(view().name(startsWith("redirect:")));
    }

    @Test
    public void givenCorrectStudent_whenAddStrudent_thenReturnNormalview2() throws Exception {
        IncomingStudent student = givenNewCorrectStudent();
        String url = controller_url_base + "/add";

        this.mockMvc.perform(MockMvcRequestBuilderUtils.postForm(url, student))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("msg", is("User added successfully!")))
                .andExpect(view().name(Matchers.startsWith("redirect:")));
    }

    @Test
    public void givenId_whenUpdateStudent_thenReturn() throws Exception {
        String selectURL = controller_url_base + "/list";

        MvcResult mvcResult = this.mockMvc.perform(get(selectURL)).andReturn();
        Map<String, Object> maps = mvcResult.getModelAndView().getModel();
        List<OutGoingStudent> outGoingStudents = (List<OutGoingStudent>) maps.get("users");

        int userId = outGoingStudents.get(0).getId();
        String updateURL = controller_url_base + "/{0}/update";
        String resultURL = SystemConstant.FE_SPRING_PURE + "student/userform";

        MessageFormat messageFormat = new MessageFormat(updateURL);
        String strUpdateURL = messageFormat.format(new Object[]{userId});

        this.mockMvc
                .perform(get(strUpdateURL))
                .andExpect(model().attribute("testUpdate", is("test update value")))
                .andExpect(view().name(resultURL));
    }

    private IncomingStudent givenNewCorrectStudent() {
        IncomingStudent incomingStudent = new IncomingStudent();
        incomingStudent.setAddress("this is a new address");
        incomingStudent.setConfirmPassword("this is password");
        incomingStudent.setPassword("this is password");
        incomingStudent.setCountry("Singapore");
        incomingStudent.setEmail("email@email.com");
        incomingStudent.setFramework(Lists.newArrayList("Spring MVC", "JSF 2", "GWT"));
        incomingStudent.setName("this is name");
        incomingStudent.setNewsletter(true);
        incomingStudent.setSex("M");
        incomingStudent.setNumber(4);
        incomingStudent.setSkill(Lists.newArrayList("Struts", "Spring", "Hibernate"));
        return incomingStudent;
    }

    /**
     * Test select original list
     * <p>
     * More than 3 is because above test already add one
     * <p>
     * assume this should change if use dbunit component
     */
    @Test
    public void testSelectOriginalList() throws Exception {
        String selectURL = controller_url_base + "/list";
        int ORIGINAL_DB_INIT_ENTITY_COUNT = 3;

        this.mockMvc.perform(get(selectURL)).andExpect(model().attribute("users", Matchers.not(Matchers.lessThanOrEqualTo(ORIGINAL_DB_INIT_ENTITY_COUNT))));
    }

    /**
     * Test select existing single
     */
    @Test
    public void testSelectExistingStudent() throws Exception {
        String selectURL = controller_url_base + "/list";

        MvcResult mvcResult = this.mockMvc.perform(get(selectURL)).andReturn();
        Map<String, Object> maps = mvcResult.getModelAndView().getModel();
        List<OutGoingStudent> outGoingStudents = (List<OutGoingStudent>) maps.get("users");

        int existingStudentID = outGoingStudents.get(0).getId();
        selectURL = controller_url_base + "/" + existingStudentID;
        this.mockMvc.perform(get(selectURL)).andExpect(model().attribute("student", Matchers.notNullValue()));
    }

    /**
     * Test insert new student
     */
    @Test
    public void testAddNewStudent() throws Exception {
        String selectURL = controller_url_base + "/list";

        //Before add, check how many students
        MvcResult mvcResult = this.mockMvc.perform(get(selectURL)).andReturn();
        Map<String, Object> maps = mvcResult.getModelAndView().getModel();
        List<OutGoingStudent> outGoingStudents = (List<OutGoingStudent>) maps.get("users");
        int sizeCountBeforeAdd = outGoingStudents.size();

        // add one more student
        givenCorrectStudent_whenAddStrudent_thenReturnNormalview2();

        //After add, check how many students
        mvcResult = this.mockMvc.perform(get(selectURL)).andReturn();
        maps = mvcResult.getModelAndView().getModel();
        outGoingStudents = (List<OutGoingStudent>) maps.get("users");
        int sizeCountAfterAdd = outGoingStudents.size();

        assertTrue("Should have 1 more entry after add student", sizeCountBeforeAdd + 1 == sizeCountAfterAdd);
    }

    /**
     * Test update existing student, and validate by get signal
     */
    @Test
    public void testUpdateExistingStudent() throws Exception {
        String selectURL = controller_url_base + "/list";
        String InsertURL = controller_url_base + "/add";

        // add one student
        IncomingStudent student = givenNewCorrectStudent();
        this.mockMvc.perform(MockMvcRequestBuilderUtils.postForm(InsertURL, student));

        // fetch last student
        MvcResult mvcResult = this.mockMvc.perform(get(selectURL)).andReturn();
        Map<String, Object> maps = mvcResult.getModelAndView().getModel();
        List<OutGoingStudent> outGoingStudents = (List<OutGoingStudent>) maps.get("users");
        OutGoingStudent lastStudent = outGoingStudents.get(outGoingStudents.size() - 1);

        // update last student, and post
        student.setId(lastStudent.getId());
        student.setName("a new name");
        student.setAddress("a new address");
        student.setEmail("anewemail@email.com");
        this.mockMvc.perform(MockMvcRequestBuilderUtils.postForm(InsertURL, student));

        // validate the last student
        selectURL = controller_url_base + "/" + lastStudent.getId();
        mvcResult = this.mockMvc.perform(get(selectURL)).andReturn();
        maps = mvcResult.getModelAndView().getModel();
        OutGoingStudent updatedStudent = (OutGoingStudent) maps.get("student");
        Assert.assertThat("name should already changed", updatedStudent.getName(), Matchers.equalTo("a new name"));
        Assert.assertThat("address should already changed", updatedStudent.getAddress(), Matchers.equalTo("a new address"));
        Assert.assertThat("email should already changed", updatedStudent.getEmail(), Matchers.equalTo("anewemail@email.com"));
    }

    /**
     * Test delete, and validate by select original list
     */
    @Test
    public void testDeleteStudent() throws Exception {
        //select students
        String selectURL = controller_url_base + "/list";
        String deleteURL = controller_url_base + "/{0}/delete";

        //Before add, check how many students
        MvcResult mvcResult = this.mockMvc.perform(get(selectURL)).andReturn();
        Map<String, Object> maps = mvcResult.getModelAndView().getModel();
        List<OutGoingStudent> outGoingStudents = (List<OutGoingStudent>) maps.get("users");
        int step1_students_count = outGoingStudents.size();

        //add one students
        givenCorrectStudent_whenAddStrudent_thenReturnNormalview();

        //select students
        mvcResult = this.mockMvc.perform(get(selectURL)).andReturn();
        maps = mvcResult.getModelAndView().getModel();
        outGoingStudents = (List<OutGoingStudent>) maps.get("users");
        int step2_students_count = outGoingStudents.size();
        Assert.assertTrue(step1_students_count + 1 == step2_students_count);

        //delete this students
        int aStudentID = outGoingStudents.get(0).getId();
        MessageFormat messageFormat = new MessageFormat(deleteURL);
        String strDeleteURL = messageFormat.format(new Object[]{aStudentID});
        this.mockMvc.perform(get(strDeleteURL));

        //select students
        mvcResult = this.mockMvc.perform(get(selectURL)).andReturn();
        maps = mvcResult.getModelAndView().getModel();
        outGoingStudents = (List<OutGoingStudent>) maps.get("users");
        int step3_students_count = outGoingStudents.size();
        Assert.assertTrue(step1_students_count == step3_students_count);
    }
}
