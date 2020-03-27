package com.exercises.jdbc;

import com.mysql.cj.protocol.Resultset;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class JDBCTest {
    public static void main(String[] args) throws Exception {
        Connection connection = JDBCTools.getConnection();
//        String sqlUpdate = "INSERT INTO book (bname, price, btypeId) VALUES ('Go', 65, 1)";
//        JDBCTools.update(sqlUpdate);
//        String sqlSelect = "SELECT * FROM book";
//        ResultSet rs = JDBCTools.query(sqlSelect);
//        while (rs.next()) {
//            int id = rs.getInt("bid");
//            String name = rs.getString("bname");
//            float price = rs.getFloat("price");
//            int typeId = rs.getInt("btypeId");
//            System.out.println(id + "-" + name + "-" + price + "-" + typeId);
//        }
//
//        JDBCTools.release(rs, null, null);
        String sqlInsert = "INSERT INTO examstudent (Type, IDCard, ExamCard, StudentName, Location, Grade) VALUES " +
                "(%d, '%s', '%s', '%s', '%s', %d)";
        System.out.println("Please enter student's information: ");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type: ");
        int type = scanner.nextInt();
        System.out.print("IDCard: ");
        String idCard = scanner.next();
        System.out.print("ExamCard: ");
        String examCard = scanner.next();
        System.out.print("StudentName: ");
        String studentName = scanner.next();
        System.out.print("Location: ");
        String location = scanner.next();
        System.out.print("Grade: ");
        int grade = scanner.nextInt();

        sqlInsert = String.format(sqlInsert, type, idCard, examCard, studentName, location, grade);
        System.out.println(sqlInsert);
        JDBCTools.update(sqlInsert);
    }
}
