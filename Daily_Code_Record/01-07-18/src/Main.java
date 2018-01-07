import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class HDTV {
    private int size;
    private String brand;

    public HDTV(int size, String brand) {
        this.size = size;
        this.brand = brand;
    }

    public int getSize() {
        return size;
    }

    public String getBrand() {
        return brand;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

class SizeComparator implements Comparator<HDTV> {
    @Override
    public int compare(HDTV tv1, HDTV tv2) {
        int tv1Size = tv1.getSize();
        int tv2Size = tv2.getSize();

        if(tv1Size > tv2Size) {
            return -1;
        } else if (tv1Size < tv2Size) {
            return 1;
        } else {
            return 0;
        }
    }
}

public class Main {

    public static void main(String[] args) {
        HDTV tv1 = new HDTV(50, "Apple");
        HDTV tv2 = new HDTV(28,"Samsung");
        HDTV tv3 = new HDTV(33,"Foxconn");

        List<HDTV> al = new ArrayList<>();
        al.add(tv1);
        al.add(tv2);
        al.add(tv3);

        Collections.sort(al, new SizeComparator());

        for(HDTV a : al) {
            System.out.println(a.getBrand() + ": size - " + a.getSize());
        }
    }
}