package com.example.flower_app;

//Класс для объявления полей таблицы
public class clientData {
    private String id;
    private String fio;
    private String address;
    private String birthday;
    private String login;
    private String password;

    public clientData(String id, String fio, String address, String birthday, String login, String password) {
        this.id = id;
        this.fio = fio;
        this.address = address;
        this.birthday = birthday;
        this.login = login;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.login = password;
    }
}
