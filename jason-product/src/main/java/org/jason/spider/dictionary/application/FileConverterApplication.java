package org.jason.spider.dictionary.application;

import org.apache.commons.lang3.StringUtils;
import org.jason.annotation.Application;
import org.jason.util.JasonFileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Application(name = "Convert Property file to JSON")
public class FileConverterApplication {

    private static String formatter = "{\"{0}\": \"{1}\",\"{2}\": \"{3}\",\"{4}\": {5}\"}";

    public static void main(String[] args) {

    }

    public static String convertPropertyToJSON(String fileName) throws IOException {
        List<String> words = JasonFileUtil.readFile(fileName);

        List<String> resultList =  new ArrayList<>();
        int count = 1;
        for(String word : words) {
            String source = word.split("=")[0];
            String translation = word.split("=")[1].substring(1);

            String stage = String.format(formatter, "word", source, "translation", translation, "id", count++);
            resultList.add(stage);
        }

        return String.join(",", resultList);
    }
}
