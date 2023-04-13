package farm.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Controller {

    FileHandler extData;

    Farm farm;

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

        for (String[] record: animals) {
            System.out.printf("%d ", record.length);
            System.out.println(Arrays.toString(record));
        }
        System.out.println("----");
        for (String[] record: farm.petsOptions) {
            System.out.printf("%d ", record.length);
            System.out.printf("%s %s%n", Arrays.toString(record), record[2]);
        }
        System.out.println("----");
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
        Scanner sc = new Scanner(System.in);
        StringBuilder command = new StringBuilder();
        boolean work = true;
        while (work) {
            String s = sc.nextLine();
            if (s.contains(" ")) {
                String userArgs = s.substring(s.indexOf(" ")).trim();
                switch (s.substring(0, s.indexOf(" "))) {
                    case "manual":
                        System.out.println(MenuConst.manual);
                    case "observe": {
                        for (Animal a : farm.animals) {
                            System.out.println(a);
                        }
                    }
                    case "search-n": {
                        String [] arg = checkInput("search-n", userArgs);
                        if (arg != null) {
                            farm.searchByName(arg[0]).forEach(System.out::println);
                        }
                    }
                    case "search-c": {
                        String [] arg = checkInput("search-c", userArgs);
                        if (arg != null) {
                            farm.searchByCategory(arg[0]).forEach(System.out::println);
                        }
                        else System.out.println(MenuConst.fail);
                    }
                    case "add": {
                        String [] arg = checkInput("add", userArgs);
                        if (arg != null) {
                            farm.addAnimal(arg);
                            System.out.println(farm.animals.get(farm.animals.size()-1));
                        }
                        else System.out.println(MenuConst.fail);
                    }
                    case "lookup": {
                        String [] arg = checkInput("lookup", userArgs);
                        if (arg != null) {
                            LinkedList<Animal> result = farm.searchByName(arg[0]);
                            for (Animal a: result) {
                                System.out.println(a.getCommands());
                                switch (a.getClass().getSimpleName()) {
                                    case "Dog" ->
                                        System.out.println(farm.petsOptions.get(0)[1]);
                                    case "Cat" ->
                                        System.out.println(farm.petsOptions.get(1)[1]);
                                    case "Hamster" ->
                                        System.out.println(farm.petsOptions.get(2)[1]);
                                    case "Camel" ->
                                        System.out.println(farm.packsOptions.get(0)[1]);
                                    case "Horse" ->
                                        System.out.println(farm.packsOptions.get(1)[1]);
                                    case "Donkey" ->
                                        System.out.println(farm.packsOptions.get(2)[1]);
                                }
                            }
                        }
                        else System.out.println(MenuConst.fail);
                    }
                    case "teach": {
                        String [] arg = checkInput("lookup", userArgs);
                        if (arg != null) {
                            System.out.println(farm.teach(arg[0], arg[1]));
                        }
                    }
                    case "exit":
                        System.out.println(MenuConst.bye);
                        work = false;
                    default:
                        System.out.println(MenuConst.fail);
                }
            }
        }

    }

    String [] checkInput (String command, String userArgs) { //проверяет команду от пользователя на сооветствие синтаксису и Options
        return null;
    }

}
