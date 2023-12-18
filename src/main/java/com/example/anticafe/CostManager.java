package com.example.anticafe;

import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Управляет отображением заработанных денег для столика.
 */
public class CostManager {
    double tableEarnedMoneyForDay = 0;
    Label costLabel = new Label();
    double tableEarnedMoney = 0;
    Table table = new Table();
    double costPerMinute = 0;
    int second = 0;
    int tableNumber = 0;
    private static final Logger logger = LogManager.getLogger(AnticafeManager.class);

    /**
     * Отображает заработанные деньги для столика.
     *
     * @param table Столик, которому надо включить отображение цены.
     */
    public void displayCost(Table table) {
        this.table = table;
        this.second = table.getSecond();
        this.tableEarnedMoney = table.getTableearnedmoney();
        this.tableEarnedMoneyForDay = table.getTableearnedmoneyforday();
        this.costLabel = table.getCostLabel();
        this.costPerMinute = WorkingMenuController.getCostPerMinute();
        this.tableNumber = table.getTableNumber();

        tableEarnedMoney = second * costPerMinute;
        tableEarnedMoneyForDay += costPerMinute;

        table.setTableearnedmoneyforday(tableEarnedMoneyForDay);

        costLabel.setText(tableEarnedMoney + " руб.");
    }

    /**
     * Останавливает отображение заработанных денег для столика.
     *
     * @param table Столик, которому надо выключить отображение цену.
     */
    public void stopDisplayCost(Table table) {
        this.table = table;
        this.costLabel = table.getCostLabel();
        this.tableNumber = table.getTableNumber();

        costLabel.setText("");

        logger.info("Остановлено отображение заработанных денег столика № " + tableNumber);
    }
}

