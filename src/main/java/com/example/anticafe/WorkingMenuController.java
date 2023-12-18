package com.example.anticafe;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Контроллер для рабочего меню
 */
public class WorkingMenuController {

    @FXML
    private Label CostLabel1;

    @FXML
    private Label CostLabel2;

    @FXML
    private Label CostLabel3;

    @FXML
    private Label CostLabel4;

    @FXML
    private Label CostLabel5;

    @FXML
    private Label CostLabel6;

    @FXML
    private Label CostLabel7;

    @FXML
    private Label CostLabel8;

    @FXML
    private Label CostLabel9;

    @FXML
    private Label CostLabel10;

    @FXML
    private Text SuccesEnterText;

    @FXML
    private TextField CostPerMinuteText;

    @FXML
    private Button CostPerMinuteEnterButton;

    @FXML
    private Label Timer1;

    @FXML
    private Label Timer10;

    @FXML
    private Label Timer2;

    @FXML
    private Label Timer3;

    @FXML
    private Label Timer4;

    @FXML
    private Label Timer5;

    @FXML
    private Label Timer6;

    @FXML
    private Label Timer7;

    @FXML
    private Label Timer8;

    @FXML
    private Label Timer9;

    @FXML
    private Button ShiftEndButton;

    @FXML
    private Button table10button;

    @FXML
    private Button table1button;

    @FXML
    private Button table2button;

    @FXML
    private Button table3button;

    @FXML
    private Button table4button;

    @FXML
    private Button table5button;

    @FXML
    private Button table6button;

    @FXML
    private Button table7button;

    @FXML
    private Button table8button;

    @FXML
    private Button table9button;

    @FXML
    private Label EarnedMoneyLabel;

    private final Table[] tables = new Table[10];
    private final Button[] tableButtons = new Button[10];
    private final Label[] timerlabels = new Label[10];
    private final Label[] costlabels = new Label[10];

    private final TableManager[] tableManagers = new TableManager[10];
    private final ArchiveData archiveData = new ArchiveData();

    public static double CostPerMinute;
    public boolean isCostEntered = false;
    private static final Logger logger = LogManager.getLogger(AnticafeManager.class);

    /**
     * Конструктор класса WorkingMenuController. Инициализирует массивы tables и tableManagers.
     */
    public WorkingMenuController() {
        for (int i = 0; i < tables.length; i++) {
            tables[i] = new Table();
        }
        for (int i = 0; i < tableManagers.length; i++) {
            tableManagers[i] = new TableManager();
        }
    }

    /**
     * Инициализирует компоненты при запуске контроллера, также распределяет некоторые элементы управления в свои списки
     */
    @FXML
    public void initialize() {
        tableButtons[0] = table1button;
        tableButtons[1] = table2button;
        tableButtons[2] = table3button;
        tableButtons[3] = table4button;
        tableButtons[4] = table5button;
        tableButtons[5] = table6button;
        tableButtons[6] = table7button;
        tableButtons[7] = table8button;
        tableButtons[8] = table9button;
        tableButtons[9] = table10button;

        timerlabels[0] = Timer1;
        timerlabels[1] = Timer2;
        timerlabels[2] = Timer3;
        timerlabels[3] = Timer4;
        timerlabels[4] = Timer5;
        timerlabels[5] = Timer6;
        timerlabels[6] = Timer7;
        timerlabels[7] = Timer8;
        timerlabels[8] = Timer9;
        timerlabels[9] = Timer10;

        costlabels[0] = CostLabel1;
        costlabels[1] = CostLabel2;
        costlabels[2] = CostLabel3;
        costlabels[3] = CostLabel4;
        costlabels[4] = CostLabel5;
        costlabels[5] = CostLabel6;
        costlabels[6] = CostLabel7;
        costlabels[7] = CostLabel8;
        costlabels[8] = CostLabel9;
        costlabels[9] = CostLabel10;

        for (int i = 0; i < tableButtons.length; i++) {
            int tableNumber = i + 1;
            initializeTableButton(tableButtons[i], tableNumber);
        }

        deactivateTableButtons();
        costEnter();
        endShift();
        displayEarnedMoney();
    }

    /**
     * Инициализирует кнопку для управления столом.
     *
     * @param button Кнопка, связанная со столом.
     * @param tableNumber Номер стола, который управляется кнопкой.
     */
    public void initializeTableButton(Button button, int tableNumber) {
        button.setOnAction(event -> {

            Table table = tables[tableNumber - 1];

            table.setTableNumber(tableNumber);
            table.setCostLabel(costlabels[tableNumber - 1]);
            table.setTablebutton(button);
            table.setTimer(timerlabels[tableNumber - 1]);

            if (!table.getChoose()) {
                tableManagers[tableNumber - 1].takeTable(table);
            } else {
                tableManagers[tableNumber - 1].releaseTable(table);
            }
        });
    }

    /**
     * Отображает заработанные деньги и обновляет метку с интервалом в 1 секунду.
     */
    public void displayEarnedMoney() {
        Timeline timeline = new Timeline();

        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event ->
                EarnedMoneyLabel.setText("Заработано за сегодня: " + summerEarnedMoney() + " руб.")));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Вычисляет сумму заработанных денег за сегодня.
     *
     * @return Сумма заработанных денег.
     */
    public double summerEarnedMoney() {
        double sum = 0;

        for (Table table : tables) {
            sum += table.getTableearnedmoneyforday();
        }
        return sum;
    }

    /**
     * Завершает смену, освобождает все столики и выполняет дополнительные расчеты для следующего экрана.
     */
    public void endShift() {
        ShiftEndButton.setOnAction(event -> {
            if (noTablesSelected()) {
                showAlert();

                logger.error("Не было занято ни одного столика");

            } else {
                releaseAllTables();



                archiveData.calculateEarnedMoney(tables);
                archiveData.calculateMostClickedTable(tables);
                archiveData.calculateMostProfitableTable(tables);
                archiveData.calculateAverageTime(tables);

                ShiftEndButton.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EndShiftMenu.fxml"));
                Parent root;

                try {
                    root = fxmlLoader.load();

                } catch (IOException e) {
                    logger.error("Ошибка загрузки EndShiftMenu.fxml", e);

                    throw new RuntimeException(e);
                }

                Stage stage = new Stage();
                stage.setTitle("Менеджер Антикафе");
                stage.setScene(new Scene(root, 560, 500));
                stage.showAndWait();

                logger.info("Смена закончена успешно. Отображается EndShiftMenu.fxml.");
            }
        });
    }

    /**
     * Проверяет, был ли выбран хоть один столик.
     *
     * @return true, если ни один столик не был выбран, в противном случае false.
     */
    public boolean noTablesSelected() {
        for (Table table : tables) {

            if (table.getClickedtimes() != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Отображает диалоговое окно с информацией о том, что ни один столик не был выбран.
     */
    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Внимание");
        alert.setHeaderText(null);
        alert.setContentText("Ни один столик не был выбран!");

        alert.showAndWait();
    }

    /**
     * Освобождает все выбранные столики с использованием соответствующего менеджера столиков.
     */
    public void releaseAllTables() {
        for (int i = 0; i < tables.length; i++) {

            if ((tables[i].getChoose())) {
                tableManagers[i].releaseTable(tables[i]);
            }
        }

        logger.info("Все столики освобождены");
    }

    /**
     * Деактивирует все кнопки столов.
     */
    public void deactivateTableButtons() {
        for (Button button : tableButtons) {
            button.setDisable(true);
        }

        logger.info("Все кнопки столов деактивированы");
    }

    /**
     * Активирует все кнопки столов.
     */
    public void activateTableButtons() {
        for (Button button : tableButtons) {
            button.setDisable(false);
        }

        logger.info("Все кнопки столов активированы");
    }

    /**
     * Проверяет правильность введенной стоимости за минуту и соответственно активирует или деактивирует кнопки столов.
     *
     * @param isCostEntered true, если стоимость успешно введена, в противном случае false.
     */
    public void checkRightCostEnter(boolean isCostEntered) {
        if (isCostEntered) {
            activateTableButtons();

        } else {
            deactivateTableButtons();
            releaseAllTables();
        }
    }

    /**
     * Обрабатывает событие ввода стоимости за минуту.
     */
    public void costEnter() {
        CostPerMinuteEnterButton.setOnAction(event -> {
            try {
                CostPerMinute = Double.parseDouble(CostPerMinuteText.getText());

                if (CostPerMinute < 0) {
                    SuccesEnterText.setText("Вы ввели отрицательное число (стоимость не может быть отрицательной)");

                    isCostEntered = false;

                    logger.error("Была введена отрицательная стоимость за минуту");

                } else if (CostPerMinute == 0) {
                    SuccesEnterText.setText("Стоимость не может быть равна нулю");

                    isCostEntered = false;

                    logger.error("Была введена нулевая стоимость за минуту");

                } else {
                    isCostEntered = true;

                    SuccesEnterText.setText("Стоимость успешно введена (" + CostPerMinute + ")");

                    logger.info("Стоимость за минуту успешно введена");
                }

            } catch (NumberFormatException e) {
                SuccesEnterText.setText("""
                        Неверно введено число
                                            
                        Попробуйте ввести в таком формате: '5' или '5.0'\s""");

                isCostEntered = false;

                logger.error("Была неверно введена стоимость за минуту");

            } finally {
                checkRightCostEnter(isCostEntered);
            }
        });
    }

    /**
     * Возвращает стоимость за минуту.
     *
     * @return стоимость за минуту.
     */
    public static double getCostPerMinute() {
        return CostPerMinute;
    }

}


