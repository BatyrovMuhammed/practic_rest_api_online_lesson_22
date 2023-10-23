package legendsoft.practic_rest_api_online_lesson_22.service.impl;

import jakarta.persistence.EntityNotFoundException;
import legendsoft.practic_rest_api_online_lesson_22.dto.StudentDto;
import legendsoft.practic_rest_api_online_lesson_22.model.Student;
import legendsoft.practic_rest_api_online_lesson_22.repository.StudentRepository;
import legendsoft.practic_rest_api_online_lesson_22.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<StudentDto> findAllStudent() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public StudentDto findStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
        return convertToDto(student);
    }

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
        Student student = convertToEntity(studentDto);
        student = studentRepository.save(student);
        return convertToDto(student);
    }

    @Override
    public StudentDto updateStudentById(Long id, StudentDto studentDto) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Student not found with id: " + id);
        }
        Student student = convertToEntity(studentDto);
        student.setId(id);
        student = studentRepository.save(student);
        return convertToDto(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    private StudentDto convertToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setAge(student.getAge());
        studentDto.setEmail(student.getEmail());
        studentDto.setPhoneNumber(student.getPhoneNumber());
        studentDto.setStudyFormat(student.getStudyFormat());
        return studentDto;
    }

    private Student convertToEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        student.setEmail(studentDto.getEmail());
        student.setPhoneNumber(studentDto.getPhoneNumber());
        student.setStudyFormat(studentDto.getStudyFormat());
        return student;
    }
}
