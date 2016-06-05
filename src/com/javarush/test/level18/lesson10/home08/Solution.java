package com.javarush.test.level18.lesson10.home08;

import java.io.*;
import java.lang.management.BufferPoolMXBean;
import java.util.HashMap;
import java.util.Map;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Закрыть потоки. Не использовать try-with-resources
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = "";
        boolean flag = false;
        while (!flag) {
            fileName = reader.readLine();
            if(fileName.equals("exit")) {
                flag = true;
            } else {
                ReadThread readThread = new ReadThread(fileName);
                readThread.join();
                /*for(Map.Entry<String, Integer> pair : resultMap.entrySet()) {
                    System.out.println(pair.getKey() + " " + pair.getValue());
                }*/
            }
        }

        reader.close();

    }

    public static class ReadThread extends Thread {

        HashMap<Integer, Integer> tmp = new HashMap<>();
        private String fileName;

        public ReadThread(String fileName) {
            this.fileName = fileName;
            this.start();
        }

        @Override
        public void run() {
            try {
                FileInputStream fileInputStream = new FileInputStream(fileName);
                while(fileInputStream.available() > 0) {
                    Integer oneByte = fileInputStream.read();
                    //System.out.println(oneByte);
                    if(tmp.containsKey(oneByte)) {
                        Integer count = tmp.get(oneByte);
                        tmp.put(oneByte, count++);
                    } else {
                        tmp.put(oneByte, 1);
                    }
                }
                Integer maxValue = 0;
                Integer maxKey = 0;
                for (Map.Entry<Integer,Integer> pair : tmp.entrySet()) {
                    if(pair.getValue() > maxValue) {
                        maxValue = pair.getValue();
                        maxKey = pair.getKey();
                    }
                }
                resultMap.put(this.fileName, maxKey);
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // implement file reading here - реализуйте чтение из файла тут
    }
}
