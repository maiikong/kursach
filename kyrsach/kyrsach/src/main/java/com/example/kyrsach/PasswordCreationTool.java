package com.example.kyrsach;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasswordCreationTool extends Random{

    private static final String RUSSIAN_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String ENGLISH_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()";

    /**
     *Создаётся список, в котрый заносятся необходимые символы
     * @param languageCount параметр количества используемых языков
     * @param hasSpecialCharacters параметр наличия специальных символов
     * @param requiredDigits параметр необходимых цифр
     * @return возвращает список символов, который можно использовать для генерации паролей
     */
    List<String> createCharacterPool(int languageCount, boolean hasSpecialCharacters, String requiredDigits) {

        List<String> characterPool = new ArrayList<>();
        if (languageCount == 3) {
            characterPool.add(ENGLISH_ALPHABET);
        }
        if (languageCount == 2) {
            characterPool.add(RUSSIAN_ALPHABET);
        }
        if (languageCount == 1) {
            characterPool.add(ENGLISH_ALPHABET);
            characterPool.add(RUSSIAN_ALPHABET);
        }
        if (hasSpecialCharacters) {
            characterPool.add(SPECIAL_CHARACTERS);
        }
        for (char digit : requiredDigits.toCharArray()) {
            characterPool.add(String.valueOf(digit));
        }
        return characterPool;
    }

    /**
     *Генерация пароля
     * @param characterPool список символов, который можно использовать для генерации паролей
     * @param length параметр длинны пароля
     * @param hasUpperCase параметр наличия верхнего регистра
     * @return возвращает объект StringBuilder, содержащий сгенерированный пароль.
     */
    StringBuilder addCharactersAndConvertToUpperCase(List<String> characterPool, int length, boolean hasUpperCase) {
        StringBuilder password = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            String randomCharacterSet = characterPool.get(rand.nextInt(characterPool.size()));
            char randomChar = getRandomCharacterFromString(randomCharacterSet, rand);
            password.append(randomChar);
        }
        if (hasUpperCase) {
            randomizeUpperCase(password);
        }
        return password;
    }

    /**
     *Замена некоторых символов на те же в верхнем регистре
     * @param password параметр пароля
     */
    private void randomizeUpperCase(StringBuilder password) {
        Random rand = new Random();
        for (int i = 0; i < password.length(); i++) {
            if (rand.nextBoolean()) {
                char currentChar = password.charAt(i);
                password.setCharAt(i, Character.toUpperCase(currentChar));
            }
        }
    }

    /**
     *Генерация случайного индекса
     * @param source параметр строки
     * @param rand объект Random
     * @return возвращает символ строки source по полученному случайному индексу
     */
    private char getRandomCharacterFromString(String source, Random rand) {
        int randomIndex = rand.nextInt(source.length());
        return source.charAt(randomIndex);
    }

}
