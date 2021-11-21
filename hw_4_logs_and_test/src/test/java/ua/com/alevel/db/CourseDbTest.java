package ua.com.alevel.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.alevel.entity.Course;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CourseDbTest {

    public static final String ID = "1";
    private CourseDb db;

    @BeforeEach
    void setUp() {
        CourseDb.clear();
        db = CourseDb.getInstance();
    }

    @Test
    void create() {
        Course course = getCourse();
        db.create(course);
        assertEquals(1, db.findAll().length);
        Optional<Course> optionalCourse = db.findById(ID);
        assertTrue(optionalCourse.isPresent());
        assertEquals(ID, optionalCourse.get().getId());
        assertEquals("name", optionalCourse.get().getName());
    }

    private Course getCourse() {
        Course course = new Course();
        course.setId(ID);
        course.setName("name");
        return course;
    }

    @Test
    void findById() {
        Course course = getCourse();
        db.create(course);
        assertEquals(1, db.findAll().length);
        Optional<Course> optionalCourse = db.findById(ID);
        assertTrue(optionalCourse.isPresent());
        assertEquals(ID, optionalCourse.get().getId());
        assertEquals("name", optionalCourse.get().getName());
    }

    @Test
    void update() {
        db.create(getCourse());
        assertEquals(1, db.findAll().length);
        Course course = getCourse();
        course.setName("newName");
        db.update(course);
        assertEquals(1, db.findAll().length);
        Optional<Course> optionalCourse = db.findById(ID);
        assertTrue(optionalCourse.isPresent());
        assertEquals(ID, optionalCourse.get().getId());
        assertEquals("newName", optionalCourse.get().getName());
    }

    @Test
    void delete() {
        Course course = getCourse();
        db.create(course);
        assertEquals(1, db.findAll().length);
        db.delete(ID);
        assertEquals(0, db.findAll().length);
    }

    @Test
    void findAll() {
        assertEquals(0, db.findAll().length);
        db.create(new Course());
        assertEquals(1, db.findAll().length);
    }
}