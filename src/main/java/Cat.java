public class Cat extends Animal {
    private boolean hasStripes;

    public Cat(String name, int age) {
        super(name, age);
    }

    public Cat(String name, int age, boolean hasStripes) {
        super(name, age);
        this.hasStripes = hasStripes;
    }

    public boolean isHasStripes() {
        return hasStripes;
    }

    public void setHasStripes(boolean hasStripes) {
        this.hasStripes = hasStripes;
    }

    public void makeStripes() {
        this.hasStripes = true;
    }

    @Override
    public String toString() {
        return "Cat{" + "hasStripes=" + hasStripes + ", name=" + getName() + ", aged=" + getAge() + '}';
    }

    public void removeStripes() {
        this.hasStripes = false;
    }
}
