package model;

import controler.ReadAndWriteFileStudent;
import controler.iUser;

import java.util.ArrayList;
import java.util.List;

public class StudentManager implements iUser<Student> {
    private List<Student> listStudent;
    private ReadAndWriteFileStudent readAndWriteFileStudent = new ReadAndWriteFileStudent();

    public StudentManager() {
        listStudent = readAndWriteFileStudent.ReadFile();
    }
    public boolean isIDExists(String id) {
        for (Student student : listStudent) {
            if (student.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void addStudent(Student student) {
        listStudent.add(student);
        readAndWriteFileStudent.writeFile(listStudent);
    }

    public boolean removeStudent(String id) {
        boolean check = false;
        for (Student student : listStudent) {
            if (student.getId().equals(id)) {
                listStudent.remove(student);
                readAndWriteFileStudent.writeFile(listStudent);
                check = true;
            }

        }
        return check;
    }

    public boolean editStudent(String id, Student newStudent) {
        boolean check = false;
        for (Student student : listStudent) {
            if (student.getId().equals(id)) {
                student.setName(newStudent.getName());
                student.setBirthday(newStudent.getBirthday());
                student.setGender(newStudent.getGender());
                student.setAddress(newStudent.getAddress());
                student.setScore(newStudent.getScore());
                readAndWriteFileStudent.writeFile(listStudent);
                check = true;

            }
        }
        return check;

    }

    public Student findStudentById(String id) {
        for (Student student : listStudent) {
            if (student.getId().equals(id)) {
                return student;
            }

        }
        return null;
    }

    public List<Student> findStudentByName(String name) {
        List<Student> result = new ArrayList<>();
        for (Student student : listStudent) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(student);
            }
        }
//        readAndWriteFileStudent.ReadFile();
        return result;
    }

    public Student studentHaveAverageGradesMax() {
        if (listStudent.isEmpty()) {
            return null;
        }
        Student studentMax = listStudent.get(0);
        double scoreMax = studentMax.calculateAverageScore();
        for (Student student : listStudent) {
            double scoreAverage = student.calculateAverageScore();
            if (scoreAverage > scoreMax) {
                scoreMax = scoreAverage;
                studentMax = student;
//                readAndWriteFileStudent.writeFile(listStudent);
            }
        }
        return studentMax;
    }

    public List<Student> showListStudent() {
        return listStudent;
    }
}
