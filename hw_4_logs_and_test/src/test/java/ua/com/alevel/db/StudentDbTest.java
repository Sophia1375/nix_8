package ua.com.alevel.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.alevel.entity.Student;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class StudentDbTest {

    public static final String ID = "1";
    private StudentDb db;

    @BeforeEach
    void setUp() {
        StudentDb.clear();
        db = StudentDb.getInstance();
    }

    @Test
    void create() {
        Student student = getStudent();
        db.create(student);
        assertEquals(1, db.findAll().length);
        Optional<Student> optionalStudent = db.findById(ID);
        assertTrue(optionalStudent.isPresent());
        assertEquals(ID, optionalStudent.get().getId());
        assertEquals("name", optionalStudent.get().getName());
    }

    private Student getStudent() {
        Student student = new Student();
        student.setId(ID);
        student.setName("name");
        student.setSurname("surname");
        return student;
    }

    @Test
    void findById() {
        Student student = getStudent();
        db.create(student);
        assertEquals(1, db.findAll().length);
        Optional<Student> optionalStudent = db.findById(ID);
        assertTrue(optionalStudent.isPresent());
        assertEquals(ID, optionalStudent.get().getId());
        assertEquals("name", optionalStudent.get().getName());
    }

    @Test
    void update() {
        db.create(getStudent());
        assertEquals(1, db.findAll().length);
        Student student = getStudent();
        student.setName("newName");
        db.update(student);
        assertEquals(1, db.findAll().length);
        Optional<Student> optionalStudent = db.findById(ID);
        assertTrue(optionalStudent.isPresent());
        assertEquals(ID, optionalStudent.get().getId());
        assertEquals("newName", optionalStudent.get().getName());
    }

    @Test
    void delete() {
        Student student = getStudent();
        db.create(student);
        assertEquals(1, db.findAll().length);
        db.delete(ID);
        assertEquals(0, db.findAll().length);
    }

    @Test
    void findAll() {
        assertEquals(0, db.findAll().length);
        db.create(new Student());
        assertEquals(1, db.findAll().length);
    }
}