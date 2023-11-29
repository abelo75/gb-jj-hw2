public class Dog extends Animal {
    private final boolean isDangerous;

    public Dog(String name, int age, boolean isDangerous) {
        super(name, age);
        this.isDangerous = isDangerous;
    }

    public void makeSound() {
        System.out.println("Gav");
    }

    @Override
    public String toString() {
        return "Dog {" + "isDangerous=" + isDangerous + ", name=" + getName() + ", aged=" + getAge() + '}';
    }
}
