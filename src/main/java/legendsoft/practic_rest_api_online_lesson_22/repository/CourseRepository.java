package legendsoft.practic_rest_api_online_lesson_22.repository;

import legendsoft.practic_rest_api_online_lesson_22.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
