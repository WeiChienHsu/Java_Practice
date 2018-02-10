public class Test {
    public static void main(String[] args) {
        String test = "yes";
        try {
            System.out.println("Start Try");
            doRisky(test);
            System.out.println("End Test");
        } catch(ScaryException ex) {
            System.out.println("Exception!");
        } finally {
            System.out.println("Finally");
        }
        System.out.println("End of Main");
    }

    static void doRisky(String test) throws ScaryException {
        System.out.println("Start Risky!");
        if("yes".equals(test)){
            throw new ScaryException();
        }
        System.out.println("end risky");
    }
}

class ScaryException extends Exception{};
