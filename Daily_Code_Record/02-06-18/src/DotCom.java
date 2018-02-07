import java.util.ArrayList;

public class DotCom {
    String name;
    private ArrayList<String> locationCells;

    public DotCom(String name) {
        this.name = name;
    }

    public void setLocationCells(ArrayList<String> locationCells) {
        this.locationCells = locationCells;
    }

    public String checkYourself(String userInput){
        String result = "miss";
        int index = locationCells.indexOf(userInput);
        if(index >= 0) {
            locationCells.remove(index);
            if(locationCells.isEmpty()){
                result = "kill";
                System.out.println("You sunk " + name + " !!");
            } else {
                result = "hit";
            }
        }
        return result;
    }
}
