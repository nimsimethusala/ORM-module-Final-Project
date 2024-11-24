package org.example.ormcourseworkfinal.util;

import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public  static boolean isTextFieldValid(TextFeildRegex textField  , String text){

        String filed = "";

        switch (textField){
            case NAME:
                filed = "^[A-z|\\\\s]{3,}$";
                break;
            case CONTACT:
                filed = "^07[0-9]{8}$";
                break;
            case PRICE:
                filed = "^\\d+$";
                break;
            case EMAIL:
                filed = "^[a-zA-Z0-9]+@[a-zA-Z]+\\.com$";
        }

        Pattern pattern = Pattern.compile(filed);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;
    }

    public static boolean setTextColor(TextFeildRegex location, TextField textField) {
        if (Regex.isTextFieldValid(location, textField.getText())) {
            textField.setStyle("-fx-text-fill: Green; ");
        } else {
            textField.setStyle("-fx-text-fill: red;");

            return false;
        }
        return false;
    }
}
