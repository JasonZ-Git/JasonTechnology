package org.jason.spider.dictionary.application;

import org.apache.commons.lang3.StringUtils;
import org.jason.annotation.Application;
import org.jason.spider.dictionary.DictionaryConstants;
import org.jason.util.JasonFileUtil;
import org.jason.util.JasonStringUtil;
import org.jason.util.finalclass.StringPair;
import org.jason.util.translation.JasonDictionaryUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Application(name = "Convert key=value Property file to JSON")
public class JSONFileConverterApplication {

    public static void main(String[] args) throws IOException {
        String finalJsonString = convertPropertyToJSON(DictionaryConstants.DICTIONARY_FILE);

        JasonFileUtil.writeFile(DictionaryConstants.DICTIONARY_JSON_FILE, finalJsonString);
        System.out.println(finalJsonString);
    }

    public static String convertPropertyToJSON(String fileName) throws IOException {
        List<String> words = JasonFileUtil.readFile(fileName);

        List<StringPair> wordTransPairs =  words.stream().map( item -> JasonStringUtil.cutTwoBy(item, "="))
                .filter(Objects::nonNull).collect(Collectors.toList());

        List<String> jsonStrings = JasonDictionaryUtil.convertToJson(wordTransPairs);

        return JasonStringUtil.squareBracketed(String.join(",", jsonStrings));
    }
}
