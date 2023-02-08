package com.example.flower_app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.flower_app.DB.*;

public class enterOrder implements Initializable {
    @FXML
    private ComboBox orderSelect;
    @FXML
    private TextField orderTime;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label orderLabel;

    private String buffer = "";
    String[] arr = new String[3];

    private String Order = "";
    private String time = "";
    private String state = "";

    private final String QUERY = "insert into orders(state_order, time_order) values(?, ?)";
    private final String QUERY2 = "SELECT order_id FROM `orders` WHERE orders.state_order is NULL and orders.time_order is NULL;";

    //Метод для выбора невыполненных заказов
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement(QUERY2);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderSelect.getItems().add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Метод для завершения заказа
    public void confirmOrder() throws SQLException {
        buffer = orderSelect.getValue().toString();
        arr = buffer.split(",");
        Order = arr[0];
        System.out.println(Order);
        time = orderTime.getText();
        state = "Завершено";
        Connection connection = DriverManager.getConnection(connectionURL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("update orders set " +
                "state_order =\"" + state + "\", time_order =" + time + "  where order_id=" + Order + "");
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            orderLabel.setTextFill(Color.web("#a63f3f"));
            orderLabel.setText("Ошибка");
            throw new RuntimeException(e);
        }
        orderLabel.setTextFill(Color.web("#3fa655"));
        orderLabel.setText("Завершено");
    }

    //Метод для отмены заказа
    public void cancelOrder() throws SQLException {
        buffer = orderSelect.getValue().toString();
        arr = buffer.split(",");
        Order = arr[0];
        System.out.println(Order);
        time = orderTime.getText();
        state = "Отменено";
        Connection connection = DriverManager.getConnection(connectionURL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("update orders set " +
                "state_order =\"" + state + "\", time_order =" + time + "  where order_id=" + Order + "");
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            orderLabel.setTextFill(Color.web("#a63f3f"));
            orderLabel.setText("Ошибка");
            throw new RuntimeException(e);
        }
        orderLabel.setTextFill(Color.web("#3fa655"));
        orderLabel.setText("Заказ отменен");
    }
}
