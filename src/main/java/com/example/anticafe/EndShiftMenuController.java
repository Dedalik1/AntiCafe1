package com.example.anticafe;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Контроллер завершающего меню
 */
public class EndShiftMenuController {

    @FXML
    private Label AverageTimeLabel;

    @FXML
    private Label EarnedMoneyLabel;

    @FXML
    private Label MostPopularTableLabel;

    @FXML
    private Label MostProfitableTableLabel;

    /**
     * Инициализирует контроллер и отображает информацию о заработке, популярности столиках, самом доходном столике, среднее время нахождение в кафе.
     */
    @FXML
    public void initialize() {
        DisplayEarnedMoney();
        DisplayMostPopularTable();
        DisplayMostProfitableTable();
        DisplayAverageTime();
    }

    /**
     * Отображает информацию о заработанных деньгах за сегодня.
     */
    public void DisplayEarnedMoney() {
        EarnedMoneyLabel.setText("Заработано за сегодня: " + ArchiveData.getEarnedMoney() + " руб.");
    }

    /**
     * Отображает информацию о самом популярном столике и сколько раз он был выбран.
     */
    public void DisplayMostPopularTable() {
        MostPopularTableLabel.setText("Самый популярный столик - столик №" + ArchiveData.getMostClickedTable() +
                "\n" + "был выбран - " + ArchiveData.getClickedtimes() + " раз(a)");
    }

    /**
     * Отображает информацию о самом доходном столике и сколько он заработал.
     */
    public void DisplayMostProfitableTable() {
        MostProfitableTableLabel.setText("Самый доходный столик - столик №" + ArchiveData.getMostprofitabletable() +
                "\n" + "Он заработал: " + ArchiveData.getHighestprofit() + " руб.");
    }

    /**
     * Отображает информацию о среднем времени нахождения в кафе.
     */
    public void DisplayAverageTime() {
        AverageTimeLabel.setText("Среднее время нахождения в кафе: " + ArchiveData.getAveragetiime() + " минут");
    }
}