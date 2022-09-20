package edu.baylor.cs.se.hibernate.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@NamedQueries({
    @NamedQuery(
            name = "Person.findPersonByContestName",
            query = "SELECT c FROM Person c JOIN c.contests s WHERE s.name = :name"
    )
})
@Entity
public class Person{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

//    public Long getId() {
//        return id;
//    }

    //@NotNull
    @Column
    private Date birthday;
    
    
    //@NotNull
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "must contain valid email address") //again JSR 303 annotation
    @Column
    private String email;

    //@NotNull
    @Column
    private String university;
    
  @ManyToMany(mappedBy = "persons"/*, cascade = CascadeType.ALL, fetch = FetchType.LAZY*/)
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

  private Team teammemberof;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "coach")
  //annotation bellow is just for Jackson serialization in controller
  @JsonIdentityInfo(
          generator = ObjectIdGenerators.PropertyGenerator.class,
          property = "id")
  @JsonIdentityReference(alwaysAsId=true)
  private Set<Team> coachof = new HashSet();
//    @ManyToMany(mappedBy = "students"/*, cascade = CascadeType.ALL, fetch = FetchType.LAZY*/)
//    //annotation bellow is just for Jackson serialization in controller
//    @JsonIdentityInfo(
//            generator = ObjectIdGenerators.PropertyGenerator.class,
//            property = "id")
//    @JsonIdentityReference(alwaysAsId=true)
//    private Set<Course> courses = new HashSet();

//    public Set<Course> getCourses() {
//        return courses;
//    }
//
//    public void setCourses(Set<Course> courses) {
//        this.courses = courses;
//    }
    public Long getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    
    public Date getBirthday(){
        return birthday;
    }
    public Set<Contest> getContests(){
        return contests;
    }
    public Set<Team> getCoach(){
        return coachof;
    }  
    public Team getMember(){
        return teammemberof;
    }
    public void setMember(Team team){
         this.teammemberof= team;
    }

    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
