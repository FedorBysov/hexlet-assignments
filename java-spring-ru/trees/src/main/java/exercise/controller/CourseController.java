package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCorses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping("/{id}/previous")
    public Iterable<Course> previousCourse(@PathVariable long id){

        var course = courseRepository.findById(id);
        String path = course.getPath();

        List<Long> a = getPath(path);

        return courseRepository.findAllById(a);

    }

    private List<Long> getPath(String path) {

        if (path == null){
            return List.of();
        }

        String[] ids = path.split("\\.");
        return Arrays.stream(ids)
                .map(s -> Long.parseLong(s))
                .collect(Collectors.toList());
    }
    // END

}
