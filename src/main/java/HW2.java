import java.lang.reflect.*;

public class HW2 {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        Animal[] animals = {
                new Cat("Vasily", 2),
                new Dog("Simon", 4, true),
                new Animal("Bars", 9),
                new Cat("Matroskin", 2),
                new Dog("Sharik", 12, false),
        };
        for (Animal animal: animals) {
            ClassInfo.printObjectInfo(animal);
        }
    }
}
