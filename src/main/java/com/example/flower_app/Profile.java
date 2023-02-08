package com.example.flower_app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Profile implements Initializable {
    @FXML
    private ImageView photo;
    @FXML
    private Text textHi;
    @FXML
    private Text doljnost;

    @FXML
    private Button registerBtn;
    @FXML
    private Button serviceBtn;
    @FXML
    private Button history;
    @FXML
    private Button showEmp;
    @FXML
    private Button ClientBtn;
    @FXML
    private Button formOrder;
    @FXML
    private Button enterOrder;
    @FXML
    private Button EmployeeBtn;
    public static String name = "";
    public static String post = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("src/img/" + name.split(" ")[0] + ".jpeg");
        Image image = new Image(file.toURI().toString());
        photo.setImage(image);
        textHi.setText("Здравствуйте, " + name + " !");
        doljnost.setText("Ваша должность: " + post);
        //Скрываем кнопки исходя от должности пользователя
        if (post.equals("Курьер")) {
            registerBtn.setDisable(true);
            registerBtn.setVisible(false);

            serviceBtn.setDisable(true);
            serviceBtn.setVisible(false);

            EmployeeBtn.setVisible(false);
            EmployeeBtn.setDisable(true);

            history.setDisable(true);
            history.setVisible(false);

            showEmp.setVisible(false);
            showEmp.setDisable(true);

            formOrder.setDisable(true);
            formOrder.setVisible(false);

            ClientBtn.setDisable(true);
            ClientBtn.setVisible(false);
        }
        if (post.equals("Менеджер")) {
            serviceBtn.setDisable(true);
            serviceBtn.setVisible(false);

            EmployeeBtn.setVisible(false);
            EmployeeBtn.setDisable(true);

            history.setDisable(true);
            history.setVisible(false);

            showEmp.setVisible(false);
            showEmp.setDisable(true);

            enterOrder.setVisible(false);
            enterOrder.setDisable(true);
        }
        if (post.equals("Администратор")) {
            formOrder.setDisable(true);
            formOrder.setVisible(false);

            enterOrder.setVisible(false);
            enterOrder.setDisable(true);

            ClientBtn.setDisable(true);
            ClientBtn.setVisible(false);
        }
    }

    //Метод для перехода на registration.fxml
    public void clientRegister(ActionEvent event) throws IOException {
        Stage register = new Stage();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("registration.fxml")));
        register.getIcons().add(new Image("flower_logo.png"));
        register.setTitle("FlowersCenter");
        register.setScene(new Scene(parent, 400, 400));
        register.setResizable(false);
        register.show();
    }

    //Метод для перехода на addService.fxml
    public void addService(ActionEvent event) throws IOException {
        Stage addService = new Stage();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addService.fxml")));
        addService.getIcons().add(new Image("flower_logo.png"));
        addService.setTitle("FlowersCenter");
        addService.setScene(new Scene(parent, 400, 400));
        addService.setResizable(false);
        addService.show();
    }

    //Метод для перехода на enterOrder.fxml
    public void enterOrder(ActionEvent event) throws IOException {
        Stage formOrder = new Stage();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("enterOrder.fxml")));
        formOrder.getIcons().add(new Image("flower_logo.png"));
        formOrder.setTitle("FlowersCenter");
        formOrder.setScene(new Scene(parent, 400, 400));
        formOrder.setResizable(false);
        formOrder.show();
    }

    //Метод для перехода на formOrder.fxml
    public void formOrder(ActionEvent event) throws IOException {
        Stage formOrder = new Stage();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("formOrder.fxml")));
        formOrder.getIcons().add(new Image("flower_logo.png"));
        formOrder.setTitle("FlowersCenter");
        formOrder.setScene(new Scene(parent, 400, 600));
        formOrder.setResizable(false);
        formOrder.show();
    }

    //Метод для перехода на showClients.fxml
    public void showClients(ActionEvent event) throws IOException {
        Stage histStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("showClients.fxml")));
        histStage.getIcons().add(new Image("flower_logo.png"));
        histStage.setTitle("FlowersCenter");
        histStage.setScene(new Scene(root, 800, 400));
        histStage.setResizable(false);
        histStage.show();
    }

    //Метод для перехода на addEmployee.fxml
    public void addEmployee(ActionEvent event) throws IOException {
        Stage addEmployee = new Stage();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addEmployee.fxml")));
        addEmployee.getIcons().add(new Image("flower_logo.png"));
        addEmployee.setTitle("FlowersCenter");
        addEmployee.setScene(new Scene(parent, 400, 400));
        addEmployee.setResizable(false);
        addEmployee.show();
    }

    //Метод для перехода на history.fxml
    public void history(ActionEvent event) throws IOException {
        Stage histStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("history.fxml")));
        histStage.getIcons().add(new Image("flower_logo.png"));
        histStage.setTitle("FlowersCenter");
        histStage.setScene(new Scene(root, 600, 400));
        histStage.setResizable(false);
        histStage.show();
    }

    //Метод для перехода на showEmployee.fxml
    public void showEmp(ActionEvent event) throws IOException {
        Stage histStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("showEmployee.fxml")));
        histStage.getIcons().add(new Image("flower_logo.png"));
        histStage.setTitle("FlowersCenter");
        histStage.setScene(new Scene(root, 800, 400));
        histStage.setResizable(false);
        histStage.show();
    }

    //Метод для перехода на start-view.fxml
    public void exit(ActionEvent event) throws IOException {
        Stage st3 = new Stage();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("start-view.fxml")));
        st3.getIcons().add(new Image("flower_logo.png"));
        st3.setTitle("FlowersCenter");
        st3.setScene(new Scene(root, 300, 400));
        st3.setResizable(false);
        st3.show();

        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
