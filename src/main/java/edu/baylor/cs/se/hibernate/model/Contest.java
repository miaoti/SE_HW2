package edu.baylor.cs.se.hibernate.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//Obsolete with Java 8 @Repeatable annotation
@NamedQueries({
    @NamedQuery(
            name = "Contest.findContestsByPersonName",
            query = "SELECT c FROM Contest c JOIN c.persons s WHERE s.name = :name"
    )
})


@Entity
public class Contest {

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private int capacity;
    
    @NotNull
    @Column
    private Date registration__from;
    
    @NotNull
    @Column
    private Date registration__to;
    
    @NotNull
    @Column
    private boolean registration__allowed;
//    @ManyToOne
//    //annotation bellow is just for Jackson serialization in controller
//    @JsonIdentityInfo(
//            generator = ObjectIdGenerators.PropertyGenerator.class,
//            property = "id")
//    @JsonIdentityReference(alwaysAsId=true)
//    private Person teacher;
    
//    @ManyToOne
//    @JsonIdentityInfo(
//            generator = ObjectIdGenerators.PropertyGenerator.class,
//            property = "id")
//    @JsonIdentityReference(alwaysAsId=true)
//    private Room room;

    @ManyToMany//(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "PERSON_CONTEST",
            joinColumns = { @JoinColumn(name = "CONTEST_ID", referencedColumnName = "ID") }, //do not forget referencedColumnName if name is different
            inverseJoinColumns = { @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID") })
    //annotation bellow is just for Jackson serialization in controller
    //Manager
    private Set<Person> persons = new HashSet();
    
    
    @ManyToMany//(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "TEAM_CONTEST",
            joinColumns = { @JoinColumn(name = "CONTEST_ID", referencedColumnName = "ID") }, //do not forget referencedColumnName if name is different
            inverseJoinColumns = { @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID") })
   
    private Set<Team> teams = new HashSet();
    
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "subcontest")
  //annotation bellow is just for Jackson serialization in controller
  @JsonIdentityInfo(
          generator = ObjectIdGenerators.PropertyGenerator.class,
          property = "id")
  @JsonIdentityReference(alwaysAsId=true)
  private Set<Contest> contests = new HashSet();
  
  @ManyToOne
  @JsonManagedReference
  @JsonIdentityInfo(
          generator = ObjectIdGenerators.PropertyGenerator.class,
          property = "id")
  @JsonIdentityReference(alwaysAsId=true)
  private Contest subcontest;

    public void setStudents(Set<Person> students) {
        this.persons = students;
    }
    public void setContest(Contest contest) {
        this.subcontest = contest;
    }

//    public Long getId() {
//        return id;
//    }

    public String getName() {
        return name;
    }
    
//    public Room getRoom() {
//        return room;
//    }

    public void setName(String name) {
        this.name = name;
    }

//    public Person getTeacher() {
//        return teacher;
//    }

//    public void setTeacher(Teacher teacher) {
//        this.teacher = teacher;
//    }
    
//    public void setRoom(Room room) {
//        this.room = room;
//    }

    public Set<Person> getStudents() {
        return persons;
    }    
    public Set<Team> getTeams() {
        return teams;
    }
    public Set<Contest> getContests() {
        return contests;
    }
    
    public Date getRegistration__from() {
    	return registration__from;
    }
    public void setRegistration__from(Date registration__from) {
    	this.registration__from = registration__from;
    }
    public Date getRegistration__to() {
    	return registration__to;
    }
    public void setRegistration__to(Date registration__to) {
    	this.registration__to = registration__to;
    }
    public boolean getRegistration__allowed() {
    	return registration__allowed;
    }
    public void setRegistration__allowed(boolean registration__allowed) {
    	this.registration__allowed = registration__allowed;
    }
    public int getCapacity() {
    	return capacity;
    }
    public void setCapacity(int capacity) {
    	this.capacity = capacity;
    }

}
