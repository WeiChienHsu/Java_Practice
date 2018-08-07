public class EvenOddPattern {

    public static void main(String[] args) {
        EvenOddPattern.printPattern(13);
    }
    public static void printPattern(int num) {
        int i , print;
        if(num % 2 == 0) {
            print = 0;
            while(print <= num){
                System.out.print(print + " ");
                print += 2;
            }


        } else {
            print = 1;
            for( i = 0; i < num; i = i + 2)
            {
                System.out.print(print + " ");
                print += 2;
            }

        }
    }
}

