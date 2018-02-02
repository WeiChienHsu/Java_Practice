public class GuessGame {

    Player p1;
    Player p2;
    Player p3;
    int number;

    public GuessGame(){ }

    public void startGame() {
        p1 = new Player("Kevin");
        p2 = new Player("Steven");
        p3 = new Player("John");


        while(true) {
            number = (int)(Math.random() * 10);
            System.out.println("Number is: " + number);

            if(p1.guessedNumber() == number) {
                System.out.println("Winner is: " + p1.getName());
                break;
            }
            if(p2.guessedNumber() == number){
                System.out.println("Winner is: " + p2.getName());
                break;
            }
            if(p3.guessedNumber() == number){
                System.out.println("Winner is: " + p3.getName());
                break;
            }

            System.out.println("There is no Winner, Guess Again!!");
        }


    }
}
