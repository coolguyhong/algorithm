package test.factory;

public class AnimalFactory {
    public static Animal create(String animalName) {
        if (animalName == null) {
            throw new IllegalArgumentException("not null");
        }

        if (animalName.equals("cow")) {
            return new Cow();
        } else if (animalName.equals("cat")) {
            return new Cat();
        } else if (animalName.equals("dog")) {
            return new Dog();
        } else {
            return null;
        }

    }
}
