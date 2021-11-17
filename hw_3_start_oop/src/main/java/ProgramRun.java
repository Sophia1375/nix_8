import contoller.StudentController;
import entity.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class ProgramRun {
    private static StudentController studentController;

    public static void run() {
        preview();
        studentController = new StudentController();
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

    private static void findAllStudent(BufferedReader reader) {
        System.out.println("Students: ");
        Student[] students = studentController.findAll();
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i]);
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

    private static void readStudent(BufferedReader reader) throws IOException {
        System.out.println("Enter student's ID: ");
        String id = reader.readLine();
        Optional<Student> studentOptional = studentController.findById(id);
        if (studentOptional.isPresent()){
            System.out.println(studentOptional.get());}
        else {
            System.out.println("Student is not found");
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

    private static void preview() {
        System.out.println();
        System.out.println("if you want to create new student's profile, please select 1");
        System.out.println("if you need to find student's profile, please select 2");
        System.out.println("if you need the find all students' profiles, please select 3");
        System.out.println("if you need to update student's profile, please select 4");
        System.out.println("if you need to delete student's profile, please select 5");
        System.out.println("if you need exit task, please select 0");
        System.out.println("Select you event:");
    }
}
