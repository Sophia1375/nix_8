package ua.com.alevel.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.alevel.db.CourseDb;
import ua.com.alevel.db.StudentDb;
import ua.com.alevel.entity.Course;
import ua.com.alevel.entity.Student;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentDaoTest {
    public static final String ID = "1";
    @Mock
    private StudentDb studentDb;
    @Mock
    private CourseDb courseDb;
    private StudentDao dao;

    @BeforeEach
    void setUp() {
        dao = new StudentDao(studentDb, courseDb);
    }

    @Test
    void shouldCreateStudentWithoutCourses() {
        Student student = getStudent();
        dao.create(student);
        verify(studentDb).create(eq(student));
        verifyNoInteractions(courseDb);
    }

    @Test
    void shouldCreateStudentWithNewCourse() {
        Student student = getStudent();
        Course course = getCourse();
        Course[] courses = new Course[]{course};
        student.setCourses(courses);
        StudentDao studentDao = new StudentDao(studentDb, courseDb);
        studentDao.create(student);
        verify(studentDb).create(eq(student));
        verify(courseDb).create(eq(course));
    }

    @Test
    void shouldCreateStudentWithExistingCourse() {
        Student student = getStudent();
        Course course = getCourse();
        Course[] courses = new Course[]{course};
        student.setCourses(courses);
        doReturn(Optional.of(course)).when(courseDb).findById(eq("1"));

        dao.create(student);
        verify(studentDb).create(eq(student));
        verify(courseDb).update(eq(course));
    }

    @Test
    void findById() {
        Student student = getStudent();
        doReturn(Optional.of(student)).when(studentDb).findById(eq(ID));
        Optional<Student> optionalStudent = dao.findById(ID);
        assertTrue(optionalStudent.isPresent());
        assertEquals(student, optionalStudent.get());
        verify(studentDb).findById(eq(ID));
    }

    @Test
    void update() {
        Student student = getStudent();
        student.setId(ID);
        doReturn(Optional.of(student)).when(studentDb).findById(eq(ID));
        dao.update(student);
        verify(studentDb).update(eq(student));
        verifyNoInteractions(courseDb);
    }

    @Test
    void shouldDeleteStudentWithoutCourses() {
        Student student = getStudent();
        doReturn(Optional.of(student)).when(studentDb).findById(eq("1"));
        dao.delete("1");
        verify(studentDb).delete(eq("1"));
        verifyNoInteractions(courseDb);
    }

    @Test
    void shouldDeleteStudentWithCourses() {
        Student student = getStudent();
        Course course = getCourse();
        course.setStudents(new Student[]{student});
        student.setCourses(new Course[]{course});
        doReturn(Optional.of(student)).when(studentDb).findById(eq("1"));
        doReturn(Optional.of(course)).when(courseDb).findById("1");
        dao.delete("1");
        verify(studentDb).delete(eq("1"));
        verify(courseDb).update(eq(course));
    }

    @Test
    void findAll() {
        Student student = getStudent();
        doReturn(new Student[]{student}).when(studentDb).findAll();
        Student[] students = dao.findAll();
        assertEquals(1, students.length);
        assertEquals(student, students[0]);
    }

    private Student getStudent() {
        Student student = new Student();
        student.setName("name");
        student.setSurname("surname");
        return student;
    }

    private Course getCourse() {
        Course course = new Course();
        course.setId("1");
        course.setName("Math");
        return course;
    }
}