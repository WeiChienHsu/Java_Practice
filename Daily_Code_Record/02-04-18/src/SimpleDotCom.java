public class SimpleDotCom {
    private int[] locationCells;
    int numOfHits;

    public SimpleDotCom() {
        this.locationCells = locationCells;
        this.numOfHits = 0;
    }

    public void setLocationCells(int[] locations) {
        this.locationCells = locations;
    }

    public String checkYourself(String userGuessStr) {
        int userGuess = Integer.parseInt(userGuessStr);
        String result = "miss";

        for (int i : locationCells) {
            if (i == userGuess) {
                result = "hit";
                numOfHits++;
                break;
            }
        }

        if(numOfHits == locationCells.length ){
            result = "kill";
        }

        System.out.println(result);
        return result;

    }
}
