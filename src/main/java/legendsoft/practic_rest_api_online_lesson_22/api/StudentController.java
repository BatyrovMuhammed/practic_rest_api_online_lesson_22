package legendsoft.practic_rest_api_online_lesson_22.api;

import jakarta.persistence.EntityNotFoundException;
import legendsoft.practic_rest_api_online_lesson_22.dto.StudentDto;
import legendsoft.practic_rest_api_online_lesson_22.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> findAllStudents() {
        List<StudentDto> students = studentService.findAllStudent();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDto> findByIdStudent(@PathVariable Long studentId) {
        try {
            StudentDto student = studentService.findStudentById(studentId);
            return ResponseEntity.ok(student);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto studentDto) {
        StudentDto saveStudent = studentService.saveStudent(studentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveStudent);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long studentId, @RequestBody StudentDto studentDto) {
        try {
            StudentDto updateStudent = studentService.updateStudentById(studentId, studentDto);
            return ResponseEntity.ok(updateStudent);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId) {
        try {
            studentService.deleteStudentById(studentId);
            return ResponseEntity.ok("Course deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
