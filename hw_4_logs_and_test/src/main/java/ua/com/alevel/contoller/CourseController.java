package ua.com.alevel.contoller;

import ua.com.alevel.entity.Course;
import ua.com.alevel.service.CourseService;

import java.util.Optional;

public class CourseController {
    private final CourseService service = new CourseService();

    public void create(Course course) {
        service.create(course);
    }

    public Optional<Course> findById(String id) {
        return service.findById(id);
    }

    public Course[] findAll() {
        return service.findAll();
    }

    public void update(Course course) {
        service.update(course);
    }

    public void delete(String id) {
        service.delete(id);
    }
}
