package farm;

import farm.utils.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        String commands = "тя1вкать, гулять, rr";
        for (String s : commands.split(", "))
            System.out.printf("%s_%s", s, s.matches("[а-я]+"));

    }
}