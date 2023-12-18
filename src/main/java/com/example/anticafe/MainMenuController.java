package com.example.anticafe;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Контроллер главного меню
 */
public class MainMenuController {

    @FXML
    private Button Shift_start;

    @FXML
    private Label WelcomeLabel;

    private static final Logger logger = LogManager.getLogger(AnticafeManager.class);

    /** Инициализирует контроллер главного меню
     */
    @FXML
    public void initialize() {
        startshift();
        welcomemessage();
    }

    /** Метод дла старта смены и вывода нового окна
     *
     */
    public void startshift() {

        Shift_start.setOnAction(event -> {
            logger.info("Смена начата");

            Shift_start.getScene().getWindow().hide();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Working_menu.fxml"));
            Parent root;

            try {
                root = fxmlLoader.load();

            } catch (IOException e) {
                logger.error("Ошибка загрузки Working_menu.fxml", e);

                throw new RuntimeException(e);
            }

            Stage stage = new Stage();
            stage.setTitle("Менеджер Антикафе");
            stage.setScene(new Scene(root, 800, 600));
            stage.showAndWait();

            logger.info("Смена начата успешно. Отображается Working_menu.fxml.");
        });
    }

    /** Метод для отображения приветственного сообщения
     *
     */
    private void welcomemessage() {
        WelcomeLabel.setText("""
                Добро пожаловать в менеджер Антикафе
                  Чтобы начать работу нажните на кнопку
                                        "Начать смену"
                """);

    }

}

