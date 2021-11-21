package ua.com.alevel.dao;


import ua.com.alevel.db.CourseDb;
import ua.com.alevel.db.StudentDb;
import ua.com.alevel.entity.Course;
import ua.com.alevel.entity.Student;
import ua.com.alevel.util.SimpleList;

import java.util.List;
import java.util.Optional;

public class StudentDao {
    private static StudentDb STUDENT_DB = StudentDb.getInstance();
    private static CourseDb COURSE_DB = CourseDb.getInstance();
    private static long idGenerator = 0;

    public StudentDao() {

    }

    StudentDao(StudentDb studentDb, CourseDb courseDb) {
        STUDENT_DB = studentDb;
        COURSE_DB = courseDb;
    }

    public void create(Student student) {
        if (student != null && student.getId() == null) {
            student.setId(generateId());
        }
        STUDENT_DB.create(student);
        Course[] courses = student.getCourses();
        if (courses == null || courses.length == 0) {
            return;
        }
        for (Course course : courses) {
            Optional<Course> courseById = COURSE_DB.findById(course.getId());
            if (courseById.isEmpty()) {
                Student[] students = new Student[1];
                students[0] = student;
                course.setStudents(students);
                COURSE_DB.create(course);
                return;
            }
            Student[] oldStudents = courseById.get().getStudents();

            if (oldStudents == null) {
                Student[] newStudents;
                newStudents = new Student[]{student};
                course.setStudents(newStudents);
            } else {
                Student[] newStudents = new Student[oldStudents.length + 1];
                for (int i = 0; i < oldStudents.length; i++) {
                    newStudents[i] = oldStudents[i];
                }
                newStudents[newStudents.length - 1] = student;
                course.setStudents(newStudents);
            }

            COURSE_DB.update(course);
        }

    }

    public Optional<Student> findById(String id) {
        return STUDENT_DB.findById(id);
    }

    public void update(Student student) {
        if (student == null) return;
        Optional<Student> studentOptional = STUDENT_DB.findById(student.getId());
        if (studentOptional.isEmpty()) return;
        Student oldStudent = studentOptional.get();
        SimpleList<Course> courses = new SimpleList<>();
        if (student.getCourses() != null) {
            for (Course studentCourse : student.getCourses()) {
                Optional<Course> courseDbById = COURSE_DB.findById(studentCourse.getId());
                courseDbById.ifPresent(courses::add);
            }
            student.setCourses(courses.toArray(new Course[0]));
        }
        STUDENT_DB.update(student);
        Course[] newCourses = student.getCourses();
        Course[] oldCourses = oldStudent.getCourses();
        List<Course> addedCourse = new SimpleList<>();
        List<Course> deletedCourse = new SimpleList<>();
        mergeCourses(oldCourses, newCourses, addedCourse, deletedCourse);
        for (Course course : addedCourse) {
            Optional<Course> courseOptional = COURSE_DB.findById(course.getId());
            courseOptional.ifPresent(
                    cor -> {
                        SimpleList<Student> students = new SimpleList<>(cor.getStudents());
                        students.add(student);
                        cor.setStudents((Student[]) students.toArray());
                        COURSE_DB.update(course);
                    }
            );
        }
        for (Course course : deletedCourse) {
            Optional<Course> courseOptional = COURSE_DB.findById(course.getId());
            courseOptional.ifPresent(
                    cor -> {
                        SimpleList<Student> students = new SimpleList<>(cor.getStudents());
                        students.remove(student);
                        cor.setStudents((Student[]) students.toArray());
                        COURSE_DB.update(course);
                    }
            );
        }

    }

    private void mergeCourses(Course[] oldCourses, Course[] newCourses, List<Course> addedCourse, List<Course> deletedCourse) {
        if (newCourses == null || newCourses.length == 0) {
            if (oldCourses != null)
                deletedCourse = new SimpleList<>(oldCourses);
            return;
        }
        if (oldCourses == null || oldCourses.length == 0) {
            addedCourse = new SimpleList<>(newCourses);
            return;
        }
        for (int i = 0; i < newCourses.length; i++) {
            boolean isAdded = true;
            for (int j = 0; j < oldCourses.length; j++) {
                if (newCourses[i].getId().equals(oldCourses[j].getId())) {
                    isAdded = false;
                    break;
                }
            }
            if (isAdded) {
                addedCourse.add(newCourses[i]);
            }
        }
        for (int i = 0; i < oldCourses.length; i++) {
            boolean isDeleted = true;
            for (int j = 0; j < newCourses.length; j++) {
                if (newCourses[i].getId().equals(oldCourses[j].getId())) {
                    isDeleted = false;
                    break;
                }
            }
            if (isDeleted) {
                deletedCourse.add(newCourses[i]);
            }
        }

    }

    public void delete(String id) {
        Optional<Student> student = STUDENT_DB.findById(id);
        if (student.isEmpty()) {
            return;
        }
        STUDENT_DB.delete(id);
        Course[] courses = student.get().getCourses();
        if (courses == null || courses.length == 0) {
            return;
        }
        for (Course currentCourse : courses) {
            Optional<Course> currCourse = COURSE_DB.findById(currentCourse.getId());
            currCourse.ifPresent(course -> {
                if (course.getStudents() == null && course.getStudents().length == 0)
                    return;
                if (course.getStudents().length == 1) {
                    course.setStudents(new Student[0]);
                } else {
                    Student[] newStudents = new Student[course.getStudents().length - 1];
                    boolean isDeleted = false;
                    for (int idx = 0; idx < course.getStudents().length; idx++) {
                        if (student.get().getId().equals(course.getStudents()[idx].getId())) {
                            isDeleted = true;
                            continue;
                        }
                        if (isDeleted) {
                            newStudents[idx - 1] = course.getStudents()[idx];
                        } else {
                            newStudents[idx] = course.getStudents()[idx];
                        }
                    }
                    course.setStudents(newStudents);
                }
                COURSE_DB.update(course);
            });
        }

    }

    public Student[] findAll() {
        return STUDENT_DB.findAll();
    }

    private String generateId() {
        return String.valueOf(++idGenerator);
    }
}
