package ua.com.alevel.db;

import ua.com.alevel.entity.Course;
import ua.com.alevel.util.SimpleList;

import java.util.Optional;

public class CourseDb {
    private static final CourseDb instance = new CourseDb();
    private static SimpleList<Course> courses = new SimpleList<>();

    private CourseDb() {
    }

    public static CourseDb getInstance() {
        return instance;
    }

    public static void clear() {
        courses = new SimpleList<>();
    }

    public void create(Course course) {
        courses.add(course);
    }

    public void update(Course course) {
        Optional<Course> optionalCourse = findById(course.getId());
        optionalCourse.ifPresent(cour -> {
            cour.setId(course.getId());
            cour.setName(course.getName());
            if (course.getStudents() != null) {
                cour.setStudents(course.getStudents());
            }
        });
    }

    public void delete(String id) {
        Optional<Course> optionalCourse = findById(id);
        optionalCourse.ifPresent(course -> courses.remove(course));
    }

    public Course[] findAll() {
        return courses.toArray(new Course[0]);
    }

    public Optional<Course> findById(String id) {
        for (Course course : courses) {
            if (course.getId().equals(id)) {
                return Optional.of(course);
            }
        }
        return Optional.empty();
    }
}
