/*
   Set up the game
   Play the game until the DotComs are dead
   End the game

 */

import java.util.ArrayList;

class DotComBust {
    GameHelper helper = new GameHelper();
    ArrayList<DotCom> dotComsList = new ArrayList<>();
    int numOfGuesses = 0;

    /*
    Initialize the DotCom(name, location)
     */
    private void setUpGame(){
        DotCom dot1 = new DotCom("dot1");
        DotCom dot2 = new DotCom("dot2");
        DotCom dot3 = new DotCom("dot3");
        dotComsList.add(dot1);
        dotComsList.add(dot2);
        dotComsList.add(dot3);

        System.out.println("Your Goal is to Sink 3 dots");
        System.out.println("Try to sink them all in the fewest number of guesses");

        for(DotCom dot : dotComsList) {
            ArrayList<String> newLocation = helper.placeDotCom(3);
            dot.setLocationCells(newLocation);
        }
    }

    /*
        Asks player for guesses
        Call checkUserGuess()
 */
    private void startPlaying(){
        while(!dotComsList.isEmpty()) {
            String userGuess = helper.getUserInput("Please Enter a guess: (a1 - g7)");
            checkUserGuess(userGuess);
        }
        finishGame();
    }

    /*
        Loop through all remaining DotCom objects
        Call eah DotCom checkYourself()
     */
    private void checkUserGuess(String userGuess){
        numOfGuesses++;
        String result = "miss";
        for(DotCom dot : dotComsList) {
            result = dot.checkYourself(userGuess);
            if (result.equals("hit")){
                break;
            }
            if(result.equals("kill")) {
                dotComsList.remove(dot);
                break;
            }
        }
        System.out.println(result);
    }

    private void finishGame(){
        System.out.println("Game Finished!");
        if(numOfGuesses <= 18){
            System.out.println("It only took you " + numOfGuesses + " guesses");
        } else {
            System.out.println("You took long enough " + numOfGuesses + " guesses." );
        }
    }

    public static void main(String[] args) {
        DotComBust game = new DotComBust();
        game.setUpGame();
        game.startPlaying();
    }

}
