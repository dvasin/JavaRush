package com.javarush.test.level26.lesson15.big01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by denis on 11.06.16.
 */
public class ConsoleHelper {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        String s = "";
        try {
            s = reader.readLine();
           // bf.close();
        } catch (IOException e) {
        }
        return s;
    }

    public String askCurrencyCode() {
        boolean flag = false;
        String currencyCode = "";
        while (!flag) {
            writeMessage("Введите код валюты: ");
            currencyCode = readString();
            if (currencyCode.length() != 3) {
                writeMessage("Код валюты введен неверно!");
            } else {
                flag = true;
            }
        }
        return currencyCode.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) {
        String[] array;
        writeMessage("Ввведите номинал и количество");

        while (true)
        {
            String s = readString();
            array = s.split(" ");
            int k;
            int l;
            try
            {
                k = Integer.parseInt(array[0]);
                l = Integer.parseInt(array[1]);
            }
            catch (Exception e)
            {
                writeMessage("Неверные данные");
                continue;
            }
            if (k <= 0 || l <= 0 || array.length > 2)
            {
                writeMessage("Неверные данные");
                continue;
            }
            break;
        }
        return array;
    }

}
