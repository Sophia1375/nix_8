package dao;

import db.StudentDb;
import entity.Student;

import java.util.Optional;

public class StudentDao {
    private static final StudentDb db = new StudentDb();
    private static long idGenerator = 0;

    public void create(Student student) {
        if (student != null && student.getId() == null) {
            student.setId(generateId());
        }
        db.create(student);
    }

    public Optional<Student> findById(String id) {
        return db.findById(id);
    }

    public void update(Student student) {
        db.update(student);
    }

    public void delete(String id) {
        db.delete(id);
    }

    public Student[] findAll() {
        return db.findAll();
    }

    private String generateId() {
        return String.valueOf(++idGenerator);
    }
}
