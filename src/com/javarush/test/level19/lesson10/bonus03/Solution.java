package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span><span>Super</span><span>girl</span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) throws Exception {
        //args = new String[]{"span"};
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        BufferedReader bfile = new BufferedReader(new FileReader(fileName));
        String line;
        String mainText = "";
        String text = "";
        String resultText = "";
        String part = "";
        LinkedList<String> parts = new LinkedList<>();
        boolean flag = false;
        while((line = bfile.readLine()) != null) {
            mainText += line.replace("\n","");
        }

            for(String arg : args) {
                text = mainText;

                while(text.contains(arg)) {
                    String strBeforeText = text.substring(0, text.indexOf("<" + arg));
                    text = text.replace(strBeforeText, "");
                    if(text.indexOf("<"+arg+" ") != -1) {
                        part = text.substring(text.indexOf("<"+arg+" "), text.indexOf(">")+1);
                        text = text.replaceFirst(part,"");
                        resultText += part;
                        while(text.indexOf("<"+arg) < text.indexOf("</"+arg)) {
                            part = text.substring(0, text.indexOf("</" + arg) + arg.length() + 3);
                            text = text.replaceFirst(part,"");
                            resultText += part;
                        }
                        part = text.substring(0, text.indexOf("</"+arg)+ arg.length() + 3);
                        resultText += part;
                        text = text.replaceFirst(part,"");
                        parts.addFirst(resultText);

                        text = resultText.replaceFirst(arg,"");


                        while (text.contains("<"+arg) /*&& text.lastIndexOf("<"+arg) > 0*/) {
                            if(text.indexOf("<"+arg) < text.indexOf("</")) {
                                part = text.substring(text.indexOf("<"+arg+">"), text.indexOf("</" + arg+">") + arg.length() + 3);
                                text = text.replaceFirst(part,"");
                                parts.addLast(part);
                            }
                        }
                        mainText = mainText.replaceAll(resultText, "").replaceAll(strBeforeText,"");
                        text = mainText;
                    } else if(text.indexOf("<"+arg+">") != -1) {
                        part = text.substring(text.indexOf("<"+arg+">"), text.indexOf("</"+arg+">")+arg.length()+3);
                        parts.addLast(part);
                        text = text.replaceFirst(part,"");
                        mainText = text;
                    }
                }
            }
        for(String s : parts) {
            System.out.println(s);
        }
        reader.close();
        bfile.close();

    }
}
