package farm.utils;

import java.util.ArrayList;

abstract class PetAnimal implements Animal {

    protected String name;
    protected String birthDate;
    protected String learnedCommands;
    protected String toys;

    public PetAnimal (String [] properties) {
        name = properties[1];
        birthDate = properties[2];
        if (properties.length > 3) learnedCommands = properties[3];
        this.toys = "";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCommands() {
        if (learnedCommands != null) return learnedCommands;
        else return "не знает команд";
    }

    @Override
    public boolean learnCommand(String command) {
        learnedCommands = String.format("%s, %s", learnedCommands, command);
        return false;
    }

    public void setToys(String toys) {
        this.toys = toys;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", learnedCommands='" + learnedCommands + '\'' +
                ", toys='" + toys + '\'' +
                '}';
    }

    public boolean play (String toy, String command) {
        return false;
    }

}

class Dog extends PetAnimal {
    public Dog(String [] properties) {
        super (properties);
    }

}

class Cat extends PetAnimal {
    public Cat(String [] properties) {
        super (properties);
    }
}

class Hamster extends PetAnimal {
    public Hamster(String [] properties) {
        super (properties);
    }
}
