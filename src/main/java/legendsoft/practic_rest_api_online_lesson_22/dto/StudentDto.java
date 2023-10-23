package legendsoft.practic_rest_api_online_lesson_22.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import legendsoft.practic_rest_api_online_lesson_22.enums.StudyFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentDto {

    private Long id;
    private String name;
    private int age;
    private String email;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
}
