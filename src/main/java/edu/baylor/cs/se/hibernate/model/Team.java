package edu.baylor.cs.se.hibernate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
@NamedQueries({
    @NamedQuery(
            name = "Team.findTeamsByContestName",
            query = "SELECT c FROM Team c JOIN c.contests s WHERE s.name = :name"
    )
})
@Entity
public class Team {
    @Id
    @GeneratedValue
    private Long id;

    //JPA standard to make not null column
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int rank;
    @Column(nullable = false)
    private State state;


    
//    @NotNull
//    //Not JPA but JSR 303 Bean Validation annotation, Hibernate translates it to nullable=false in @Column for you
//    @Column
//    @Size(min = 1, max = 25) //JSR 303 validation annotation that can be used in entities
//    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
//    private String lastName;
//
//    @NotNull
//    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "must contain valid email address") //again JSR 303 annotation
//    @Column
//    private String email;
//    
//
//    @NotNull
//    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", message="provide a valid phone number")
//    private String telephoneNumber;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
//    //annotation bellow is just for Jackson serialization in controller
//    @JsonIdentityInfo(
//            generator = ObjectIdGenerators.PropertyGenerator.class,
//            property = "id")
//    @JsonIdentityReference(alwaysAsId=true)
//    private Set<Course> courses = new HashSet<Course>();
    
  //coach
  @ManyToOne
  @JsonIdentityInfo(
          generator = ObjectIdGenerators.PropertyGenerator.class,
          property = "id")
  @JsonIdentityReference(alwaysAsId=true)
  private Person coach;
    

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "teammemberof")
  //annotation bellow is just for Jackson serialization in controller
  @JsonIdentityInfo(
          generator = ObjectIdGenerators.PropertyGenerator.class,
          property = "id")
  @JsonBackReference
  @JsonIdentityReference(alwaysAsId=true)
  private Set<Person> contain_member = new HashSet();
  
 // private int numPerson=members.size();
  @ManyToMany(mappedBy = "teams"/*, cascade = CascadeType.ALL, fetch = FetchType.LAZY*/)
  //annotation bellow is just for Jackson serialization in controller
  @JsonIdentityInfo(
          generator = ObjectIdGenerators.PropertyGenerator.class,
          property = "id")
  @JsonIdentityReference(alwaysAsId=true)
  private Set<Contest> contests = new HashSet();
  
    public Long getId() {
        return id;
    }
    public int getRank() {
        return rank;
    }
    
    public Set<Person> getPerson(){
    	return contain_member;
    } 
    public void setPerson(Set<Person> personman){
    	//if(numPerson < 4) {
    		this.contain_member = personman;
    	//}
    } 
    public Set<Contest> getContest(){
    	return contests;
    } 
    
    public void setRank(int rank) {
        this.rank = rank;
    }
    public void setState(State state) {
        this.state = state;
    }
//    public int getNum() {
//        return numPerson;
//    }
//    public void setNum(int num) {
//        this.numPerson = num;
//    }
    public Person getCoach() {
        return coach;
    }
    public void setCoach(Person coach) {
        this.coach = coach;
    }
    public State getState() {
        return state;
    }

//    public String getTelephoneNumber() {
//        return telephoneNumber;
//    }
//    public void setTelephoneNumber(String i) {
//         this.telephoneNumber = i;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//
//    public Set<Course> getCourses() {
//        return courses;
//    }

}
