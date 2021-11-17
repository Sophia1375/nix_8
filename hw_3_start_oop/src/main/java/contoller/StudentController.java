package contoller;

import entity.Student;
import service.StudentService;

import java.util.Optional;

public class StudentController {

    private final StudentService service = new StudentService();

    public void create(Student student) {
        service.create(student);
    }

    public Optional<Student> findById(String id) {
        return service.findById(id);
    }

    public Student[] findAll() {
        return service.findAll();
    }

    public void update(Student student) {
        service.update(student);
    }

    public void delete(String id) {
        service.delete(id);
    }
}
