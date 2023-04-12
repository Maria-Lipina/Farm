package farm.utils;

import java.util.ArrayList;

abstract class PackAnimal implements Animal {

    protected String name;
    protected String birthDate;
    protected String learnedCommands;
    protected int maxLoad;

    public PackAnimal (String [] properties, int maxLoad) {
        name = properties[1];
        birthDate = properties[2];
        if (properties.length > 3) learnedCommands = properties[3];
        this.maxLoad = maxLoad;
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

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", learnedCommands='" + learnedCommands + '\'' +
                ", maxLoad=" + maxLoad +
                '}';
    }

    boolean transport(int load) {
        return false;
    }

}

class Camel extends PackAnimal {
    public Camel(String [] properties, int maxLoad) {
       super (properties, maxLoad);
    }
}

class Donkey extends PackAnimal {
    public Donkey(String [] properties, int maxLoad) {
        super (properties, maxLoad);
    }
}

class Horse extends PackAnimal {
    public Horse(String [] properties, int maxLoad) {
        super (properties, maxLoad);
    }
}