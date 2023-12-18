package com.example.anticafe;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Менеджер таймера.
 */
public class TimerManager {
    private Timeline timeline = new Timeline();
    private Label timerLabel = new Label();
    public Table table = new Table();
    private int tableNumber = 0;
    private int second = 0;
    private final CostManager[] costManagers = new CostManager[10];
    private static final Logger logger = LogManager.getLogger(AnticafeManager.class);

    /**
     * Конструктор класса TimerManager.
     * Инициализирует массив менеджеров стоимости.
     */
    public TimerManager() {
        for (int i = 0; i < costManagers.length; i++) {
            costManagers[i] = new CostManager();
        }
    }

    /**
     * Инициализирует таймер для столика.
     *
     * @param table Столик, для которого нужно инициализировать таймер.
     */
    public void initializeTimer(Table table) {
        this.table = table;
        this.timeline = table.getTimeline();
        this.second = table.getSecond();
        this.tableNumber = table.getTableNumber();

        timeline = new Timeline();
        table.setTimeline(timeline);
        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            second++;
            table.setSecond(second);
            updateTimerLabel(table);
            costManagers[tableNumber - 1].displayCost(table);
        }));

        startTimer(table);
    }

    /**
     * Запускает таймер для столика.
     *
     * @param table Столик, для которого нужно запустить таймер.
     */
    public void startTimer(Table table) {
        this.table = table;
        this.tableNumber = table.getTableNumber();

        timeline.play();

        logger.info("Запущен таймер для столика № " + tableNumber);
        logger.info("Запущено отображение заработанных денег столика № " + tableNumber);
    }

    /**
     * Останавливает таймер для столика.
     *
     * @param table Столик, для которого нужно остановить таймер.
     */
    public void stopTimer(Table table) {
        this.table = table;
        this.timeline = table.getTimeline();
        this.second = table.getSecond();
        this.tableNumber = table.getTableNumber();

        timeline.pause();

        table.setTimeline(timeline);
        table.setSecond(0);
        table.addToSecondsforallday(second);

        timerLabel.setText("");

        costManagers[tableNumber - 1].stopDisplayCost(table);

        logger.info("Остановлен таймер для столика № " + tableNumber);
    }

    /**
     * Обновляет метку таймера на основе переданных секунд.
     *
     * @param table Столик, для которого нужно обновить метку с таймером.
     */
    public void updateTimerLabel(Table table) {
        this.timerLabel = table.getTimer();

        int minutes = second / 60;
        int remainingSeconds = second % 60;

        timerLabel.setText(String.format("%d:%02d", minutes, remainingSeconds));
    }
}