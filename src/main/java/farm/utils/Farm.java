package farm.utils;

import java.util.ArrayList;

class Farm {
    ArrayList<Animal> animals;

    ArrayList<String[]> petsOptions;
    ArrayList<String[]> packsOptions;


    public Farm () {
        animals = new ArrayList<>();
    }

    void addAnimal(String [] animal) {

        Animal toAdd;
        switch (animal[0]) {
            case "собака": toAdd = new Dog(animal); break;
            case "кошка": toAdd = new Cat(animal);  break;
            case "хомяк": toAdd = new Hamster(animal);  break;
            case "верблюд": toAdd = new Camel(animal,
                    Integer.parseInt(searchOption(packsOptions, 3, "верблюд")));  break;
            case "лошадь": toAdd = new Horse(animal,
                    Integer.parseInt(searchOption(packsOptions, 3, "лошадь")));  break;
            case "осел": toAdd = new Donkey(animal,
                    Integer.parseInt(searchOption(packsOptions, 3, "осел")));  break;
            default:
                throw new IllegalStateException("Unexpected value: " + animal[0]);
        }

        animals.add(toAdd);
        System.out.println("Добавленное животное: " + toAdd.getClass().getSimpleName() + " " + toAdd);
    }

    private String searchOption(ArrayList<String[]> options, int findIndex, String animal) {
        for (String[] option : options) {
            if (option[1].equals(animal)) return option[findIndex];
        }
        return "-1";
    }
}
