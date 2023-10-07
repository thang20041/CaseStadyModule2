package controler;

import model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteFileStudent {
    File file = new File("Data/Student.csv");

    public void writeFile(List<Student> listStudent) {
        try {
            FileWriter fileWriter = new FileWriter("Data/Student.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Student student : listStudent) {
                String line = student.getId() + "," + student.getName() + "," + student.getBirthday() + "," +
                        student.getGender() + "," + student.getAddress() + "," + student.getDataScore()+ "," + + student.calculateAverageScore() + "," +
                        (student.isAttending() ? "Đang đi học" : "Đang nghỉ phép");
                bufferedWriter.write(line + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Student> ReadFile() {
        List<Student> listStudent = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0];
                String name = data[1];
                String birthday = data[2];
                String gender = data[3];
                String address = data[4];
                String[] scoreData = data[5].split(";");
                double mathScore = Double.parseDouble(scoreData[0]);
                double literatureScore = Double.parseDouble(scoreData[1]);
                double englishScore = Double.parseDouble(scoreData[2]);
                double averageScore = Double.parseDouble(data[6]);
                boolean attending = Boolean.parseBoolean(data[7]);

                Student student = new Student(id, name, birthday, gender, address,mathScore,literatureScore,englishScore, averageScore, attending);
                listStudent.add(student);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return listStudent;
    }
}
