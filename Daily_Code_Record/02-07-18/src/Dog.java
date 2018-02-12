public class Dog extends Animal implements Pet {
    int size = 0;
    boolean isDog = false;

    public Dog(){
        this.size = size;
    }

    public Dog(int size) {
        super();
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    void eat() {
        System.out.println("Dog eat meat.");
    }

    @Override
    public void makeNoise(){
        super.makeNoise();
        System.out.println("Make noises from Dog");
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
