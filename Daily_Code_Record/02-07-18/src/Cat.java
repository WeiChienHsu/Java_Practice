public class Cat extends Animal{
    String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void makeNoise(){
        System.out.println("Noises from Cat");
    }

    @Override
    public void eat(){
        System.out.println("Cat eat Fish!");
    }
}
