package com.example.kyrsach;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PasswordGeneratorClass extends PasswordCreationTool {
    /**
     *Привязка элементов пользовательского интерфейса с соответствующими полями в классе
     */
    final Logger logger = LogManager.getLogger(PasswordGeneratorClass.class.getName());

    @FXML
    private Label timeLabel;

    @FXML
    private Label infoLabel;

    @FXML
    private TextField figureField;

    @FXML
    private ScrollPane passwordPane;

    @FXML
    private TextField numField;

    @FXML
    private TextArea passwordArea;

    @FXML
    private CheckBox registerBox;

    @FXML
    private CheckBox symbolsBox;

    @FXML
    private CheckBox englishBox;

    @FXML
    private CheckBox russianBox;

    /**
     *Обработка исключений, запись информации об ошибке, либо о генерации пароля, и подсчёт времени потраченного на генерацию пароля
     * @param event имя параметра типа "ActionEvent"
     */
    @FXML
    void generateButton(ActionEvent event) {

        if (numField.getText().trim().isEmpty() ) {
            infoLabel.setText("Quantity field is empty!");
            return;
        }
        if (!numField.getText().trim().matches("\\d*"))
        {
            infoLabel.setText("Only figures in Quantity filed!");
            return;
        }
        if (figureField.getText().trim().isEmpty() & !englishBox.isSelected() & !russianBox.isSelected() & !symbolsBox.isSelected())
        {
            infoLabel.setText("At least one necessary character filed is empty!");
            return;
        }
        if (!figureField.getText().trim().matches("\\d*")) {
            infoLabel.setText("Only figures in Figures filed!");
            return;
        }
        {
            try {
                int passwordLength = Integer.parseInt(numField.getText().trim());
                int languageCount = 0;
                if (russianBox.isSelected() & englishBox.isSelected())
                    languageCount = 1;
                else if(russianBox.isSelected())
                    languageCount = 2;
                else if (englishBox.isSelected())
                    languageCount = 3;
                boolean hasUpperCase = registerBox.isSelected();
                boolean hasSpecialCharacters = symbolsBox.isSelected();
                String requiredDigits = figureField.getText().trim();

                long startTime = System.currentTimeMillis();
                StringBuilder password = generatePassword(passwordLength, languageCount, hasUpperCase, hasSpecialCharacters, requiredDigits);
                long endTime = System.currentTimeMillis();

                if (passwordLength > 0) {
                    infoLabel.setText("");
                    passwordArea.setText(password.toString());
                    passwordPane = new ScrollPane(passwordArea);
                    timeLabel.setText("Password Generation Time: " + (endTime - startTime) + " ms");
                    logger.info("Был сгенерирован пароль.");
                } else {
                    infoLabel.setText("Invalid password length");
                }
            } catch (NumberFormatException e) {
                infoLabel.setText("Invalid values: use numbers instead of letters!");
                logger.error("Были использованы ошибочные символы.");
            }
        }
    }

    /**
     *Обработка исключений при вводе
     * @param length длинна пароля
     * @param languageCount параметр количества языков
     * @param hasUpperCase параметр наличия верхнего регистра
     * @param hasSpecialCharacters параметр наличия специальных символов
     * @param requiredDigits параметр необходимых цифр
     * @return возвращает объект StringBuilder, содержащий сгенерированный пароль.
     */

    private StringBuilder generatePassword(int length, int languageCount, boolean hasUpperCase, boolean hasSpecialCharacters, String requiredDigits) {
        StringBuilder password = new StringBuilder();

        try {
            List<String> characterPool = createCharacterPool(languageCount, hasSpecialCharacters, requiredDigits);
            if (characterPool.isEmpty()) {
                infoLabel.setText("No character types selected");
                return password;
            }
            password = addCharactersAndConvertToUpperCase(characterPool, length, hasUpperCase);
        } catch (Exception e) {
            infoLabel.setText("An error occurred while generating password");
            logger.error("Ошибка в генерации пароля.");
        }
        return password;
    }
}
