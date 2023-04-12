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

        switch (animal[0]) {
            case "Dog":
                animals.add(new Dog(animal));  break;
            case "Cat":
                animals.add(new Cat(animal));  break;
            case "Hamster":
                animals.add(new Hamster(animal));  break;
            case "Camel":
                animals.add(new Camel(animal,
                    Integer.parseInt(searchOption(packsOptions, 3, "верблюд"))));  break;
            case "Horse":
                animals.add(new Horse(animal,
                    Integer.parseInt(searchOption(packsOptions, 3, "лошадь"))));  break;
            case "Donkey":
                animals.add(new Donkey(animal,
                    Integer.parseInt(searchOption(packsOptions, 3, "осел"))));  break;
        }

        if (animals.size() > 0) System.out.println("Добавленное животное: " + animals.get(animals.size() -1));
    }

    private String searchOption(ArrayList<String[]> options, int findIndex, String animal) {
        for (String[] option : options) {
            if (option[1].equals(animal)) return option[findIndex];
        }
        return "-1";
    }

    Animal canLearnCommand(String animalName, String command) {
        for (Animal anim: animals) {
            if (anim.getName().equals(animalName)) {
                if(anim.getCommands().contains(command)) return null;
                if(anim instanceof PetAnimal) {
                    if (searchOption(petsOptions, 2, anim.getClass().getSimpleName())
                            .contains(command)) return anim;
                }
                if(anim instanceof PackAnimal) {
                    if (searchOption(packsOptions, 2, anim.getClass().getSimpleName())
                            .contains(command)) return anim;
                }
            }
        }
        return null;
    }
}
