package dev.restapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNumber;
    @Column(name = "student_name")
    private String name;
    @Column(name = "student_score")
    private float score;
    @Column(name = "student_department")
    private String department;

    public Student() {

    }

    public Student(String name, float score, String department) {
        this.name = name;
        this.score = score;
        this.department = department;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Student{" +
                "idNumber=" + idNumber +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", department='" + department + '\'' +
                '}';
    }

}
