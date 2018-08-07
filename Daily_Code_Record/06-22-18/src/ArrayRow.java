public class ArrayRow {
    public static void main(String[] args) {
        ArrayRow.print3(4);
    }

    public static void print3(int row) {
        int x = 1;
        for(int i = 1; i <= row; i++ ) {
            for(int j = i; j > 0; j--) {
                System.out.print(x + "" + x);
            }
            System.out.println("");
        }
    }


}
