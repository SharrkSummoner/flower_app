package com.example.flower_app;

import java.sql.*;
import java.util.ArrayList;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import org.apache.ibatis.jdbc.ScriptRunner;


//Подключение к DB
public class DB {
    static Map<String, String> env = System.getenv();
    public static final String DATABASE_URL =env.getOrDefault("DB_HOST", "localhost") ;
    public static final String DATABASE_USERNAME = env.getOrDefault("DB_USER", "root");
    public static final String DATABASE_PORT = env.getOrDefault("DB_PORT", "3306");
    public static final String DATABASE_PASSWORD = env.getOrDefault("DB_PASS", "root");
    public static final String DATABASE_NAME = env.getOrDefault("DB_NAME", "user1");


    static String connectionURL = String.format(
            "jdbc:mysql://%s:%s/%s?serverTimezone=UTC",
            DATABASE_URL,
            DATABASE_PORT,
            DATABASE_NAME
    );

    public static final String SELECT_QUERY = "SELECT * FROM `employee` WHERE login = ? and password = ?";

    public void importDb() throws FileNotFoundException, SQLException {
        Connection connection = DriverManager.getConnection(connectionURL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("SHOW TABLES;");
        ResultSet resultSet = preparedStatement.executeQuery();
        if ( !(resultSet.next()) ) {
            ScriptRunner sr = new ScriptRunner(connection);
            Reader reader = new BufferedReader(new FileReader("backup.sql"));
            sr.runScript(reader);
        }
    }

    public boolean validate(String login, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(connectionURL, DATABASE_USERNAME, DATABASE_PASSWORD);

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);

        System.out.println(preparedStatement);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            connection.close();
            return true;

        } else {
            connection.close();
            return false;
        }

    }

    //Метод для получения информации о пользователе
    public String getEmpInfo(String login, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(connectionURL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        System.out.println(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        if ((resultSet.next()) && (!resultSet.wasNull())) {
            Profile.name = resultSet.getString(2);
            Profile.post = resultSet.getString(3);
        }
        connection.close();
        return "Failed";
    }

    //Метод для получения информации об истории входа сотрудников
    public static ArrayList<String> getHist() throws SQLException {
        String query = "select fio, last_enter from employee";
        Connection connection = DriverManager.getConnection(connectionURL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
        ResultSet res = preparedStatement.executeQuery(query);
        ArrayList<String> hist = new ArrayList<>();
        while (res.next()) {
            hist.add(res.getString(1));
            hist.add(res.getString(2));
        }
        return hist;
    }

    //Метод для получения информации о сотрудниках
    public static ArrayList<String> getEmp() throws SQLException {
        String query = "select * from employee";
        Connection connection = DriverManager.getConnection(connectionURL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
        ResultSet res = preparedStatement.executeQuery(query);
        ArrayList<String> empdata = new ArrayList<>();
        while (res.next()) {
            empdata.add(res.getString(1));
            empdata.add(res.getString(2));
            empdata.add(res.getString(3));
            empdata.add(res.getString(4));
            empdata.add(res.getString(5));
            empdata.add(res.getString(6));
            empdata.add(res.getString(7));
            empdata.add(res.getString(8));
        }
        return empdata;
    }

    //Метод для получения информации о клиентах
    public static ArrayList<String> getClients() throws SQLException {
        String query = "select * from clients";
        Connection connection = DriverManager.getConnection(connectionURL, DATABASE_USERNAME, DATABASE_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
        ResultSet res = preparedStatement.executeQuery(query);
        ArrayList<String> clientsdata = new ArrayList<>();
        while (res.next()) {
            clientsdata.add(res.getString(1));
            clientsdata.add(res.getString(2));
            clientsdata.add(res.getString(3));
            clientsdata.add(res.getString(4));
            clientsdata.add(res.getString(5));
            clientsdata.add(res.getString(6));
        }
        return clientsdata;
    }
}
