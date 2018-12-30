package org.vincent.springmvc.dozer.providers;

import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;
import org.vincent.springmvc.data.entity.springpure.Student;
import org.vincent.springmvc.dto.incomming.IncomingStudent;
import org.vincent.springmvc.dto.outgoing.OutGoingStudent;

import java.util.Arrays;
import java.util.Collection;

@Component
public class StudentMappingProvider implements MappingProvider{


    @Override
    public Collection<BeanMappingBuilder> getMapperConfigurations() {
        return Arrays.asList(incomingStudentTOStudentEntityMapping(),studentEntityToOutGoingStudentMapping());
    }

    private BeanMappingBuilder incomingStudentTOStudentEntityMapping() {
        return new BeanMappingBuilder() {

            @Override
            protected void configure() {
                mapping(IncomingStudent.class, Student.class);
            }
        };
    }

    private BeanMappingBuilder studentEntityToOutGoingStudentMapping() {
        return new BeanMappingBuilder() {

            @Override
            protected void configure() {
                mapping(Student.class, OutGoingStudent.class);
            }
        };
    }
}
