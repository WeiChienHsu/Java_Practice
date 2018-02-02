public class Circle extends Shape {
    public Circle(){}

    @Override
    public void playSound(){
        System.out.println("playSound!! From child class!");
    }
}
