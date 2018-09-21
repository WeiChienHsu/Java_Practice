/* the following two pieces of code execute the same way: */

public class sync {

    public synchronized void methodA(int i, String msg) {
        log.writeln(Integer.toString(i));
        log.writeln(msg);
    }

    public void methodB(int i, String msg) {
        synchronized (this) {
            log.writeln(Integer.toString(i));
            log.writeln(msg);
        }
    }
}


class log {

    public log() {;}

    public static void writeln(Integer o) {}

    public static void writeln(String s) {}

}