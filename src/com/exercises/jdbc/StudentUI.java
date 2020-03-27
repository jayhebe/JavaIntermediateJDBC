package com.exercises.jdbc;

import java.util.Scanner;

public class StudentUI {
    public static void main(String[] args) {
        displayMenu();
    }

    public static void displayMenu() {
        while (true) {
            System.out.println("请选择要输入的类型：");
            System.out.print("1. 准考证号  2. 身份证号 (输入0退出)：");
            Scanner scanner = new Scanner(System.in);
            int searchType = scanner.nextInt();

            if (searchType == 0) {
                break;
            }

            if (searchType == 1) {
                System.out.print("请输入准考证号：");
            } else if (searchType == 2) {
                System.out.print("请输入身份证号：");
            } else {
                System.out.println("您的输入有误，请重新输入");
                continue;
            }

            String searchItem = scanner.next();
            StudentDAO dao = new StudentDAO();
            Student student = dao.getStudent(searchType, searchItem);
            if (student == null) {
                System.out.println("查无此人");
            } else {
                System.out.println("四级/六级：\t" + student.getType());
                System.out.println("身份证号：\t" + student.getIdCard());
                System.out.println("准考证号：\t" + student.getExamCard());
                System.out.println("学生姓名：\t" + student.getStudentName());
                System.out.println("区域：\t\t" + student.getLocation());
                System.out.println("成绩：\t\t" + student.getGrade());
            }
        }
    }
}
