public class GameLauncher {

    public static void main(String[] args) {
        int numOfGuesses = 0;
        GameHelper helper = new GameHelper();
        SimpleDotCom game = new SimpleDotCom();
        int randomNum = helper.giveARandomNum();
        int[] location = {randomNum, randomNum + 1, randomNum + 2};

        // Assign Location into game
        game.setLocationCells(location);
        boolean isAlive = true;

        while (isAlive) {
            String guess = helper.getUserInput("Please enter the number: ");
            String result = game.checkYourself(guess);
            numOfGuesses++;
            if (result.equals("kill")) {
                isAlive = false;
                System.out.println("You took " + numOfGuesses + " times guessing to kill.");
            }
        }
    }
}
