package org.vincent.springmvc.controller.springpure.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.vincent.springmvc.dto.incomming.IncomingStudent;

@Component
public class UserFormValidator implements Validator {

    @Autowired
    private EmailValidator emailValidator;

    @Override
    public boolean supports(Class<?> clazz) {
        return IncomingStudent.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        IncomingStudent student = (IncomingStudent)target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.userForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.userForm.address");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword","NotEmpty.userForm.confirmPassword");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", "NotEmpty.userForm.sex");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "NotEmpty.userForm.country");

        if(!emailValidator.valid(student.getEmail())){
            errors.rejectValue("email", "Pattern.userForm.email");
        }

        if(student.getNumber()==null || student.getNumber()<=0){
            errors.rejectValue("number", "NotEmpty.userForm.number");
        }

        if(student.getCountry().equalsIgnoreCase("none")){
            errors.rejectValue("country", "NotEmpty.userForm.country");
        }

        if (!student.getPassword().equals(student.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Diff.userform.confirmPassword");
        }

        if (student.getFramework() == null || student.getFramework().size() < 2) {
            errors.rejectValue("framework", "Valid.userForm.framework");
        }

        if (student.getSkill() == null || student.getSkill().size() < 3) {
            errors.rejectValue("skill", "Valid.userForm.skill");
        }

    }
}
