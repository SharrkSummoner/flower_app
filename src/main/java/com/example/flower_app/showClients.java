package com.example.flower_app;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.flower_app.DB.*;

public class showClients implements Initializable {
    private ObservableList<clientData> clients = FXCollections.observableArrayList();
    @FXML
    private Button saveBtn;
    @FXML
    private Label statusOtchet;
    @FXML
    private TableView<clientData> tableViewClients;
    @FXML
    private TableColumn<clientData, String> tableColumnId;
    @FXML
    private TableColumn<clientData, String> tableColumnFIO;
    @FXML
    private TableColumn<clientData, String> tableColumnAddress;
    @FXML
    private TableColumn<clientData, String> tableColumnBirthday;
    @FXML
    private TableColumn<clientData, String> tableColumnLogin;
    @FXML
    private TableColumn<clientData, String> tableColumnPassword;

    //for pdf
    private String id;
    private String fio;
    private String address;
    private String birthday;
    private String login;
    private String password;

    //Метод инициализации формы с таблицей истории входа
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Вывод записей в таблицу
        ArrayList<String> name = null;
        try {
            name = DB.getClients();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < name.size(); ++i) {
            clients.add(new clientData(name.get(i), name.get(++i), name.get(++i), name.get(++i), name.get(++i), name.get(++i)));
        }
        //Задаем тип данных переменным
        tableColumnId.setCellValueFactory(new PropertyValueFactory<clientData, String>("id"));
        tableColumnFIO.setCellValueFactory(new PropertyValueFactory<clientData, String>("fio"));
        tableColumnAddress.setCellValueFactory(new PropertyValueFactory<clientData, String>("address"));
        tableColumnBirthday.setCellValueFactory(new PropertyValueFactory<clientData, String>("birthday"));
        tableColumnLogin.setCellValueFactory(new PropertyValueFactory<clientData, String>("login"));
        tableColumnPassword.setCellValueFactory(new PropertyValueFactory<clientData, String>("password"));
        tableViewClients.setItems(clients);
    }

    //Метод для показа кнопки saveBtn
    public void formOtchet(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        saveBtn.setVisible(true);
    }

    //Метод для сохранения отчета
    public void saveOtchet(ActionEvent actionEvent) throws FileNotFoundException, DocumentException, SQLException {
        Connection connection = DriverManager.getConnection(connectionURL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `clients`;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            id = resultSet.getString(1);
            fio = resultSet.getString(2);
            address = resultSet.getString(3);
            birthday = resultSet.getString(4);
            login = resultSet.getString(5);
            password = resultSet.getString(6);

        }
        connection.close();

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("fileClients.pdf"));
        Font f1 = FontFactory.getFont("DejaVuSans.ttf", "cp1251", BaseFont.EMBEDDED, 10);
        document.open();

        ArrayList<String> name = null;
        try {
            name = DB.getClients();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < name.size(); i++) {
            List list = new List();
            list.add((new ListItem("ID: " + name.get(i) + "\t", f1)));
            list.add((new ListItem("FIO: " + name.get(++i) + "\t", f1)));
            list.add((new ListItem("Address: " + name.get(++i) + "\t", f1)));
            list.add((new ListItem("Birthday: " + name.get(++i) + "\t", f1)));
            list.add((new ListItem("Login: " + name.get(++i) + "\t", f1)));
            list.add((new ListItem("Password: " + name.get(++i) + "\t\n\n", f1)));
            document.add(list);
        }
        System.out.println(name);
        document.close();
        statusOtchet.setVisible(true);
    }
}
