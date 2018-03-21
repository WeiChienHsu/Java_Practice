public class timeComplexity {
    public static void main(String[] args) {
        int k = 1;
        for(int i = 0; i < 10; i++){
            for(int j = 0 ; j < 10; j++ ) {
                System.out.println(k++);
            }
        }
        System.out.println(k / 10);
    }
}
