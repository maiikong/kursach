package com.example.kyrsach;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *Передача аргументов из командной строки и запись информации о запуске программы
 */
public class MainLauncher {
    public static void main(String[] args) {

        final Logger logger = LogManager.getLogger(MainLauncher.class.getName());
        logger.info("Произведен запуск.");
        ApplicationStart.main(args);
    }
}