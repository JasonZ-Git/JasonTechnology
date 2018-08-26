package org.jason.algorithm;

import java.util.Arrays;

public class DataValidation {
  public static String validate(String input) {
    String[] temp = input.split("~n");
    
    int records = temp.length - 1;
    long emptyFiledNumber = 0;
    int maxFieldLength = 0;
    
    for(int index =1; index <= temp.length - 1 ; ++index) {
      String current = temp[index].trim().replace("~|", "~n");
      String[] items = current.substring(1).split("\\|");
      if (items.length - 1  > maxFieldLength){
        maxFieldLength = items.length - 1;
      }
      long itemEmptyFieldNumber = Arrays.asList(items).stream().filter(u-> u.equals("")).count();
      emptyFiledNumber += itemEmptyFieldNumber;
    }
    
    String[] tempFields = temp[0].split("\\|");
    String lastField = "";
    if (maxFieldLength >1){
      lastField = tempFields[tempFields.length -1]+ "_" + (maxFieldLength-1);
    } else {
      lastField = tempFields[tempFields.length -1];
    }
    return records + ":" + maxFieldLength + ":" + emptyFiledNumber + ":" + lastField;
  }
 }
