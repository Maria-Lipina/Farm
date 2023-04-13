package farm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Controller {

    FileHandler extData;

    Farm farm;
    boolean work = true;

    public Controller() {
        extData = new FileHandler();
        farm = new Farm();
    }

    void init() {
        ArrayList<String[]> animals =
                extData.load("src/main/resources/animals_prog.csv");
        farm.petsOptions =
                extData.load("src/main/resources/pets_prog.csv");
        farm.packsOptions =
                extData.load("src/main/resources/packs_prog.csv");

        for (String[] record: farm.packsOptions) {
            System.out.printf("%d ", record.length);
            System.out.printf("%s %s%n", Arrays.toString(record), record[2]);
        }

        System.out.println("----");
        for (String[] record: animals) {
            farm.addAnimal(record);
        }

    }

    public void run() {
        this.init();
        if (farm.animals.isEmpty() || farm.petsOptions.isEmpty() || farm.packsOptions.isEmpty() ) {
            System.out.println(MenuConst.empty);
            System.out.println("ѕриложение не может быть запущено.");
            return;
        }
        System.out.println(MenuConst.hello);
        Scanner sc = new Scanner(System.in);
        while (this.work) {
            String s = sc.nextLine();
            if (!s.contains(" ")) this.runNoArgsCommands(s);
            else {
                runArgsCommands(
                        s.substring(0, s.indexOf(" ")),
                        checkInput(s.substring(0, s.indexOf(" ")), s.substring(s.indexOf(" ")).trim()));
            }
        }
    }

    void runNoArgsCommands(String command) {
        switch (command) {
            case "manual": System.out.println(MenuConst.manual); break;
            case "observe": farm.animals.forEach(System.out::println); break;
            case "exit": {
                System.out.println(MenuConst.bye);
                this.work = false;
            } break;
        }
    }

    void runArgsCommands(String command, String[] arg) {
        switch (command) {
            case "search-n": {
                if (arg.length > 0) printResult(farm.searchByName(arg[0]));
                else System.out.println(MenuConst.fail);
            } break;
            case "search-c": {
                if (arg.length > 0) printResult(farm.searchByCategory(arg[0]));
                else System.out.println(MenuConst.fail);
            } break;
            case "add": {
                if (arg.length > 0) {
                    farm.addAnimal(arg);
                    System.out.println(farm.animals.get(farm.animals.size()-1));
                }
                else System.out.println(MenuConst.fail);
            } break;
            case "lookup": {
                if (arg.length > 0) {
                    LinkedList<Animal> result = farm.searchByName(arg[0]);
                    if (result.isEmpty()) System.out.println(MenuConst.empty);
                    else for (Animal a : result) {
                        System.out.println(a.getCommands());
                        switch (a.getClass().getSimpleName()) {
                            case "Dog" -> System.out.println(farm.petsOptions.get(0)[1]);
                            case "Cat" -> System.out.println(farm.petsOptions.get(1)[1]);
                            case "Hamster" -> System.out.println(farm.petsOptions.get(2)[1]);
                            case "Camel" -> System.out.println(farm.packsOptions.get(0)[1]);
                            case "Horse" -> System.out.println(farm.packsOptions.get(1)[1]);
                            case "Donkey" -> System.out.println(farm.packsOptions.get(2)[1]);
                        }
                    }
                }
                else System.out.println(MenuConst.fail);
            } break;
            case "teach": {
                if (arg.length > 0)
                    System.out.println(farm.teach(arg[0], arg[1]));
                else System.out.println(MenuConst.fail);
            } break;
            default: System.out.println(MenuConst.fail);
        }
    }

    String [] checkInput (String command, String userArgs) {
        switch (command) {
           case "search-n", "lookup": {
               if (userArgs.matches("[а-€\s]+")) return new String[]{userArgs};
           } break;
           case "search-c": {
               if (userArgs.equals("pet") || userArgs.equals("pack")) return new String[]{userArgs};
           } break;
           case "add": {
               String[] result = userArgs.split(";");
               if (result.length > 2 &&
                       "Dog, Cat, Hamster, Camel, Horse, Donkey".contains(result[0]) &&
                       result[1].matches("[а-€\s]+") &&
                       this.checkDate(result[2]) && this.checkCommands(result)
               ) return result;
           } break;
            case "teach": {
               String[] result = userArgs.split(";");
               if (result.length == 2 &&
                       result[0].matches("[а-€\s]+") &&
                       result[1].matches("[а-€\s]+")
               ) return result;
           } break;
       }
       return new String[0];
    }

    boolean checkDate(String date) {
        try {
            new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    boolean checkCommands(String[] commands) {
        if (commands.length == 3) return true;
        if (commands.length > 4) return false;
        for (String s : commands[3].split(", "))
            if (!s.matches("[а-€\s]+")) return false;
        return true;
    }

    void printResult(LinkedList<Animal> result) {
        if (result.isEmpty()) {
            System.out.println(MenuConst.empty);
        } else {
            result.forEach(System.out::println);
        }
    }

}
