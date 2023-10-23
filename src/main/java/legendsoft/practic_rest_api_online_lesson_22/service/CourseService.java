package legendsoft.practic_rest_api_online_lesson_22.service;

import legendsoft.practic_rest_api_online_lesson_22.dto.CourseDto;

import java.util.List;

public interface CourseService {

    List<CourseDto> findAllCourses();

    CourseDto findCourseById(Long id);

    CourseDto saveCourse(CourseDto courseDto);

    CourseDto updateCourseById(Long id, CourseDto courseDto);

    void deleteCourseById(Long id);

    void enrollStudentInCourse(Long courseId, Long studentId);

    void removeCourseFromStudent(Long courseId, Long studentId);

}
