package legendsoft.practic_rest_api_online_lesson_22.service.impl;

import jakarta.persistence.EntityNotFoundException;
import legendsoft.practic_rest_api_online_lesson_22.dto.CourseDto;
import legendsoft.practic_rest_api_online_lesson_22.model.Course;
import legendsoft.practic_rest_api_online_lesson_22.model.Student;
import legendsoft.practic_rest_api_online_lesson_22.repository.CourseRepository;
import legendsoft.practic_rest_api_online_lesson_22.repository.StudentRepository;
import legendsoft.practic_rest_api_online_lesson_22.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;


    @Override
    public List<CourseDto> findAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public CourseDto findCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id:" + id));
        return convertToDto(course);
    }

    @Override
    public CourseDto saveCourse(CourseDto courseDto) {
        Course course = convertToEntity(courseDto);
        course = courseRepository.save(course);
        return convertToDto(course);
    }

    @Override
    public CourseDto updateCourseById(Long id, CourseDto courseDto) {
        if (!courseRepository.existsById(id)) {
            throw new EntityNotFoundException("Course not found with id:" + id);
        }
        Course course = convertToEntity(courseDto);
        course.setId(id);
        course = courseRepository.save(course);
        return convertToDto(course);
    }

    @Override
    public void deleteCourseById(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new EntityNotFoundException("Course not found with id:" + id);
        }
        courseRepository.deleteById(id);
    }

    @Override
    public void enrollStudentInCourse(Long courseId, Long studentId) {
       Course course = courseRepository.findById(courseId)
               .orElseThrow(()-> new EntityNotFoundException("Course not found with id:" + courseId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new EntityNotFoundException("Student not found with id:" + studentId));

        course.getStudents().add(student);
        student.getCourses().add(course);

        courseRepository.save(course);
        studentRepository.save(student);


    }

    @Override
    public void removeCourseFromStudent(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(()-> new EntityNotFoundException("Course not found with id:" + courseId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new EntityNotFoundException("Student not found with id:" + studentId));

        course.getStudents().remove(student);
        student.getCourses().remove(course);

        courseRepository.save(course);
        studentRepository.save(student);
    }


    private CourseDto convertToDto(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setName(course.getName());
        courseDto.setDateOfStart(course.getDateOfStart());
        courseDto.setDateOfFinish(course.getDateOfFinish());
        return courseDto;
    }

    private Course convertToEntity(CourseDto courseDto) {
        Course course = new Course();
        course.setName(courseDto.getName());
        course.setDateOfStart(courseDto.getDateOfStart());
        course.setDateOfFinish(courseDto.getDateOfFinish());
        return course;
    }
}
