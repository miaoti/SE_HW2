package edu.baylor.cs.se.hibernate.controller;

import edu.baylor.cs.se.hibernate.model.Contest;
import edu.baylor.cs.se.hibernate.model.Person;
import edu.baylor.cs.se.hibernate.model.Team;
import edu.baylor.cs.se.hibernate.services.SuperRepository;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//Ignore this as it is Spring and not Java EE (Jax-RS) controller
@RestController
public class MyController {

    private SuperRepository superRepository;

    //you should generally favour constructor/setter injection over field injection
    @Autowired
    public MyController(SuperRepository superRepository){
        this.superRepository = superRepository;
    }

    //very bad practise - using GET method to insert something to DB
    @RequestMapping(value = "/populate", method = RequestMethod.GET)
    public ResponseEntity populate() throws ParseException{
        superRepository.populate();
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ResponseEntity<Contest> getCoursesBySize(){
        return new ResponseEntity(superRepository.getCoursesBySize(),HttpStatus.OK);
    }

    
//    @RequestMapping(value = "/courses2", method = RequestMethod.GET)
//    public ResponseEntity<Contest> getCoursesByStudentName(){
////        String getName(@RequestParam(value = "person", required = false) String studentName) {
////            return "Required element of request param";
////        }
//    	return new ResponseEntity(superRepository.getCoursesByStudentName(),HttpStatus.OK);
//    }
    @RequestMapping(value = "/person/{bbn}", method = RequestMethod.GET)
    public ResponseEntity<Contest> getCoursesByStudentName(@PathVariable String bbn){
    	return new ResponseEntity(superRepository.getCoursesByStudentName(bbn),HttpStatus.OK);
    }
    @RequestMapping(value = "/team/{bbn}", method = RequestMethod.GET)
    public ResponseEntity<Contest> getTeamsbyContestName(@PathVariable String bbn){
    	return new ResponseEntity(superRepository.getTeamsbyContestName(bbn),HttpStatus.OK);
    }
    
  //getTeamsbyContestName  
//    @RequestMapping(value = "/name", method = RequestMethod.GET)
//    public ResponseEntity<Student> getStudentByCourseName(){
////        String getName(@RequestParam(value = "person", required = false) String studentName) {
////            return "Required element of request param";
////        }
//    	return new ResponseEntity(superRepository.getStudentByCourseName(),HttpStatus.OK);
//    }
    

    
    @RequestMapping(value = "/team", method = RequestMethod.GET)
    public ResponseEntity<Team> getTeam(){
    	return new ResponseEntity(superRepository.getTeam(),HttpStatus.OK);
    }
    
    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public ResponseEntity<Team> getPerson(){
    	return new ResponseEntity(superRepository.getPerson(),HttpStatus.OK);
    }
    
    @RequestMapping(value = "/person/group", method = RequestMethod.GET)
    public ResponseEntity<Contest> getPersongROUP(){
        return new ResponseEntity(superRepository.getPersongROUP(),HttpStatus.OK);
    }
}
