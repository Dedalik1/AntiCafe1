package com.example.anticafe;

import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;

/**
 * Класс стола для хранения данных о столике
 */
public class Table {
    private Boolean IsChoose;
    private int TableNumber;
    private int second;
    private Label CostLabel;
    private Label Timerlabel;
    private Button tablebutton;
    private Timeline timeline;
    private double tableearnedmoney;
    private double tableearnedmoneyforday;
    private ArrayList<Integer> secondsforallday;

    private int clickedtimes;

    /**
     * Конструктор стола для объявления базовых переменных
     */
    public Table() {
        IsChoose = false;
        clickedtimes = 0;
        secondsforallday = new ArrayList<>();
    }

    /**
     * Возвращает заработанную сумму для данного столика.
     *
     * @return Заработанная сумма для столика.
     */
    public double getTableearnedmoney() {
        return tableearnedmoney;
    }

    /**
     * Устанавливает заработанную сумму для данного столика.
     *
     * @param tableearnedmoney Новая заработанная сумма для столика.
     */
    public void setTableearnedmoney(double tableearnedmoney) {
        this.tableearnedmoney = tableearnedmoney;
    }

    /**
     * Возвращает заработанную сумму за день.
     *
     * @return Заработанная сумма за день.
     */
    public double getTableearnedmoneyforday() {
        return tableearnedmoneyforday;
    }

    /**
     * Устанавливает заработанную сумму за день.
     *
     * @param tableearnedmoneyforday Заработанная сумма за день для установки.
     */
    public void setTableearnedmoneyforday(double tableearnedmoneyforday) {
        this.tableearnedmoneyforday = tableearnedmoneyforday;
    }

    /**
     * Возвращает таймлайн.
     *
     * @return Таймлайн.
     */
    public Timeline getTimeline() {
        return timeline;
    }

    /**
     * Устанавливает таймлайн.
     *
     * @param timeline Таймлайн для установки.
     */
    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    /**
     * Возвращает количество секунд занятым столиком.
     *
     * @return Количество секунд.
     */
    public int getSecond() {
        return second;
    }

    /**
     * Устанавливает количество секунд занятым столиком.
     *
     * @param second Количество секунд для установки.
     */
    public void setSecond(int second) {
        this.second = second;
    }

    /**
     * Возвращает номер столика.
     *
     * @return Номер столика.
     */
    public int getTableNumber() {
        return TableNumber;
    }

    /**
     * Устанавливает номер столика.
     *
     * @param tableNumber Номер столика для установки.
     */
    public void setTableNumber(int tableNumber) {
        TableNumber = tableNumber;
    }

    /**
     * Возвращает метку стоимости.
     *
     * @return Метка стоимости.
     */
    public Label getCostLabel() {
        return CostLabel;
    }

    /**
     * Устанавливает метку стоимости.
     *
     * @param costLabel Метка стоимости для установки.
     */
    public void setCostLabel(Label costLabel) {
        CostLabel = costLabel;
    }

    /**
     * Возвращает метку таймера.
     *
     * @return Метка таймера.
     */
    public Label getTimer() {
        return Timerlabel;
    }

    /**
     * Устанавливает метку таймера.
     *
     * @param timer Метка таймера для установки.
     */
    public void setTimer(Label timer) {
        Timerlabel = timer;
    }

    /**
     * Возвращает кнопку столика.
     *
     * @return Кнопка столика.
     */
    public Button getTablebutton() {
        return tablebutton;
    }

    /**
     * Устанавливает кнопку столика.
     *
     * @param tablebutton Кнопка столика для установки.
     */
    public void setTablebutton(Button tablebutton) {
        this.tablebutton = tablebutton;
    }

    /**
     * Возвращает флаг выбора.
     *
     * @return Флаг выбора.
     */
    public Boolean getChoose() {
        return IsChoose;
    }

    /**
     * Устанавливает флаг выбора.
     *
     * @param choosen Флаг выбора для установки.
     */
    public void setChoose(Boolean choosen) {
        IsChoose = choosen;
    }

    /**
     * Возвращает количество нажатий (сколько раз столик был выбран).
     *
     * @return Количество нажатий.
     */
    public int getClickedtimes() {
        return clickedtimes;
    }

    /**
     * Устанавливает количество нажатий (сколько раз столик был выбран).
     *
     * @param clickedtimes Количество нажатий для установки.
     */
    public void setClickedtimes(int clickedtimes) {
        this.clickedtimes = clickedtimes;
    }

    /**
     * Возвращает список секунд за весь день (каждое значение это время занятое гостями).
     *
     * @return Список секунд за весь день.
     */
    public ArrayList<Integer> getSecondsforallday() {
        return secondsforallday;
    }

    /**
     * Добавляет секунду в список за весь день (каждое значение это время занятое гостями).
     *
     * @param seconds Секунда для добавления.
     */
    public void addToSecondsforallday(int seconds) {
        secondsforallday.add(seconds);
    }

    /**
     * Устанавливает список секунд за весь день (каждое значение это время занятое гостями).
     *
     * @param secondsforallday Список секунд за весь день для установки.
     */
    public void setSecondsforallday(ArrayList<Integer> secondsforallday) {
        this.secondsforallday = secondsforallday;
    }
}
