package com.example.flower_app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.flower_app.DB.*;

public class AddEmployee implements Initializable {

    @FXML
    private TextField EmployeeId;
    @FXML
    private TextField EmployeeFio;
    @FXML
    private TextField EmployeePost;
    @FXML
    private TextField EmployeeAddress;
    @FXML
    private TextField EmployeeBirth;
    @FXML
    private TextField EmployeeLogin;
    @FXML
    private TextField EmployeePassword;
    @FXML
    private Button EmployeeBtn;
    @FXML
    private Label employeeLabel;

    private String id = "";
    private String fio = "";
    private String post = "";
    private String address = "";
    private String birth = "";
    private String login = "";
    private String password = "";
    private final String QUERY = "insert into employee(employee_id, FIO, post, address, birthday, login, password) values(?, ?, ?, ?, ?, ?, ?)";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void addEmployee() throws SQLException {
        id = EmployeeId.getText();
        fio = EmployeeFio.getText();
        post = EmployeePost.getText();
        address = EmployeeAddress.getText();
        birth = EmployeeBirth.getText();
        login = EmployeeLogin.getText();
        password = EmployeePassword.getText();
        Connection connection = DriverManager.getConnection(connectionURL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
        preparedStatement.setString(1, id);
        preparedStatement.setString(2, fio);
        preparedStatement.setString(3, post);
        preparedStatement.setString(4, address);
        preparedStatement.setString(5, birth);
        preparedStatement.setString(6, login);
        preparedStatement.setString(7, password);
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            employeeLabel.setTextFill(Color.web("#a63f3f"));
            employeeLabel.setText("Ошибка регистрации");
            throw new RuntimeException(e);
        }
        employeeLabel.setTextFill(Color.web("#3fa655"));
        employeeLabel.setText("Работник зарегистрирован");

    }
}
