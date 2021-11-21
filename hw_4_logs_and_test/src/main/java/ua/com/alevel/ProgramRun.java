package ua.com.alevel;


import ua.com.alevel.contoller.CourseController;
import ua.com.alevel.contoller.StudentController;
import ua.com.alevel.entity.Course;
import ua.com.alevel.entity.Student;
import ua.com.alevel.util.SimpleList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class ProgramRun {
    private static StudentController studentController;
    private static CourseController courseController;

    public static void run() {
        preview();
        studentController = new StudentController();
        courseController = new CourseController();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String event;
        try {
            while ((event = reader.readLine()) != null) {
                switch (event) {
                    case "1": {
                        createStudent(reader);
                    }
                    break;
                    case "2": {
                        readStudent(reader);
                    }
                    break;
                    case "3": {
                        findAllStudent(reader);
                    }
                    break;
                    case "4": {
                        updateStudent(reader);
                    }
                    break;
                    case "5": {
                        deleteStudent(reader);
                    }
                    break;
                    case "6": {
                        createCourse(reader);
                    }
                    break;
                    case "7": {
                        readCourse(reader);
                    }
                    break;
                    case "8": {
                        findAllCourse(reader);

                    }
                    break;
                    case "9": {
                        updateCourse(reader);
                    }
                    break;
                    case "10": {

                        deleteCourse(reader);
                    }
                    break;
                    case "11": {
                        addStudentToCourse(reader);
                    }
                    break;
                    case "0": {
                        System.exit(0);
                    }
                    break;
                }
                preview();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteStudent(BufferedReader reader) throws IOException {
        System.out.println("Enter student's ID: ");
        String id = reader.readLine();
        studentController.delete(id);
        System.out.println("Student's profile is deleted");
    }

    private static void deleteCourse(BufferedReader reader) throws IOException {
        System.out.println("Enter course's ID: ");
        String id = reader.readLine();
        courseController.delete(id);
        System.out.println("Course is deleted");
    }

    private static void findAllStudent(BufferedReader reader) {
        System.out.println("Students: ");
        Student[] students = studentController.findAll();
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i]);
        }
    }

    private static void findAllCourse(BufferedReader reader) {
        System.out.println("Courses: ");
        Course[] courses = courseController.findAll();
        for (int i = 0; i < courses.length; i++) {
            System.out.println(courses[i]);
        }
    }

    private static void updateStudent(BufferedReader reader) throws IOException {
        System.out.println("Enter ID of the student you want to update: ");
        String id = reader.readLine();
        System.out.println("Enter new name: ");
        String name = reader.readLine();
        System.out.println("Enter new surname: ");
        String surname = reader.readLine();
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setSurname(surname);
        studentController.update(student);
        System.out.println("Your update attempt is completed");
    }

    private static void updateCourse(BufferedReader reader) throws IOException {
        System.out.println("Enter ID of the course you want to update: ");
        String id = reader.readLine();
        System.out.println("Enter new name of a course: ");
        String name = reader.readLine();
        Course course = new Course();
        course.setId(id);
        course.setName(name);
        courseController.update(course);
        System.out.println("Course's name is updated");
    }

    private static void readStudent(BufferedReader reader) throws IOException {
        System.out.println("Enter student's ID: ");
        String id = reader.readLine();
        Optional<Student> studentOptional = studentController.findById(id);
        if (studentOptional.isPresent()) {
            System.out.println(studentOptional.get());
        } else {
            System.out.println("Student is not found");
        }
    }

    private static void readCourse(BufferedReader reader) throws IOException {
        System.out.println("Enter course's ID: ");
        String id = reader.readLine();
        Optional<Course> courseOptional = courseController.findById(id);
        if (courseOptional.isPresent()) {
            System.out.println(courseOptional.get());
        } else {
            System.out.println("Course is not found");
        }
    }

    private static void createStudent(BufferedReader reader) throws IOException {
        System.out.println("Enter student's name: ");
        String name = reader.readLine();
        System.out.println("Enter student's surname: ");
        String surname = reader.readLine();
        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        studentController.create(student);
        System.out.println("New student's profile is created");
    }

    private static void createCourse(BufferedReader reader) throws IOException {
        System.out.println("Enter name of course: ");
        String name = reader.readLine();
        Course course = new Course();
        course.setName(name);
        courseController.create(course);
        System.out.println("New course is created");
    }

    private static void addStudentToCourse(BufferedReader reader) throws IOException {
        System.out.println("Enter ID of course: ");
        String courseId = reader.readLine();
        System.out.println("Enter student's ID: ");
        String studentId = reader.readLine();
        Optional<Course> courseOptional = courseController.findById(courseId);
        if (courseOptional.isEmpty()) {
            System.out.println("TODO ERROR");
            return;
        }
        Course newCourse = new Course();
        newCourse.setId(courseId);
        Optional<Student> studentOptional = studentController.findById(studentId);
        if (studentOptional.isEmpty()) {
            System.out.println("ERROR");
            return;
        }

        Student student = new Student();
        student.setId(studentId);
        SimpleList<Course> courses = new SimpleList<>(student.getCourses());
        courses.add(newCourse);
        student.setCourses(courses.toArray(new Course[0]));
        studentController.update(student);
        System.out.println("Student was added to the course");
    }

    private static void preview() {
        System.out.println();
        System.out.println("if you want to create new student's profile, please select 1");
        System.out.println("if you need to find student's profile, please select 2");
        System.out.println("if you need the find all students' profiles, please select 3");
        System.out.println("if you need to update student's profile, please select 4");
        System.out.println("if you need to delete student's profile, please select 5");
        System.out.println("if you need to create a new course, please select 6");
        System.out.println("if you need to find course, please select 7");
        System.out.println("if you need to find all courses, please select 8");
        System.out.println("if you need to update course, please select 9");
        System.out.println("if you need to delete course, please select 10");
        System.out.println("if you need to add an existing student to a course, please select 11");
        System.out.println("if you need exit task, please select 0");
        System.out.println("Select you event:");
    }
}
