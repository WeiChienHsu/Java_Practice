abstract public class Animal {

    public Animal(){
        System.out.println("Constructor from SuperClass");
    }

    public void makeNoise() {
        System.out.println("Noises from Animal Class");
    }
    abstract void eat();
}
