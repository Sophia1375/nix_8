package ua.com.alevel.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.alevel.db.CourseDb;
import ua.com.alevel.entity.Course;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CourseDaoTest {

    public static final String ID = "1";
    @Mock
    private CourseDb courseDB;
    @Mock
    private StudentDao studentDao;
    private CourseDao dao;

    @BeforeEach
    void setIp() {
        dao = new CourseDao(courseDB, studentDao);
    }


    @Test
    void create() {
        Course course = getCourse();
        dao.create(course);
        verify(courseDB).create(eq(course));
    }

    private Course getCourse() {
        Course course = new Course();
        course.setName("name");
        return course;
    }

    @Test
    void findById() {
        Course course = getCourse();
        doReturn(Optional.of(course)).when(courseDB).findById(eq(ID));
        Optional<Course> optionalCourse = dao.findById(ID);
        assertTrue(optionalCourse.isPresent());
        assertEquals(course, optionalCourse.get());
        verify(courseDB).findById(ID);
    }

    @Test
    void update() {
        Course course = getCourse();
        course.setId(ID);
        doReturn(Optional.of(course)).when(courseDB).findById(eq(ID));
        dao.update(course);
        verify(courseDB).update(eq(course));
    }

    @Test
    void delete() {
        Course course = getCourse();
        course.setId(ID);
        doReturn(Optional.of(course)).when(courseDB).findById(eq(ID));
        dao.delete(ID);
        verify(courseDB).delete(eq(ID));
    }

    @Test
    void findAll() {
    }
}