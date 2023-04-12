package farm.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

    FileHandler extData;
    ArrayList<String[]> animals;
    ArrayList<String[]> animalCommands;

    Farm farm;

    public Controller() {
        extData = new FileHandler();
        farm = new Farm();
    }

    public void init() {
        ArrayList<String[]> animals =
                extData.load("src/main/resources/animals.csv");
        animalCommands =
                extData.load("src/main/resources/species.csv");

        for (String[] record: animals) {
            System.out.printf("%d ", record.length);
            System.out.println(Arrays.toString(record));
        }
        System.out.println("----");
        for (String[] record: animalCommands) {
            System.out.println(Arrays.toString(record));
        }

        for (String [] animal: animals) {
            farm.addAnimal(animal);
        }

    }

    public void run() {
        this.init();

    }




}
