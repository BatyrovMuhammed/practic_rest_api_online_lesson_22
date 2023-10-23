package legendsoft.practic_rest_api_online_lesson_22.service;

import legendsoft.practic_rest_api_online_lesson_22.dto.StudentDto;

import java.util.List;

public interface StudentService {

    List<StudentDto> findAllStudent();

    StudentDto findStudentById(Long id);

    StudentDto saveStudent(StudentDto studentDto);

    StudentDto updateStudentById(Long id, StudentDto studentDto);

    void deleteStudentById(Long id);
}
