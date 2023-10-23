package legendsoft.practic_rest_api_online_lesson_22.api;

import jakarta.persistence.EntityNotFoundException;
import legendsoft.practic_rest_api_online_lesson_22.dto.CourseDto;
import legendsoft.practic_rest_api_online_lesson_22.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseDto>> findAllCourses() {
        List<CourseDto> courses = courseService.findAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDto> findByIdCourse(@PathVariable Long courseId) {
        try {
            CourseDto course = courseService.findCourseById(courseId);
            return ResponseEntity.ok(course);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<CourseDto> saveCourse(@RequestBody CourseDto courseDto) {
        CourseDto saveCourse = courseService.saveCourse(courseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCourse);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable Long courseId, @RequestBody CourseDto courseDto) {
        try {
            CourseDto updateCourse = courseService.updateCourseById(courseId, courseDto);
            return ResponseEntity.ok(updateCourse);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
        try {
            courseService.deleteCourseById(courseId);
            return ResponseEntity.ok("Course deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/{courseId}/courses/{studentId}")
    public ResponseEntity<String> enrollStudentInCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        try {
            courseService.enrollStudentInCourse(courseId, studentId);
            return ResponseEntity.ok("Student enrolled in the course successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{courseId}/courses/{studentId}")
    public ResponseEntity<String> removeStudentFromCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        try {
            courseService.removeCourseFromStudent(courseId, studentId);
            return ResponseEntity.ok("Student removed in the course successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
