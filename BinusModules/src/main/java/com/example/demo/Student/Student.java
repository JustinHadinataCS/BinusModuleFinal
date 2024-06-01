package com.example.demo.Student;

public class Student {
    private Long id;
    private String name;
    private Integer student_id;

    public Student(String name, Integer student_id) {
        this.name = name;
        this.student_id = student_id;
    }

    public Student(Long id, String name, Integer student_id) {
        this.id = id;
        this.name = name;
        this.student_id = student_id;
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", student_id=" + student_id +
                '}';
    }
}
