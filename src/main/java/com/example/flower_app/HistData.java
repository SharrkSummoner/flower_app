package com.example.flower_app;
//Класс для объявления полей таблицы истории входа
public class HistData {

    private String fio;
    private String enter;

    public HistData(String fio, String enter) {
        this.fio = fio;
        this.enter = enter;
    }


    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getEnter() {
        return enter;
    }

    public void setEnter(String enter) {
        this.enter = enter;
    }
}
