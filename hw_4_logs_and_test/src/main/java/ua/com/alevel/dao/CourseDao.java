package ua.com.alevel.dao;

import ua.com.alevel.db.CourseDb;
import ua.com.alevel.entity.Course;
import ua.com.alevel.entity.Student;
import ua.com.alevel.util.SimpleList;

import java.util.Optional;

public class CourseDao {
    private static CourseDb db = CourseDb.getInstance();
    private static long idGenerator = 0;
    private StudentDao studentDao = new StudentDao();

    public CourseDao() {
    }

    CourseDao(CourseDb courseDb, StudentDao studentDao) {
        db = courseDb;
        this.studentDao = studentDao;
    }


    public void create(Course course) {
        if (course != null && course.getId() == null) {
            course.setId(generateId());
        }
        db.create(course);
    }

    private String generateId() {
        return String.valueOf(++idGenerator);
    }


    public Optional<Course> findById(String id) {
        return db.findById(id);
    }

    public void update(Course course) {
        Optional<Course> optionalCourse = db.findById(course.getId());
        if (optionalCourse.isEmpty()) return;
        db.update(course);
    }

    public void delete(String id) {
        Optional<Course> optionalCourse = db.findById(id);
        if (optionalCourse.isEmpty()) return;
        Course course = optionalCourse.get();
        SimpleList<Student> students = new SimpleList<>(course.getStudents());
        for (Student student : students) {
            Optional<Student> studentOptional = studentDao.findById(student.getId());
            studentOptional.ifPresent(student1 -> {
                SimpleList<Course> courses = new SimpleList<>(student1.getCourses());
                courses.remove(course);
                student1.setCourses((Course[]) courses.toArray());
                studentDao.update(student1);
            });
        }
        db.delete(id);
    }

    public Course[] findAll() {
        return db.findAll();
    }
}
