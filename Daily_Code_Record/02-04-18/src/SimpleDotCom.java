import java.util.ArrayList;

public class SimpleDotCom {
    private ArrayList<Integer> locationCells;
    int numOfHits;

    public SimpleDotCom() {
        this.locationCells = locationCells;
        this.numOfHits = 0;
    }

    public void setLocationCells(ArrayList<Integer> locations) {
        this.locationCells = locations;
    }

    public String checkYourself(String userGuessStr) {
        int userGuess = Integer.parseInt(userGuessStr);

        while (!locationCells.isEmpty()){
            for (int i : locationCells) {
                if (i == userGuess) {
                    locationCells.remove(locationCells.indexOf(i));
                    return "hit";
                }
            }
            return "miss";
        }
        return "kill";
    }
}
