package edu.baylor.cs.se.hibernate;

import edu.baylor.cs.se.hibernate.model.Team;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExampleTest {

    @Autowired
    private TestEntityManager entityManager;

//    @Test
//    //simple test
//    public void demoTest(){
//        Teacher teacher = new Teacher();
//        teacher.setEmail("email@email.com");
//        teacher.setFirstName("John");
//        teacher.setLastName("Roe");
//        teacher.setTelephoneNumber("5419791876");
//        entityManager.persist(teacher);
//        Teacher dbTeacher = (Teacher)entityManager.getEntityManager().createQuery("SELECT t FROM Teacher t WHERE t.firstName LIKE 'John' ").getResultList().get(0);
//        assertThat(teacher.getFirstName()).isEqualToIgnoringCase(dbTeacher.getFirstName());
//    }
//
//    @Test
//    //tests that email validation works
//    public void anotherDemoTest(){
//        Teacher teacher = new Teacher();
//        teacher.setEmail("hahaWrongEmail");
//        teacher.setFirstName("John");
//        teacher.setLastName("Roe");
//        teacher.setTelephoneNumber("549791876");
//        assertThatThrownBy(() -> { entityManager.persist(teacher); }).isInstanceOf(ConstraintViolationException.class).hasMessageContaining("provide a valid phone number");
//    }
}
