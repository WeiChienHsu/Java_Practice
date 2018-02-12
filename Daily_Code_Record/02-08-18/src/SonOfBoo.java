public class SonOfBoo extends Boo {

    public SonOfBoo(){
        super("boo");
    }

    public SonOfBoo(int i) {
        super("Abc");
    }

    public SonOfBoo(String s) {
        super(42);
    }

    public SonOfBoo(String s, int i, int a) {
        super(s, i);
    }
}
