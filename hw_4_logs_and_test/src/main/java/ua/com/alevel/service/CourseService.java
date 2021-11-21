package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.CourseDao;
import ua.com.alevel.entity.Course;

import java.util.Optional;

public class CourseService {
    private static final Logger loggerError = LoggerFactory.getLogger("error");
    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private final CourseDao courseDao = new CourseDao();

    public void create(Course course) {
        loggerInfo.info("create method:" + course.toString());
        try {
            courseDao.create(course);
        } catch (Exception e) {
            loggerError.error("create method:", e);
        }
    }

    public Optional<Course> findById(String id) {
        loggerInfo.info("findById method:" + id);
        try {
            return courseDao.findById(id);
        } catch (Exception e) {
            loggerError.error("findById method:", e);
            return Optional.empty();
        }
    }

    public void update(Course course) {
        loggerInfo.info("update method:" + course.toString());
        try {
            courseDao.update(course);
        } catch (Exception e) {
            loggerError.error("update method:", e);
        }
    }

    public void delete(String id) {
        loggerInfo.info("delete method:" + id);
        try {
            courseDao.delete(id);
        } catch (Exception e) {
            loggerError.error("delete method:", e);
        }

    }

    public Course[] findAll() {
        try {
            return courseDao.findAll();
        } catch (Exception e) {
            loggerError.error("findAll method:", e);
            return new Course[0];
        }
    }
}
