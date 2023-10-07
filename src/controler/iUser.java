package controler;

import java.util.List;

public interface iUser<T> {
    void addStudent(T t);

    boolean removeStudent(String id);

    boolean editStudent(String id, T t);

    T findStudentById(String id);

    List<T> findStudentByName(String name);

    T studentHaveAverageGradesMax();

    List<T> showListStudent();
}
