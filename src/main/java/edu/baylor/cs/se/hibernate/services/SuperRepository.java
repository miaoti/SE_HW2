package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.model.Contest;
//import edu.baylor.cs.se.hibernate.model.Room;
import edu.baylor.cs.se.hibernate.model.Person;
import edu.baylor.cs.se.hibernate.model.State;
import edu.baylor.cs.se.hibernate.model.Team;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Spring annotations, feel free to ignore it
@Repository
@Transactional
public class SuperRepository {

    @PersistenceContext
    private EntityManager em;

    public void populate() throws ParseException{
    	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
    	Date newdate = dateformat.parse("2016-6-19");
        Person student = createStudent("Joe");
        student.setBirthday(newdate);
        Person student2 = createStudent("John");
        newdate = dateformat.parse("2017-6-19");
        student2.setBirthday(newdate);
        Person student3 = createStudent("Bob");
        newdate = dateformat.parse("2013-2-19");
        student3.setBirthday(newdate);
        
        Person student4 = createStudent("Tim");
        newdate = dateformat.parse("2000-6-19");
        student4.setBirthday(newdate);
        Person student5 = createStudent("Jimmy");
        newdate = dateformat.parse("2000-8-1");
        student5.setBirthday(newdate);
        Person student6 = createStudent("Yammy");
        newdate = dateformat.parse("2000-9-1");
        student6.setBirthday(newdate);
        
        Person student7 = createStudent("Scott");
        newdate = dateformat.parse("2001-6-28");
        student7.setBirthday(newdate);
        Person student8 = createStudent("Larry");
        newdate = dateformat.parse("2002-6-19");
        student8.setBirthday(newdate);
        Person student9 = createStudent("Molly");
        newdate = dateformat.parse("2003-6-9");
        student9.setBirthday(newdate);
        
        Person student10 = createStudent("Lila");
        newdate = dateformat.parse("1999-4-19");
        student10.setBirthday(newdate);
        Person student11 = createStudent("Becca");
        newdate = dateformat.parse("2000-7-7");
        student11.setBirthday(newdate);
        Person student12 = createStudent("Long");
        newdate = dateformat.parse("2000-3-19");
        student12.setBirthday(newdate);
        
        Person manager = createStudent("Manager");
        
        //Room room = new Room();
        Team team1 = new Team();
        
        team1.setName("Gogo");
        team1.setRank(1);
        State state = State.ACCEPTED;
        team1.setState(state);
        team1.setCoach(student5);
        em.persist(team1);
        //team1.getPerson().add(student);
        student.setMember(team1);
        //team1.getPerson().add(student2);
        student2.setMember(team1);
        //team1.getPerson().add(student3);
        student3.setMember(team1);
        
        
        Team team2 = new Team();

        team2.setName("LOVE");
        team2.setRank(2);
        state = State.ACCEPTED;
        team2.setState(state);
        em.persist(team2);
       // team2.getPerson().add(student7);
        student7.setMember(team2);
        //team2.getPerson().add(student8);
        student8.setMember(team2);
        //team2.getPerson().add(student9);
        student9.setMember(team2);
        
        
        Team team3 = new Team();

        team3.setName("HATE");
        team3.setRank(3);
        state = State.ACCEPTED;
        team3.setState(state);
        em.persist(team3);
        //team3.getPerson().add(student10);
        student10.setMember(team3);
        //team3.getPerson().add(student11);
        student11.setMember(team3);
        //team3.getPerson().add(student12);
        student12.setMember(team3);
        

//        Teacher teacher = new Teacher();
//        teacher.setFirstName("Bob");
//        teacher.setLastName("Porter");
//        teacher.setEmail("bob@porter.com");
//        teacher.setTelephoneNumber("1234567894");
//        em.persist(teacher);
        
        
        
        Contest contest = new Contest(); 
        contest.setName("Big Contest");
        contest.setCapacity(30);
        contest.setRegistration__allowed(true);
        String dateString = "2020-2-28";
        Date date= (Date) new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        contest.setRegistration__from(date);
        dateString = "2021-2-28";
        date= (Date) new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        contest.setRegistration__to(date);
        contest.getStudents().add(manager);
        em.persist(contest);
        
        Contest subcontest1 = new Contest();
        subcontest1.setName("smallcontest");
        subcontest1.setCapacity(15);
        subcontest1.setRegistration__allowed(true);
        dateString = "2020-2-28";
        date= (Date) new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        subcontest1.setRegistration__from(date);
        dateString = "2021-2-28";
        date= (Date) new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        subcontest1.setRegistration__to(date);
        subcontest1.getTeams().add(team1);
        subcontest1.getTeams().add(team2);
        subcontest1.getTeams().add(team3);
        subcontest1.getStudents().add(manager);
        subcontest1.setContest(contest);
        em.persist(subcontest1);
//        course.setName("Software engineering");
//        //course.setTeacher(teacher);
//        course.getStudents().add(student);
//        course.getStudents().add(student3);
//        course.getStudents().add(student4);
//        em.persist(course);
//
//        Contest course2 = new Contest();
//        course2.setName("Boring class");
//        //course2.setTeacher(teacher);
//        course2.getStudents().add(student);
//        course2.getStudents().add(student5);
//        em.persist(course2);
        
        //room.getCourses().add(course2);
        //course2.setRoom(room);
        //course.setRoom(room);

        //room.setlocation("Second floor");
        //em.persist(room);

        //Do you know why this is not working?
        //student2.getCourses().add(course);

    }

    /**
     * List of courses with more than 2 students (3 and more)
     */
    public List<Contest> getCoursesBySize(){
        return em.createQuery("SELECT c FROM Course c WHERE c.students.size > 2 ").getResultList();
    }

    /**
     * List of courses with more than 2 students (3 and more)
     */
//    public List<Contest> getCoursesByStudentName(){
//        return em.createNamedQuery("Course.findCoursesByStudentName").setParameter("name","Joe").getResultList();
//    }
    public List<Contest> getCoursesByStudentName(String bbn){
        return em.createNamedQuery("Contest.findContestsByPersonName").setParameter("name",bbn).getResultList();
    }
//    
//    public List<Person> getStudentByCourseName(){
//        return em.createNamedQuery("Student.findStudentByCoursesName").setParameter("name","Software engineering").getResultList();
//    }

//Team.findTeamsByContestName
    public List<Contest> getTeamsbyContestName(String bbn){
        return em.createNamedQuery("Team.findTeamsByContestName").setParameter("name",bbn).getResultList();
    }

    public List<Person> getTeam(){
        return em.createQuery("SELECT t FROM Team t").getResultList();
    }    
    public List<Person> getPerson(){
        return em.createQuery("SELECT t FROM Person t").getResultList();
    }
    public List<Person> getPersongROUP(){
        return em.createQuery("SELECT t FROM Person t GROUP BY BIRTHDAY").getResultList();
    } 

    private Person createStudent(String name){
    	Person student = new Person();
        student.setName(name);
        em.persist(student);
        return student;
    }
}
