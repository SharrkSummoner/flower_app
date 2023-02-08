package com.example.flower_app;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.flower_app.DB.*;

public class History implements Initializable {

    private ObservableList<HistData> history = FXCollections.observableArrayList();

    @FXML
    private Button saveBtn;
    @FXML
    private Label statusOtchet;

    @FXML
    private TableView<HistData> tableView;

    @FXML
    private TableColumn<HistData, String> tableColumn1;

    @FXML
    private TableColumn<HistData, String> tableColumn2;

    //for pdf
    private String data;
    private String fio;
    private String lastEnter;

    //Метод инициализации формы с таблицей истории входа
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Вывод записей в таблицу
        ArrayList<String> name = null;
        try {
            name = DB.getHist();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < name.size(); ++i) {
            history.add(new HistData(name.get(i), name.get(++i)));
        }
        //Задаем тип данных переменным
        tableColumn1.setCellValueFactory(new PropertyValueFactory<HistData, String>("fio"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<HistData, String>("enter"));
        tableView.setItems(history);

    }

    //Метод для показа кнопки saveBtn
    public void formOtchet(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        saveBtn.setVisible(true);
    }

    //Метод для сохранения отчета
    public void saveOtchet(ActionEvent actionEvent) throws FileNotFoundException, DocumentException, SQLException {
        Connection connection = DriverManager.getConnection(connectionURL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT FIO, last_enter FROM `employee`;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            fio = resultSet.getString(1);
            lastEnter = resultSet.getString(2);

        }
        connection.close();

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("fileHistory.pdf"));
        Font f1 = FontFactory.getFont("DejaVuSans.ttf", "cp1251", BaseFont.EMBEDDED, 10);
        document.open();

        ArrayList<String> name = null;
        try {
            name = DB.getHist();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < name.size(); i++) {
            List list = new List();
            list.add((new ListItem("FIO: " + name.get(i) + "\t", f1)));
            list.add((new ListItem("Last Enter: " + name.get(++i) + "\t\n\n", f1)));
            document.add(list);
        }
        document.close();
        statusOtchet.setVisible(true);
    }
}
