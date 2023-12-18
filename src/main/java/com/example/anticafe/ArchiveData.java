package com.example.anticafe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Класс для архивирования данных о работе столов и кафе.
 */
public class ArchiveData {
    Table[] tables = new Table[10];
    private static double EarnedMoney;
    private static int mostclickedtable;
    private static int mostprofitabletable;
    private static double highestprofit;
    private static int averagetiime;
    private static int clickedtime;
    private static final Logger logger = LogManager.getLogger(AnticafeManager.class);


    /**
     * Рассчитывает общее количество заработанных денег за день.
     *
     * @param tables Массив столов.
     */
    public void calculateEarnedMoney(Table[] tables) {
        this.tables = tables;

        double sum = 0;

        for (Table table : tables) {
            sum += table.getTableearnedmoneyforday();
        }

        EarnedMoney = sum;

        logger.info("Было посчитано количество заработанных денег");
    }

    /**
     * Рассчитывает самый популярный столик и количество его выборов.
     *
     * @param tables Массив столов.
     */
    public void calculateMostClickedTable(Table[] tables) {
        this.tables = tables;

        int max = -1;

        for (Table table : tables) {

            if (table.getClickedtimes() > max) {
                max = table.getClickedtimes();

                mostclickedtable = table.getTableNumber();
                clickedtime = max;
            }
        }

        logger.info("Был расчитан самый популярный столик и количество его выборов");
    }


    /**
     * Рассчитывает самый доходный столик и количество заработанных им денег.
     *
     * @param tables Массив столов.
     */
    public void calculateMostProfitableTable(Table[] tables) {
        this.tables = tables;

        double max = -1;

        for (Table table : tables) {

            if (table.getTableearnedmoneyforday() > max) {
                max = table.getTableearnedmoneyforday();

                mostprofitabletable = table.getTableNumber();
                highestprofit = max;
            }
        }
        logger.info("Был рассчитан самый доходный столик и было посчитано количество заработанных им денег");
    }

    /**
     * Рассчитывает среднее время нахождения в кафе.
     *
     * @param tables Массив столов.
     */
    public void calculateAverageTime(Table[] tables) {
        this.tables = tables;

        int secondssum = 0;
        int secondsquantity = 0;

        for (Table table : tables) {
            ArrayList<Integer> secondsforallday = table.getSecondsforallday();

            if (secondsforallday != null) {
                secondssum += secondsforallday.stream().mapToInt(Integer::intValue).sum();
                secondsquantity += secondsforallday.size();
            }
        }

        averagetiime = secondssum / secondsquantity;

        logger.info("Было посчитано среднее время нахождения в кафе");
    }

    /**
     * Возвращает сумму заработанных денег.
     *
     * @return Сумма заработанных денег.
     */
    public static double getEarnedMoney() {
        return EarnedMoney;
    }

    /**
     * Возвращает номер самого популярного столика.
     *
     * @return Номер самого популярного столика.
     */
    public static int getMostClickedTable() {
        return mostclickedtable;
    }

    /**
     * Возвращает количество раз, которое были выбраны столики.
     *
     * @return Количество раз, которое столики были выбраны.
     */
    public static int getClickedtimes() {
        return clickedtime;
    }

    /**
     * Возвращает самый высокий заработок среди столов за день.
     *
     * @return Самый высокий заработок за день.
     */
    public static double getHighestprofit() {
        return highestprofit;
    }

    /**
     * Возвращает номер самого доходного столика.
     *
     * @return Номер самого доходного столика.
     */
    public static int getMostprofitabletable() {
        return mostprofitabletable;
    }

    /**
     * Возвращает среднее время нахождения в кафе.
     *
     * @return Среднее время нахождения в кафе в минутах.
     */
    public static int getAveragetiime() {
        return averagetiime;
    }
}
