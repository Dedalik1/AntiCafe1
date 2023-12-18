package com.example.anticafe;

import javafx.scene.control.Button;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Менеджер столов.
 */
public class TableManager {
    public Table table = new Table();
    private int tableNumber = 0;
    private Button button = new Button();
    private final TimerManager[] timerManagers = new TimerManager[10];
    private static final Logger logger = LogManager.getLogger(AnticafeManager.class);

    /**
     * Конструктор класса TableManager.
     * Инициализирует массив менеджеров таймеров.
     */
    public TableManager() {
        for (int i = 0; i < timerManagers.length; i++) {
            timerManagers[i] = new TimerManager();
        }
    }

    /**
     * Занимает столик.
     *
     * @param table Столик, который нужно занять.
     */
    public void takeTable(Table table) {
        this.table = table;
        this.button = table.getTablebutton();
        this.tableNumber = table.getTableNumber();

        logger.info("Занят столик № " + tableNumber);

        setColorRed(this.button);

        table.setChoose(true);
        table.setClickedtimes(table.getClickedtimes() + 1);

        timerManagers[tableNumber - 1].initializeTimer(table);
    }

    /**
     * Освобождает столик.
     *
     * @param table Столик, который нужно освободить.
     */
    public void releaseTable(Table table) {
        this.table = table;
        this.button = table.getTablebutton();
        this.tableNumber = table.getTableNumber();

        logger.info("Освобожден столик № " + tableNumber);

        setColorGreen(this.button);

        table.setChoose(false);

        timerManagers[tableNumber - 1].stopTimer(table);
    }

    /**
     * Устанавливает цвет кнопки в красный.
     *
     * @param button Кнопка, цвет которой нужно установить в красный.
     */
    public static void setColorRed(Button button) {
        button.setStyle("-fx-background-color: #FF033E;");
    }

    /**
     * Устанавливает цвет кнопки в зеленый.
     *
     * @param button Кнопка, цвет которой нужно установить в зеленый.
     */
    public static void setColorGreen(Button button) {
        button.setStyle("-fx-background-color: #0BDA51;");
    }
}
