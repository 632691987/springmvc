package org.vincent.springmvc.data.repository.springpure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.vincent.springmvc.data.entity.springpure.Student;
import org.vincent.springmvc.data.repository.StudentDAO;
import org.vincent.springmvc.data.repository.springpure.mapper.StudentMapper;
import org.vincent.springmvc.system.SystemConstant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("StudentDAO")
@Profile(SystemConstant.ProfileSpringPure)
public class StudentDAOImpl implements StudentDAO {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM students";
        List<Student> result = namedParameterJdbcTemplate.query(sql, new StudentMapper());
        return result;
    }

    @Override
    public Student getStudent(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        String sql = "select * from students where id = :id";

        Student student = null;

        try {
            student = namedParameterJdbcTemplate.queryForObject(sql, params, new StudentMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.error("Exception happen: id = {}, message = {}", id, e.getMessage());
        }

        return student;
    }

    @Override
    public Student addStudent(Student student) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO students(NAME, EMAIL, ADDRESS, PASSWORD, NEWSLETTER, FRAMEWORK, SEX, NUMBER, COUNTRY, SKILL) "
                + "VALUES ( :name, :email, :address, :password, :newsletter, :framework, :sex, :number, :country, :skill)";
        try {
            namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(student), keyHolder);
            student.setId(keyHolder.getKey().intValue());
        } catch (DataAccessException e) {
            logger.error("Exeption happen: student = {}, message = {}", student.toString(), e.getMessage());
            return null;
        }
        return student;
    }

    @Override
    public Student updateStudent(Student student) {
        String sql = "UPDATE students SET NAME=:name, EMAIL=:email, ADDRESS=:address, " + "PASSWORD=:password, NEWSLETTER=:newsletter, FRAMEWORK=:framework, "
                + "SEX=:sex, NUMBER=:number, COUNTRY=:country, SKILL=:skill WHERE id=:id";
        try {
            namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(student));
        } catch (DataAccessException e) {
            logger.error("Exeption happen: student = {}, message = {}", student.toString(), e.getMessage());
            return null;
        }
        return student;
    }

    @Override
    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id= :id";
        try {
            namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
        } catch (DataAccessException e) {
            logger.error("Exeption happen: id = {}, message = {}", id, e.getMessage());
            return false;
        }
        return true;
    }

    private SqlParameterSource getSqlParameterByModel(Student student) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", student.getId());
        paramSource.addValue("name", student.getName());
        paramSource.addValue("email", student.getEmail());
        paramSource.addValue("address", student.getAddress());
        paramSource.addValue("password", student.getPassword());
        paramSource.addValue("newsletter", student.isNewsletter());
        paramSource.addValue("framework", convertListToDelimitedString(student.getFramework()));
        paramSource.addValue("sex", student.getSex());
        paramSource.addValue("number", student.getNumber());
        paramSource.addValue("country", student.getCountry());
        paramSource.addValue("skill", convertListToDelimitedString(student.getSkill()));
        return paramSource;
    }

    private String convertListToDelimitedString(List<String> list) {
        String result = "";
        if (list != null) {
            result = StringUtils.arrayToCommaDelimitedString(list.toArray());
        }
        return result;
    }
}
