public class test {

    public static void main(String[] args) {
        SimpleDotCom dot = new SimpleDotCom();

        int[] location = {2,3,4};
        dot.setLocationCells(location);

        String userGuess = "2";
        String res = dot.checkYourself(userGuess);
        String testRes = "Failed.";

        if(res.equals("Hit")) {
            testRes = "Passed.";
        }

        System.out.println(testRes);
    }
}
