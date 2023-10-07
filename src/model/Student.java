package model;

import java.util.Arrays;

public class Student {
    private String id;
    private String name;
    private String birthday;
    private String gender;
    private String address;
    private double[] score;
    private boolean isAttending;
    public Student(String id, String name, String birthday, String gender, String address, double Math, double Literature, double English) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.score = new double[]{Math, Literature, English};
        this.isAttending = true;

    }



    public Student(String id, String name, String birthday, String gender, String address, double mathScore, double literatureScore, double englishScore, double averageScore, boolean attending) {
    }

    public boolean isAttending() {
        return isAttending;
    }
    public void toggleAttendanceStatus() {
        isAttending = !isAttending;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double[] getScore() {
        return score;


    }
    public void setAttending(boolean isAttending) {
        this.isAttending = isAttending;
    }


    public void setScore(double[] score) {
        this.score = score;
    }

    public double calculateAverageScore() {
        if (score.length ==  0) {
            return 0.0;
        }
        double sum = 0;
        for (double score : score) {
            sum += score;
        }
        double average = sum / score.length;
        return Math.round(average *10.0)/10.0;
    }

    @Override
    public String toString() {
        return "Sinh viên có: " +
                "id : " + id +
                "| Tên: " + name +
                "| Ngày sinh: " + birthday +
                "| Giới tính: " + gender +
                "| Địa chỉ: " + address +
                "| Điểm Toán, Văn, Anh: " + Arrays.toString(score)+
                "| Điểm trung bình môn: " + calculateAverageScore()+
                "| Trạng thái: " + (isAttending() ? "Đang đi học" : "Đang nghỉ phép");

    }

    public String getData() {
        return id + "," + name + "," + birthday + "," + gender + "," + address + "," + score[0] + ";" + score[1] + ";" + score[2];
    }
    public String getDataScore(){
        return score[0] + ";" + score[1] + ";" + score[2];
    }

    public void setScore(double newMath, double newPoint2, double newPoint3) {
        this.score = new double[]{newMath,newPoint2,newPoint3};
    }
}
