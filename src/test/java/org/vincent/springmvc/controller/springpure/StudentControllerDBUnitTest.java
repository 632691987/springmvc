package org.vincent.springmvc.controller.springpure;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.vincent.springmvc.ApplicationConfig;
import org.vincent.springmvc.dto.incomming.IncomingStudent;
import org.vincent.springmvc.dto.outgoing.OutGoingStudent;
import org.vincent.springmvc.service.StudentService;
import org.vincent.springmvc.system.SystemConstant;

import java.util.List;
import java.util.Random;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@TestPropertySource("classpath:application.springpure.test.properties")
@ActiveProfiles(profiles = {SystemConstant.ProfileSpringPure, SystemConstant.ProfileTest})
@Profile(SystemConstant.ProfileSpringPure)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:StudentControllerDBUnitTest.testDatabaseSetup.xml")
public class StudentControllerDBUnitTest {

	@Autowired
	private StudentService studentService;

	private static final int INITIAL_RECORDS_COUNT = 6;

	@Test
	@DatabaseTearDown
	public void testSelectStudents() {
		List<OutGoingStudent> students = studentService.getAllStudents();
		Assert.assertThat(students, Matchers.hasSize(INITIAL_RECORDS_COUNT));
	}

	@Test
	@DatabaseTearDown
	public void testSelectSingleStudent() {
		List<OutGoingStudent> students = studentService.getAllStudents();
		for(OutGoingStudent student : students) {
			OutGoingStudent singleStudent = studentService.getStudent(student.getId());
			Assert.assertThat(singleStudent, Matchers.notNullValue());
			Assert.assertThat(singleStudent, Matchers.equalTo(student));
		}
	}

	@Test
	@DatabaseTearDown
	public void testInsertStudent() {
		List<OutGoingStudent> students = studentService.getAllStudents();
		Assert.assertThat(students, Matchers.hasSize(INITIAL_RECORDS_COUNT));

		IncomingStudent student = givenNewCorrectStudent();
		studentService.addStudent(student);

		students = studentService.getAllStudents();
		Assert.assertThat(students, Matchers.hasSize(INITIAL_RECORDS_COUNT+1));
	}

	@Test
	@DatabaseTearDown
	public void testDeleteStudent() {
		List<OutGoingStudent> students = studentService.getAllStudents();
		Assert.assertThat(students, Matchers.hasSize(INITIAL_RECORDS_COUNT));

		int initialCount = INITIAL_RECORDS_COUNT;
		do {
			OutGoingStudent student = students.get(initialCount -1);
			studentService.deleteStudent(student.getId());
			initialCount--;
		} while (initialCount > 0);

		students = studentService.getAllStudents();
		Assert.assertThat(students, Matchers.empty());
	}

	@Test
	@DatabaseTearDown
	public void testUpdateStudent() {
		List<OutGoingStudent> students = studentService.getAllStudents();
		Assert.assertThat(students, Matchers.hasSize(INITIAL_RECORDS_COUNT));

		int studentIndex = new Random().nextInt(INITIAL_RECORDS_COUNT);
		OutGoingStudent student = students.get(studentIndex);

		IncomingStudent incomingStudent = givenNewCorrectStudent();
		incomingStudent.setId(student.getId());
		studentService.updateStudent(incomingStudent);

		OutGoingStudent retrieveStudent = studentService.getStudent(student.getId());
		Assert.assertThat(retrieveStudent.getAddress(), Matchers.equalTo(incomingStudent.getAddress()));
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

}
