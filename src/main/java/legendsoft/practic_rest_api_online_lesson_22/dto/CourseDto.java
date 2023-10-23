package legendsoft.practic_rest_api_online_lesson_22.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseDto {

    private Long id;
    private String name;
    private String dateOfStart;
    private String dateOfFinish;
}
