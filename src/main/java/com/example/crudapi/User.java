package com.example.crudapi;

import java.util.Map;

public class User {
    private String id;
    private String name;
    private Map<String, Integer> subjectMarks;

    // Getters and Setters
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

    public Map<String, Integer> getSubjectMarks() {
        return subjectMarks;
    }

    public void setSubjectMarks(Map<String, Integer> subjectMarks) {
        this.subjectMarks = subjectMarks;
    }

    // toString method
//    @Override
//    public String toString() {
//        return "User{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", subjectMarks=" + subjectMarks +
//                '}'; can be used to debug the response in the code So i override the toString method to dislay hat info is being sent to the backend
//    }
}
