public class Duck {
    private int size;
    private static int duckCount = 0;

    public Duck(int size) {
        this.size = size;
        duckCount++;
    }


    public int getSize() {
        return size;
    }

    public int getDuckCount(){
        return duckCount;
    }
}


