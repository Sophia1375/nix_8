package ua.com.alevel.db;


import ua.com.alevel.entity.Student;

import java.util.Optional;

public class StudentDb {

    private static final StudentDb instance = new StudentDb();
    public static final int INITIAL_SIZE = 42;
    private static Student[] students = new Student[INITIAL_SIZE];


    private StudentDb() {
    }

    public static StudentDb getInstance() {
        return instance;
    }

    public static void clear() {
        students = new Student[INITIAL_SIZE];
    }

    public void create(Student student) {
        boolean isSaved = false;
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                isSaved = true;
                break;
            }
        }
        if (isSaved) {
            return;
        }
        Student[] oldArray = students;
        students = new Student[oldArray.length * 2];
        copy(oldArray, students);
        students[oldArray.length] = student;
    }

    private void copy(Student[] from, Student[] to) {
        for (int i = 0; i < from.length; i++) {
            to[i] = from[i];
        }
    }

    public Optional<Student> findById(String id) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && id.equals(students[i].getId())) {
                return Optional.of(students[i]);
            }
        }
        return Optional.empty();
    }

    public void update(Student student) {
        Optional<Student> studentOptional = findById(student.getId());
        studentOptional.ifPresent(studentToUpdate -> {
            studentToUpdate.setName(student.getName());
            studentToUpdate.setSurname(student.getSurname());
            studentToUpdate.setCourses(student.getCourses());
        });
    }

    public void delete(String id) {
        boolean isDeleted = false;
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) break;
            if (!isDeleted) {
                if (students[i] != null && id.equals(students[i].getId())) {
                    students[i] = null;
                    isDeleted = true;
                }
            } else {
                students[i - 1] = students[i];
                students[i] = null;
            }
        }
    }

    public Student[] findAll() {
        int size = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                size = i;
                break;
            }
        }
        Student[] result = new Student[size];
        for (int i = 0; i < result.length; i++) {
            result[i] = students[i];
        }
        return result;
    }
}
