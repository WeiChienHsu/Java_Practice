public class Dog extends Animal implements Pet {

    @Override
    void eat() {
        System.out.println("Dog eat meat.");
    }

    @Override
    public void beFriendly() {
        System.out.println("I'm gonna be friendly!");
    }

    @Override
    public void play() {
        System.out.println("I'm a pet!");
    }

}
