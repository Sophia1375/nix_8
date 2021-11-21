package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.entity.Student;

import java.util.Optional;

public class StudentService {

    private final StudentDao studentDao = new StudentDao();
    private static final Logger loggerError = LoggerFactory.getLogger("error");
    private static final Logger loggerInfo = LoggerFactory.getLogger("info");

    public void create(Student student) {
        loggerInfo.info("create method:" + student.toString());
        try {
            studentDao.create(student);
        } catch (Exception e) {
            loggerError.error("create method:", e);
        }
    }

    public Optional<Student> findById(String id) {
        loggerInfo.info("findById method:" + id);
        try {
            return studentDao.findById(id);
        } catch (Exception e) {
            loggerError.error("findById method:", e);
            return Optional.empty();
        }
    }

    public void update(Student student) {
        loggerInfo.info("update method:" + student.toString());
        try {
            studentDao.update(student);
        } catch (Exception e) {
            loggerError.error("update method:", e);
        }
    }

    public void delete(String id) {
        loggerInfo.info("delete method:" + id);
        try {
            studentDao.delete(id);
        } catch (Exception e) {
            loggerError.error("delete method:", e);
        }

    }

    public Student[] findAll() {
        try {
            return studentDao.findAll();
        } catch (Exception e) {
            loggerError.error("findAll method:", e);
            return new Student[0];
        }
    }
}
