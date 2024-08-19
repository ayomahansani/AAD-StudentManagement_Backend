package lk.ijse.stumanagement.dao;

import lk.ijse.stumanagement.dto.StudentDTO;
import lk.ijse.stumanagement.entity.Student;

import java.sql.Connection;
import java.sql.SQLException;

public final class StudentDAOImpl implements StudentDAO {

    static String SAVE_STUDENT = "INSERT INTO Student (id,name,city,email,level) VALUES (?,?,?,?,?)";
    static String UPDATE_STUDENT = "UPDATE Student SET name=?, city=?, email=?, level=? WHERE id=?";
    static String GET_STUDENT = "SELECT * FROM Student WHERE id=?";
    static String DELETE_STUDENT = "DELETE FROM Student WHERE id=?";


    @Override
    public Student getStudent(String studentId, Connection connection) {

        var student = new Student();

        try{
            var ps = connection.prepareStatement(GET_STUDENT);
            ps.setString(1, studentId);
            var resultSet = ps.executeQuery();

            while(resultSet.next()){
                student.setId(resultSet.getString("id"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setCity(resultSet.getString("city"));
                student.setLevel(resultSet.getString("level"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }


    @Override
    public boolean saveStudent(Student student, Connection connection) {

        try {

            var ps = connection.prepareStatement(SAVE_STUDENT);
            ps.setString(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(4, student.getEmail());
            ps.setString(3, student.getCity());
            ps.setString(5, student.getLevel());

            return ps.executeUpdate() != 0;

        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }


    @Override
    public boolean deleteStudent(String studentId, Connection connection) {

        try {

            var ps = connection.prepareStatement(DELETE_STUDENT);
            ps.setString(1, studentId);

            return ps.executeUpdate() != 0;

        } catch (SQLException e){
            throw new RuntimeException();
        }

    }


    @Override
    public boolean updateStudent(String studentId, Student student, Connection connection) {

        try {

            var ps = connection.prepareStatement(UPDATE_STUDENT);
            ps.setString(1, student.getName());
            ps.setString(3, student.getEmail());
            ps.setString(2, student.getCity());
            ps.setString(4, student.getLevel());
            ps.setString(5, studentId);

            return ps.executeUpdate() != 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
