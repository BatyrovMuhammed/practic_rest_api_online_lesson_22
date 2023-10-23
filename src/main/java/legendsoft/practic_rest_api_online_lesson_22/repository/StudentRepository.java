package legendsoft.practic_rest_api_online_lesson_22.repository;

import legendsoft.practic_rest_api_online_lesson_22.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
