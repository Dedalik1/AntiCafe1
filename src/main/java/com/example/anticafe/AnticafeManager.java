package com.example.anticafe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Основной класс Антикафе
 */
public class AnticafeManager extends Application{
    private static final Logger logger = LogManager.getLogger(AnticafeManager.class);

    /**
     * Точка входа в приложение JavaFX.
     *
     * @param stage Главное окно приложения.
     * @throws IOException В случае ошибки загрузки FXML-файла.
     */
    @Override
    public void start(Stage stage) throws IOException {

        InputStream iconStream = getClass().getResourceAsStream("manager_icon.png");
        assert iconStream != null;
        Image image = new Image(iconStream);
        stage.getIcons().add(image);

        FXMLLoader fxmlLoader = new FXMLLoader(AnticafeManager.class.getResource("Start-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        fxmlLoader.setController(new MainMenuController());

        stage.setTitle("Менеджер Антикафе");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Точка входа в программу.
     *
     * @param args Аргументы командной строки, переданные при запуске программы.
     */
    public static void main(String[] args) {
        logger.info("Старт программы");

        launch(args);
    }
}