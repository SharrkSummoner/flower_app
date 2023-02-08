package com.example.flower_app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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

public class Registration implements Initializable {

    @FXML
    private TextField clientId;
    @FXML
    private TextField clientFio;
    @FXML
    private TextField clientAddress;
    @FXML
    private DatePicker clientBirth;
    @FXML
    private TextField clientLogin;
    @FXML
    private TextField clientPassword;
    @FXML
    private Button registerBtn;
    @FXML
    private Label regLabel;

    private String id = "";
    private String fio = "";
    private String address = "";
    private String birth = "";
    private String login = "";
    private String password = "";
    private final String QUERY = "insert into clients(client_id, fio, address, birthday, client_login, client_password) values(?, ?, ?, ?, ?, ?)";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    //Метод для регистрации нового клиента
    public void clientRegistration() throws SQLException {
        id = clientId.getText();
        fio = clientFio.getText();
        address = clientAddress.getText();
        birth = clientBirth.getValue().toString();
        login = clientLogin.getText();
        password = clientPassword.getText();
        Connection connection = DriverManager.getConnection(connectionURL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
        preparedStatement.setString(1, id);
        preparedStatement.setString(2, fio);
        preparedStatement.setString(3, address);
        preparedStatement.setString(4, birth);
        preparedStatement.setString(5, login);
        preparedStatement.setString(6, password);
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            regLabel.setTextFill(Color.web("#a63f3f"));
            regLabel.setText("Ошибка регистрации");
            throw new RuntimeException(e);
        }
        regLabel.setTextFill(Color.web("#3fa655"));
        regLabel.setText("Клиент зарегистрирован");

    }
}
