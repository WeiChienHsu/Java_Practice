public class SimpleDotCom {
    private int[] locationCells;
    int numOfHits;

    public SimpleDotCom(){
        this.locationCells =  locationCells;
        this.numOfHits = 0;
    }

    public void setLocationCells(int[] locations) {
        this.locationCells = locations;
    }

    public String checkYourself(String userGuess) {
        int userGuessInt = Integer.parseInt(userGuess);
        for(int i : locationCells) {
            if(i == userGuessInt) {
                if(numOfHits == locationCells.length - 1) {
                    return "Kill";
                } else {
                    numOfHits++;
                    return "Hit";
                }
            }
        }
        return "Miss~~";
        }
    }
