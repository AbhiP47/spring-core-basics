package org.abhinav.repository;

import org.abhinav.model.Student;

import java.sql.*;

public class StudentRepository {

    String url = "jdbc:postgresql://localhost:5432/student_db";
    String username = "postgres";
    String password = "0000";


    public void createUser()
    {
        try{
            Connection connection = DriverManager.getConnection(
                    url,
                    username,
                    password
            );
            System.out.println("Database connected successfully. ");
            Statement statement = connection.createStatement();
            String sql = "Insert INTO students(name,email,age) " +
                    "VALUES ('abhinav','abhi@gmail.com',23)";

            int result = statement.executeUpdate(sql);
            if(result == 1)
                System.out.println("Create Operation Successful ");
            else
                System.out.println("Create operation failed. ");
            connection.close();
        }
        catch (Exception e) {
            System.out.println("Abhinav. Database connection failed. ");
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student, Long id) {
        String sql = """
                     UPDATE students
                     SET name = ?,
                         email = ?,
                         age = ?
                     WHERE id = ?
                     """;
        try(
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql);
        ) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setLong(4, id);

            int rowAffected =  preparedStatement.executeUpdate();

            if(rowAffected == 1) {
                System.out.println("Update operation successful");
            }
            else {
                System.out.println("Updation failed");
            }
        }
        catch(SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }

    public void deleteStudent(Long id) {
        String sql = """
            DELETE from students WHERE id = ?
        """;

        try(
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql);
        ) {

            preparedStatement.setLong(1, id);

            int rowAffected = preparedStatement.executeUpdate();

            if(rowAffected == 1) {
                System.out.println("Delete operation successful");
            }
            else {
                System.out.println("Deletion failed");
            }
        }
        catch(SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }
}
