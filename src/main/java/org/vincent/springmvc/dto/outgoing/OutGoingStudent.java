package org.vincent.springmvc.dto.outgoing;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.List;

public class OutGoingStudent {

    // form:hidden - hidden value
    private Integer id;

    // form:input - textbox
    private String name;

    // form:input - textbox
    private String email;

    // form:textarea - textarea
    private String address;

    // form:input - password
    private String password;

    // form:input - password
    private String confirmPassword;

    // form:checkbox - single checkbox
    private boolean newsletter;

    // form:checkboxes - multiple checkboxes
    private List<String> framework;

    // form:radiobutton - radio button
    private String sex;

    // form:radiobuttons - radio button
    private Integer number;

    // form:select - form:option - dropdown - single select
    private String country;

    // form:select - multiple=true - dropdown - multiple select
    private List<String> skill;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isNewsletter() {
        return newsletter;
    }

    public void setNewsletter(boolean newsletter) {
        this.newsletter = newsletter;
    }

    public List<String> getFramework() {
        return framework;
    }

    public void setFramework(List<String> framework) {
        this.framework = framework;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getSkill() {
        return skill;
    }

    public void setSkill(List<String> skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("email", email)
                .add("address", address)
                .add("password", password)
                .add("confirmPassword", confirmPassword)
                .add("newsletter", newsletter)
                .add("framework", framework)
                .add("sex", sex)
                .add("number", number)
                .add("country", country)
                .add("skill", skill)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutGoingStudent that = (OutGoingStudent) o;
        return newsletter == that.newsletter &&
                Objects.equal(id, that.id) &&
                Objects.equal(name, that.name) &&
                Objects.equal(email, that.email) &&
                Objects.equal(address, that.address) &&
                Objects.equal(password, that.password) &&
                Objects.equal(confirmPassword, that.confirmPassword) &&
                Objects.equal(framework, that.framework) &&
                Objects.equal(sex, that.sex) &&
                Objects.equal(number, that.number) &&
                Objects.equal(country, that.country) &&
                Objects.equal(skill, that.skill);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, email, address, password, confirmPassword, newsletter, framework, sex, number, country, skill);
    }
}
