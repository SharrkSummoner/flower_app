package com.example.flower_app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;
    @FXML
    TextField passwordFieldNotHidden;
    @FXML
    CheckBox passwordCheckBox;
    @FXML
    Button submitButton;
    @FXML
    Label label;
    @FXML
    private Label error;

    private Integer attemps = 0;

    //Маска пароля
    public void visiblePass(ActionEvent event) {
        if (passwordCheckBox.isSelected()) {
            passwordFieldNotHidden.setText(passwordField.getText());
            passwordFieldNotHidden.setVisible(true);
            passwordField.setVisible(false);
            return;
        }
        passwordField.setText(passwordField.getText());
        passwordField.setVisible(true);
        passwordFieldNotHidden.setVisible(false);
    }

    //Авторизация
    public void login(ActionEvent event) throws SQLException, IOException {
        try {
            Window window = submitButton.getScene().getWindow();
            System.out.println(loginField.getText());
            System.out.println(passwordField.getText());

            if (passwordField.getText().isEmpty()) {
                label.setText("Введите пароль!");
            }
            if (loginField.getText().isEmpty()) {
                label.setText("Введите логин!");
            }

            String login = loginField.getText();
            String password = passwordField.getText();

            DB db = new DB();
            db.importDb();
            boolean flag = db.validate(login, password);

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    error.setVisible(false);
                    submitButton.setVisible(true);
                }
            };
            //Если ввели данные неправильно
            if (!flag) {
                label.setText("Неверный логин или пароль");
                Timer tm = new Timer();
                attemps = attemps + 1;
                if (attemps > 3) {
                    error.setVisible(true);
                    submitButton.setVisible(false);
                    tm.schedule(task, 5000);
                    attemps = 0;
                }
            }

            //Переход на Profile.fxml в случае успешной авторизации
            else {
                db.getEmpInfo(login, password);
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
                stage.getIcons().add(new Image("flower_logo.png"));
                stage.setTitle("FlowersCenter");
                stage.setScene(new Scene(root, 800, 600));
                stage.show();
                stage.setResizable(false);
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }
        } catch (SQLException e) {
            submitButton.setDisable(true);
            infoBox("Произошла ошибка при подключении к БД", null, "Ошибка");
            System.out.println(e.getMessage());
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

}
