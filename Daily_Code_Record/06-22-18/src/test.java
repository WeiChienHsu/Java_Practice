public class test {
    public static void main(String[] args) {
        test.print2(5);
    }

    public static void print2(int row) {
        for (int i = 0; i < row; i++) {
            char ch = 'a';
            char print = ch;
            for (int j = 0; j <= i; j++) {
                System.out.print((print++));
            }
            System.out.println("");
        }
    }
}
