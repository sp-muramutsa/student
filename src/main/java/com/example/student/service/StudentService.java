package com.example.student.service;

import com.example.student.dto.School;
import com.example.student.dto.StudentResponse;
import com.example.student.entity.Student;
import com.example.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final WebClient webClient;

    public ResponseEntity<?> createStudent(Student student){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentRepository.save(student));
    }

    public ResponseEntity<?> findStudentById(String id){
        Optional<Student> student = studentRepository.findById(id);

        if(student.isPresent()){
            // If student is there, we send a request to the School application to check their school.

            School school = webClient.get()
                    .uri("/schools/" + student.get().getSchoolId())
                    .retrieve()
                    .bodyToMono(School.class)
                    .block();


            StudentResponse studentResponse = new StudentResponse(
                    student.get().getId(),
                    student.get().getName(),
                    student.get().getAge(),
                    student.get().getGender(),
                    school
            );

            return new ResponseEntity<>(studentResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>("Student with id" + id +" not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> findAllStudents(){
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()){
            return new ResponseEntity<>("No students found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    public ResponseEntity<?> updateStudentById(String id, Student student){
        Optional<Student> existingStudent = studentRepository.findById(id);

        if (existingStudent.isPresent()){
            existingStudent.get().setName(student.getName());
            existingStudent.get().setAge(student.getAge());
            existingStudent.get().setGender(student.getGender());
            existingStudent.get().setSchoolId(student.getSchoolId());
            Student updatedStudent = studentRepository.save(existingStudent.get());
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        }

        return new ResponseEntity<>("Student with id" + student.getId() + " not found", HttpStatus.NOT_FOUND);
    }
}
