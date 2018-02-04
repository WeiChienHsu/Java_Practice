public class array {
    public static void main(String[] args) {
        Dog[] pets;
        pets = new Dog[2];
        pets[0] = new Dog("Kevin",100);
        pets[1] = new Dog("Leborn",10);
        pets[0].bark(2);
        pets[1].bark(4);
    }
}

class Dog{
    String name;
    int weight;
    public Dog(String name, int weight){
        this.name = name;
        this.weight = weight;
    }

    public void bark(int time){
        while(time > 0) {
            if(weight >= 30) {
                System.out.println(name + ": WHA!!WHAAAA!");
                time--;
            } else {
                System.out.println(name + ": wha!Wha!");
                time--;
            }
        }


    }
}
