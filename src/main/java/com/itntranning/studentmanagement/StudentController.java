package com.itntranning.studentmanagement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	@Autowired
	private StudentRepository studentRepository;
//@GetMapping("/student")
//public Student getStudent() {
//	Student student=new Student();
//	student.setStudentid(1l);
//	student.setFirstName("tamdin");
//	student.setLastName("wangyal");
//	student.setAddress("kathmandu");
//	return student;
//	
	
//}
@GetMapping ("/students/{firstName}")
public String greet(@PathVariable String firstName) {
	return "Hello"+firstName;
}

@PostMapping("/students")
public ResponseEntity<?>saveStudent(@RequestBody Student student) {
	System.out.println("First Name:"+student.getFirstName());
	System.out.println("Last Name:"+student.getLastName());
	Student savedstudent=studentRepository.save(student);
	return ResponseEntity.ok(savedstudent);
	
}
@GetMapping("/students")
public ResponseEntity<?> getAllStudents(){
	List<Student> studentList= studentRepository.findAll();
	return ResponseEntity.ok(studentList);
	
}
@PutMapping("/students")
@Transactional 
public ResponseEntity<?> updateStudent (@RequestBody Student student, @RequestParam Long studentid){
	Optional<Student> oldstudentOptional = studentRepository.findById(studentid);
	oldstudentOptional.ifPresent(s->{
		s.setAddress(student.getAddress());
		s.setFirstName(student.getFirstName());
		s.setLastName(student.getLastName());
		
	});
	return ResponseEntity.ok("student updated sucessfully ");
}
 @DeleteMapping("/student")
 public ResponseEntity<?> deleteStudent (@RequestParam Long studentid){
	 studentRepository.findById(studentid).ifPresent(s->{
		 studentRepository.delete(s);
	 });
	return ResponseEntity.ok("deleted");
 }
		
	



}

