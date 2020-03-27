package com.exercises.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {
    public Student getStudent(int searchType, String searchItem) {
        Student student = null;
        String selectSql = "";
        if (searchType == 1) {
            selectSql = "SELECT * FROM examstudent WHERE ExamCard = '%s'";
        } else if (searchType == 2) {
            selectSql = "SELECT * FROM examstudent WHERE IDCard = '%s'";
        }

        selectSql = String.format(selectSql, searchItem);

        ResultSet searchResult = JDBCTools.query(selectSql);

        try {
            if (searchResult.next()) {
                student = new Student(
                        searchResult.getInt("Type"),
                        searchResult.getString("IDCard"),
                        searchResult.getString("ExamCard"),
                        searchResult.getString("Location"),
                        searchResult.getString("StudentName"),
                        searchResult.getInt("Grade")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(searchResult, null, null);
        }

        return student;
    }

    public void addStudent(Student student) {
        String insertSql = "INSERT INTO examstudent (Type, IDCard, ExamCard, StudentName, Location, Grade) VALUES " +
                "(%d, '%s', '%s', '%s', '%s', %d)";

        int type = student.getType();
        String idCard = student.getIdCard();
        String examCard = student.getExamCard();
        String studentName = student.getStudentName();
        String location = student.getLocation();
        int grade = student.getGrade();

        insertSql = String.format(insertSql, type, idCard, examCard, studentName, location, grade);
        JDBCTools.update(insertSql);
    }
}
