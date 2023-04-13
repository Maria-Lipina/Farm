package farm.utils;

import java.util.ArrayList;
import java.util.LinkedList;

class Farm {
    ArrayList<Animal> animals;

    ArrayList<String[]> petsOptions;
    ArrayList<String[]> packsOptions;


    public Farm () {
        animals = new ArrayList<>();
    }

    void addAnimal(String [] animal) {

        switch (animal[0]) {
            case "Dog" ->
                animals.add(new Dog(animal));
            case "Cat" ->
                animals.add(new Cat(animal));
            case "Hamster" ->
                animals.add(new Hamster(animal));
            case "Camel" ->
                animals.add(new Camel(animal,
                    Integer.parseInt(searchOption(packsOptions, 2, "верблюд"))));
            case "Horse" ->
                animals.add(new Horse(animal,
                    Integer.parseInt(searchOption(packsOptions, 2, "лошадь"))));
            case "Donkey" ->
                animals.add(new Donkey(animal,
                    Integer.parseInt(searchOption(packsOptions, 2, "осел"))));
        }

        if (animals.size() > 0) System.out.println("Добавленное животное: " + animals.get(animals.size() -1));
    }

    private String searchOption(ArrayList<String[]> options, int findIndex, String animal) {
        for (String[] option : options) {
            if (option[1].equals(animal)) return option[findIndex];
        }
        return "-1";
    }

    LinkedList<Animal> searchByName(String animalName) {
        LinkedList<Animal> result = new LinkedList<>();
        for (Animal anim: animals) {
            if (anim.getName().equals(animalName)) {
                result.add(anim);
            }
        }
        return result;
    }

    LinkedList<Animal> searchByCategory (String category) {
        LinkedList<Animal> result = new LinkedList<>();
        for (Animal anim: animals) {
            if (
                (category.equals("pet") && anim instanceof PetAnimal) ||
                (category.equals("pack") && anim instanceof PackAnimal)
            )   result.add(anim);
        }
        return result;
    }

    boolean canLearnCommand(Animal anim, String command) {
        if(anim.getCommands().contains(command)) return false;
        if(anim instanceof PetAnimal) {
            if (!searchOption(petsOptions, 1, anim.getClass().getSimpleName())
                    .contains(command)) return false;
        }
        if(anim instanceof PackAnimal) {
            if (!searchOption(packsOptions, 1, anim.getClass().getSimpleName())
                    .contains(command)) return false;
        }
        return true;
    }

    String teach (String animalName, String command) {
        LinkedList<Animal> students = this.searchByName(animalName);
        StringBuilder result = new StringBuilder();
        if (students == null) {
            return String.format("%s не живет в питомнике");
        } else {
            for (Animal student: students) {
                if (this.canLearnCommand(student, command)) {
                    student.learnCommand(command);
                    result.append(
                        String.format("Теперь %s знает такие команды: %s%n", animalName, student.getCommands()));
                }
                else {
                    result.append(
                            String.format("%s не может выучить команду %s%n", animalName, command));
                }
            }
        }
       return result.toString();
    }
}
