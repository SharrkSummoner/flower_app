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

public class AddService implements Initializable {

    @FXML
    private TextField serviceId;
    @FXML
    private TextField serviceTitle;
    @FXML
    private TextField servicePrice;
    @FXML
    private Button addBtn;
    @FXML
    private Label serviceLabel;

    private String id = "";
    private String title = "";
    private String price = "";
    private final String QUERY = "insert into service(service_id, title, price) values(?, ?, ?)";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void addService() throws SQLException {
        id = serviceId.getText();
        title = serviceTitle.getText();
        price = servicePrice.getText();
        Connection connection = DriverManager.getConnection(connectionURL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
        preparedStatement.setString(1, id);
        preparedStatement.setString(2, title);
        preparedStatement.setString(3, price);
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            serviceLabel.setTextFill(Color.web("#a63f3f"));
            serviceLabel.setText("Ошибка добавления");
            throw new RuntimeException(e);
        }
        serviceLabel.setTextFill(Color.web("#3fa655"));
        serviceLabel.setText("Услуга добавлена");

    }
}