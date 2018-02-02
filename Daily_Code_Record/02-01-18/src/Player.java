public class Player {
    private String name;
    private int number;

    public Player(String name) {
        this.name = name;
    }

    public void guess(){
        this.number = (int) (Math.random() * 10);
        System.out.println(this.name + " guess: " + this.number);
    }

    public int guessedNumber(){
        return number;
    }

    public String getName(){
        return name;
    }
}
