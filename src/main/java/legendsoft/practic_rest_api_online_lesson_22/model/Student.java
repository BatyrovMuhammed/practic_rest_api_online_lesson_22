package legendsoft.practic_rest_api_online_lesson_22.model;

import jakarta.persistence.*;
import legendsoft.practic_rest_api_online_lesson_22.enums.StudyFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @SequenceGenerator(name = "student_gen", sequenceName = "student_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_gen")
    private Long id;
    private String name;
    private int age;
    private String email;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();
}
