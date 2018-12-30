package org.vincent.springmvc.data.entity.springpure;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.List;

public class Student {

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

    public boolean isNew() {
        return (this.id == null);
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return newsletter == student.newsletter &&
                Objects.equal(id, student.id) &&
                Objects.equal(name, student.name) &&
                Objects.equal(email, student.email) &&
                Objects.equal(address, student.address) &&
                Objects.equal(password, student.password) &&
                Objects.equal(framework, student.framework) &&
                Objects.equal(sex, student.sex) &&
                Objects.equal(number, student.number) &&
                Objects.equal(country, student.country) &&
                Objects.equal(skill, student.skill);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, email, address, password, newsletter, framework, sex, number, country, skill);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("email", email)
                .add("address", address)
                .add("password", password)
                .add("newsletter", newsletter)
                .add("framework", framework)
                .add("sex", sex)
                .add("number", number)
                .add("country", country)
                .add("skill", skill)
                .toString();
    }
}
