package farm.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

    FileHandler extData;

    Farm farm;

    public Controller() {
        extData = new FileHandler();
        farm = new Farm();
    }

    public void init() {
        ArrayList<String[]> animals =
                extData.load("src/main/resources/animals_prog.csv");
        farm.petsOptions =
                extData.load("src/main/resources/pets.csv");
        farm.packsOptions =
                extData.load("src/main/resources/packs.csv");

        for (String[] record: animals) {
            System.out.printf("%d ", record.length);
            System.out.println(Arrays.toString(record));
        }
        System.out.println("----");
        for (String[] record: farm.petsOptions) {
            System.out.printf("%d ", record.length);
            System.out.printf("%s %s%n", Arrays.toString(record), record[3]);
        }
        System.out.println("----");
        for (String[] record: farm.packsOptions) {
            System.out.printf("%d ", record.length);
            System.out.printf("%s %s%n", Arrays.toString(record), record[3]);
        }
        System.out.println("----");
        for (String[] record: animals) {
            farm.addAnimal(record);
        }


    }

    public void run() { //Тут команды, которые дает пользователь и их выполнение
        this.init();

    }

    boolean checkInput (String command) { //проверяет команду от пользователя на сооветствие синтаксису и Options
        return false;
    }


}
