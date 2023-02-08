package com.example.flower_app;

//Класс для объявления полей таблицы информации о сотрудниках
public class empData {
    private String id;
    private String fio;
    private String post;
    private String address;
    private String birthday;
    private String login;
    private String password;
    private String enter;

    public empData(String id, String fio, String post, String address, String birthday, String login, String password, String enter) {
        this.id = id;
        this.fio = fio;
        this.post = post;
        this.address = address;
        this.birthday = birthday;
        this.login = login;
        this.password = password;
        this.enter = enter;
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

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
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

    public String getEnter() {
        return enter;
    }

    public void setEnter(String enter) {
        this.enter = enter;
    }
}
