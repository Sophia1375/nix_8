package service;

import dao.StudentDao;
import entity.Student;

import java.util.Optional;

public class StudentService {

    private final StudentDao studentDao = new StudentDao();

    public void create(Student student) {
        studentDao.create(student);
    }

    public Optional<Student> findById(String id) {
        return studentDao.findById(id);
    }

    public void update(Student student) {
        studentDao.update(student);
    }

    public void delete(String id) {
        studentDao.delete(id);
    }

    public Student[] findAll() {
       return studentDao.findAll();
    }
}
